package com.example.giohangdemo.controller;

import com.example.giohangdemo.domain.Oder;
import com.example.giohangdemo.domain.Product;
import com.example.giohangdemo.service.OderService;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class OderController {
    @Autowired
    private OderService oderService;

    @GetMapping("/oder")
    public String oder(@RequestParam("id") int id, HttpSession http, Model model, RedirectAttributes redirectAttrs) {
        oderService.addCart(http, id);


        HashMap<Integer, Oder> productMap = (HashMap<Integer, Oder>) http.getAttribute("cart");
        productMap.forEach((key, value) ->
                log.info(value.getProduct().getName())
        );
//        model.addAttribute("productMap",productMap);
        redirectAttrs.addFlashAttribute("productMap", productMap);
        return "redirect:/display";
    }

    @GetMapping("/oder2")
    public String saveOder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            log.info(" Đã đăng nhập vs User: " + currentUserName);
        }else{
            log.info("Chưa đăng nhập");
        }

        return "redirect:/display";
    }
}
