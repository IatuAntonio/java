package org.example.audit.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditingAspect {

    @Before("@annotation(org.example.audit.annotation.Audited)")
    public void audit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.printf("[AUDIT] %s called at %s%n", methodName, LocalDateTime.now());
    }

}
