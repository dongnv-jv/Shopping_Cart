package com.example.giohangdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "oder_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oder_id")
    private int id;
    @Column(name = "oder_quantity")
    private int quantity;
    @Column(name = "oder_create")
//    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    @Column(name = "oder_status")
    private int status;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Oder(int id) {
        this.id = id;
    }
}
