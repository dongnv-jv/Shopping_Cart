package com.example.giohangdemo.repository;

import com.example.giohangdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
