package com.spring.study.common.aop;

import cn.hutool.core.util.StrUtil;
import com.spring.study.common.annotation.SystemLog;
import com.spring.study.common.util.IpInfoUtil;
import com.spring.study.common.util.ObjectUtil;
import com.spring.study.common.util.ThreadPoolUtil;
import com.spring.study.vo.EsLog;
import com.spring.study.vo.Log;
import com.spring.study.service.EsLogService;
import com.spring.study.service.LogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * Aop 实现日志管理
 *
 * @author: ZhouMingming
 * @data: Create on 2018/10/9.
 */
@Aspect
@Configuration
public class SystemLogAspect {

    private static final Logger log = LogManager.getLogger(SystemLogAspect.class);

    @Value("${spring-boot.elasticsearch.logRecord.es}")
    private static boolean IS_ES;
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    private IpInfoUtil ipInfoUtil;
    private LogService logService;
    private EsLogService esLogService;
    private HttpServletRequest httpServletRequest;

    public SystemLogAspect(@Autowired IpInfoUtil ipInfoUtil,
                           @Autowired LogService logService,
                           @Autowired EsLogService esLogService,
                           @Autowired(required = false) HttpServletRequest httpServletRequest) {
        this.ipInfoUtil = ipInfoUtil;
        this.logService = logService;
        this.esLogService = esLogService;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * 打印日志的触发点
     */
    @Pointcut("@annotation(com.spring.study.common.annotation.SystemLog)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 记录用户操作的开始时间
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }

    /**
     * 后置通知
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();

            if (StrUtil.isNotBlank(username)) {

                // 日志记录方式 true使用Elasticsearch记录 false记录至数据库中
                if (IS_ES) {
                    EsLog esLog = new EsLog();

                    //日志标题
                    esLog.setName(getControllerMethodDescription(joinPoint));
                    //日志请求url
                    esLog.setRequestUrl(httpServletRequest.getRequestURI());
                    //请求方式
                    esLog.setRequestType(httpServletRequest.getMethod());
                    //请求参数
                    Map<String, String[]> logParams = httpServletRequest.getParameterMap();
                    esLog.setMapToParams(logParams);
                    //请求用户
                    esLog.setUsername(username);
                    //请求IP
                    esLog.setIp(ipInfoUtil.getIpAddr(httpServletRequest));
                    //IP地址
                    esLog.setIpInfo(ipInfoUtil.getIpCity(ipInfoUtil.getIpAddr(httpServletRequest)));
                    //请求开始时间
                    Date logStartTime = beginTimeThreadLocal.get();

                    long beginTime = beginTimeThreadLocal.get().getTime();
                    long endTime = System.currentTimeMillis();
                    //请求耗时
                    Long logElapsedTime = endTime - beginTime;
                    esLog.setCostTime(logElapsedTime.intValue());
                    ipInfoUtil.getInfo(httpServletRequest, ObjectUtil.mapToStringAll(httpServletRequest.getParameterMap()));

                    //调用线程保存至ES
                    ThreadPoolUtil.getPool().execute(new SaveEsSystemLogThread(esLog, esLogService));
                } else {
                    Log log = new Log();

                    //日志标题
                    log.setName(getControllerMethodDescription(joinPoint));
                    //日志请求url
                    log.setRequestUrl(httpServletRequest.getRequestURI());
                    //请求方式
                    log.setRequestType(httpServletRequest.getMethod());
                    //请求参数
                    Map<String, String[]> logParams = httpServletRequest.getParameterMap();
                    log.setMapToParams(logParams);
                    //请求用户
                    log.setUsername(username);
                    //请求IP
                    log.setIp(ipInfoUtil.getIpAddr(httpServletRequest));
                    //IP地址
                    log.setIpInfo(ipInfoUtil.getIpCity(ipInfoUtil.getIpAddr(httpServletRequest)));
                    //请求开始时间
                    Date logStartTime = beginTimeThreadLocal.get();

                    long beginTime = beginTimeThreadLocal.get().getTime();
                    long endTime = System.currentTimeMillis();
                    //请求耗时
                    Long logElapsedTime = endTime - beginTime;
                    log.setCostTime(logElapsedTime.intValue());
                    ipInfoUtil.getInfo(httpServletRequest, ObjectUtil.mapToStringAll(httpServletRequest.getParameterMap()));

                    //调用线程保存至ES
                    ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(log, logService));
                }
            }
        } catch (Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }

    /**
     * 保存日志到es
     */
    private static class SaveEsSystemLogThread implements Runnable {
        private EsLog esLog;
        private EsLogService esLogService;

        public SaveEsSystemLogThread(EsLog esLog, EsLogService esLogService) {
            this.esLog = esLog;
            this.esLogService = esLogService;
        }

        @Override
        public void run() {
            esLogService.saveLog(esLog);
        }
    }

    /**
     * 保存日志到数据库
     */
    private static class SaveSystemLogThread implements Runnable {
        private Log log;
        private LogService logService;

        public SaveSystemLogThread(Log esLog, LogService logService) {
            this.log = esLog;
            this.logService = logService;
        }

        @Override
        public void run() {
            logService.save(log);
        }
    }

    /**
     * * 获取注解中对方法的描述信息 用于Controller层注解
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();

        String description = "";

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                //比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
                continue;
            }
            description = method.getAnnotation(SystemLog.class).description();
        }
        return description;
    }
}
