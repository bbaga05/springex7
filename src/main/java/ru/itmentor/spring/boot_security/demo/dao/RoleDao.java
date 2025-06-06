package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role findByName(String name);
    List<Role> findAll();
    Role findById(Long id);
}
