package com.spring.study.security;

import com.spring.study.bean.Response;
import com.spring.study.common.TimeUtils;
import com.spring.study.exception.ErrorStatusCode;
import com.spring.study.web.WebContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
@Slf4j
@Aspect
@Component("SecurityAspect")
public class SecurityAspect {

    public static final String DEFAULT_TOKEN_NAME = "Token";

    @Resource
    private TokenManager tokenManager;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        long start = TimeUtils.getCurr();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(IgnoreSecurity.class)
                || "ApiResourceController".equals(pjp.getTarget().getClass().getSimpleName())
                || "BasicErrorController".equals(pjp.getTarget().getClass().getSimpleName())) {
            return pjp.proceed();
        }

        String token = WebContext.getRequest().getHeader(DEFAULT_TOKEN_NAME);
        if (!tokenManager.checkToken(token)) {
            log.error(String.format("token [%s] is invalid", token));
            return Response.failure(ErrorStatusCode.TOKEN_ERROR.value(), ErrorStatusCode.TOKEN_ERROR.reasonPhrase());
        }

        Object result = pjp.proceed();
        log.info("[***className: " + pjp.getTarget().getClass().getSimpleName() + " ,wayName: " + pjp.getSignature().getName()
                + " ,timeConsumed: " + (TimeUtils.getCurr() - start) + " ms ***]");
        return result;
    }
}
