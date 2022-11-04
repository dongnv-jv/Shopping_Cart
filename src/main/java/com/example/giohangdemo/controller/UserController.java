package com.example.giohangdemo.controller;

import com.example.giohangdemo.domain.User;
import com.example.giohangdemo.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String addUser(Model model) {
        User user= new User();
        model.addAttribute("userinput",user);
        return "input";
    }
    @PostMapping("/add")
    public String addUser1(@ModelAttribute("user") User user) {
        if(user==null){
            System.out.println("chua input");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        log.info(user.getDayOfBirth().toString());
        iUserRepository.save(user);
        return "input";
    }

//    @ModelAttribute("userinput")
//    public User user() {
//        return new User();
//    }
}
