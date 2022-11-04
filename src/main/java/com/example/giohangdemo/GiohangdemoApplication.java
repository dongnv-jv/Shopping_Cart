package com.example.giohangdemo;

import com.example.giohangdemo.domain.User;
import com.example.giohangdemo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GiohangdemoApplication /*implements CommandLineRunner*/ {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(GiohangdemoApplication.class, args);
    }
//    @Override
//    public void run(String... args) throws Exception {
//        // Khi chương trình chạy
//        // Insert vào csdl một user.
//        User user = new User();
//        user.setUsername("dongbk");
//        user.setPassword(passwordEncoder.encode("123456"));
//        iUserRepository.save(user);
//        System.out.println(user);
//    }
}
