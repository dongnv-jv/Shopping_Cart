package com.example.giohangdemo.repository;

import com.example.giohangdemo.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query("select u from Product u ")
    Page<Product> getAllProduct(Pageable pageable);
}
