package com.arogyasathi.controller;

import com.arogyasathi.entity.User;
import com.arogyasathi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public User getEntity(@PathVariable Long id) {
        return service.getUserById(id);
    }
}
