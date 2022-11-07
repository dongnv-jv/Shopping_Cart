package com.example.giohangdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;
    @OneToMany(mappedBy = "category")
    private List<Oder> oders;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Category(User user) {
        this.user = user;
    }
}
