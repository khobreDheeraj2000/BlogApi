package com.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapi.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
