package com.spring.study.common;

import com.spring.study.bean.CheckResult;
import com.spring.study.exception.ErrorStatusCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/7.
 */
@Slf4j
public class JwtUtils {

    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long curr = TimeUtils.getCurr();
        Date now = new Date(curr);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("一帧视频营销工具")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMills = curr + ttlMillis;
            Date expDate = new Date(expMills);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = CheckResult.builder().build();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setSuccess(false);
            log.error("JwtUtils : validateJWT ExpiredJwtException" + e.getMessage());
            checkResult.setErrCode(ErrorStatusCode.SYSTEM_ERROR.value());
        } catch (SignatureException e) {
            checkResult.setSuccess(false);
            log.error("JwtUtils : validateJWT SignatureException" + e.getMessage());
            checkResult.setErrCode(ErrorStatusCode.SYSTEM_ERROR.value());
        } catch (Exception e) {
            checkResult.setSuccess(false);
            log.error("JwtUtils : validateJWT Exception" + e.getMessage());
            checkResult.setErrCode(ErrorStatusCode.SYSTEM_ERROR.value());
        }
        return checkResult;
    }

    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(JwtConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
