package org.example.audit.config;

import org.example.audit.aspect.AuditingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuditingAspect auditingAspect() {
        return new AuditingAspect();
    }

}