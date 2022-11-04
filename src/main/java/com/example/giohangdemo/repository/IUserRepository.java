package com.example.giohangdemo.repository;

import com.example.giohangdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
