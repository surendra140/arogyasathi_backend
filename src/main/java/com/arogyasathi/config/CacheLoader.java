package com.arogyasathi.config;

import com.arogyasathi.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheLoader {

    private final UserService userService;

    @Autowired
    public CacheLoader(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void loadCache() {
        userService.preloadAllUsersToCache();
    }
}
