package com.example.giohangdemo.service;

import com.example.giohangdemo.domain.Product;
import com.example.giohangdemo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoppingService {
@Autowired
private IProductRepository iProductRepository;


public Page<Product> getAllProduct(Pageable pageable){
return iProductRepository.getAllProduct(pageable);
}

}
