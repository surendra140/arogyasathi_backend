package com.arogyasathi.services;

import com.arogyasathi.entity.User;
import com.arogyasathi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CacheManager cacheManager;

//    @Cacheable(value = "userCache", key = "#id")
//    public User getEntityById(Long id) {
//        System.out.println("Fetching from DB for ID: " + id);
//        return repository.findById(Math.toIntExact(id)).orElse(null);
//    }

    public UserService(UserRepository repository, CacheManager cacheManager) {
        this.repository = repository;
        this.cacheManager = cacheManager;
    }

    public void preloadAllUsersToCache() {
        List<User> users = repository.findAll();
        Cache cache = cacheManager.getCache("userCache");
        if (cache != null) {
            for (User user : users) {
                cache.put(user.getId(), user);
            }
        }
    }

    public User getUserById(Long id) {
        Cache cache = cacheManager.getCache("userCache");
        if (cache != null) {
            User cachedUser = cache.get(id, User.class);
            if (cachedUser != null) {
                System.out.println("Returning from cache for ID: " + id);
                return cachedUser;
            }
        }
        System.out.println("Cache miss for ID: " + id + ", loading from DB");
        return repository.findById(Math.toIntExact(id)).orElse(null);
    }

}
