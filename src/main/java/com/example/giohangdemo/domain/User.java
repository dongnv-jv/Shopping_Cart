package com.example.giohangdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="user_control")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private  int id;
    @Column(name="user_fullname")
    private String name;
    @Column(name="user_name")
    private String username;
    @Column(name="user_pass")
    private String password;
    @Column(name="user_dob")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dayOfBirth;
    @OneToMany(mappedBy = "user")
    private List<Category> categories;
}
