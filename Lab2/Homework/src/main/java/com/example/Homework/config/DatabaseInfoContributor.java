package com.example.Homework.config;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.info.Info;

import java.util.Map;

@Component
public class DatabaseInfoContributor implements InfoContributor {

    private final DatabaseProperties dbProperties;

    public DatabaseInfoContributor(DatabaseProperties dbProperties) {
        this.dbProperties = dbProperties;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("database", Map.of(
                "url", dbProperties.getUrl(),
                "username", dbProperties.getUsername(),
                "driver", dbProperties.getDriverClassName()
        ));
    }

}
