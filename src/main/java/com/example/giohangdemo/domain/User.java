package com.example.giohangdemo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import java.sql.Date;
import java.util.Date;
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
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date dayOfBirth;
    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    public User(int idUser) {
        this.id=idUser;
    }
}
