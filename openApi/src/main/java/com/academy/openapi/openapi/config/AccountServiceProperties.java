package com.academy.openapi.openapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.academy.openapi.account")
public class AccountServiceProperties {
    String address;
}
