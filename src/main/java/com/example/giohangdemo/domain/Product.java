package com.example.giohangdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="product_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;
    @Column(name="product_name")
    private String name;
    @Column(name="product_cost")
    private double cost;
    @Column(name="product_decr")
    private String decription;
    @Column(name="product_image")
    private String images;
    @OneToMany(mappedBy = "product")
    private List<Oder> oders;

}
