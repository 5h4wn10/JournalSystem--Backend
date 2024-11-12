package com.journalsystem.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

public class DebugServiceSome {

    public void someMethod() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Authenticated user: " + authentication.getName());
        } else {
            System.out.println("No authenticated user.");
        }
    }
}
