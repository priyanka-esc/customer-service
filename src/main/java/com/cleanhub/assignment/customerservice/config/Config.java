package com.cleanhub.assignment.customerservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.cleanhub.assignment.customerservice.persistence")
@EnableJpaAuditing
public class Config {

}
