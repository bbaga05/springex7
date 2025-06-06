package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    void update(User user);
    void delete(long id);
    User findById(long id);
    List<User> findAll();
    User findByUsername(String username);
}
