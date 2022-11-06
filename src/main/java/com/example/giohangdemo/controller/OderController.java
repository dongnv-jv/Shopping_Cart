package com.example.giohangdemo.controller;

import com.example.giohangdemo.domain.Product;
import com.example.giohangdemo.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OderController {
    @Autowired
    private OderService oderService;

    @GetMapping("/oder")
    public String oder(@RequestParam("id") int id, HttpSession http) {
        oderService.addCart(http, id);


//      HashMap<Integer, Product> productMap= (HashMap<Integer, Product>) http.getAttribute("cart");
//        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue().toString());
//        }

        return "redirect:/display";
    }
}
