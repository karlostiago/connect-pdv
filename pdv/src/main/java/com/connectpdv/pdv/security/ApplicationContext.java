package com.connectpdv.pdv.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationContext {

    private static ApplicationContext applicationContext;
    private final String userApplicationActual;

    public ApplicationContext() {
        userApplicationActual = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static synchronized ApplicationContext getInstance() {
        if (applicationContext == null) {
            applicationContext = new ApplicationContext();
        }
        return applicationContext;
    }

    public String getUserApplicationContext() {
        return userApplicationActual;
    }
}
