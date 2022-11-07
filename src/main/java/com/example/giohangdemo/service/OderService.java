package com.example.giohangdemo.service;

import com.example.giohangdemo.domain.Category;
import com.example.giohangdemo.domain.Oder;
import com.example.giohangdemo.domain.Product;
import com.example.giohangdemo.domain.User;
import com.example.giohangdemo.repository.ICategoryRepository;
import com.example.giohangdemo.repository.IOderRepository;
import com.example.giohangdemo.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class OderService {

    @Autowired
    private IOderRepository iOderRepository;
    @Autowired
    private IProductRepository iProductRepository;


    @Autowired
    private ICategoryRepository iCategoryRepository;


    public void addCart(HttpSession httpSession, int id) {
        HashMap<Integer, Oder> cart;
        var cartraw = httpSession.getAttribute("cart");
        if (cartraw instanceof HashMap) {
            cart = (HashMap<Integer, Oder>) cartraw;
        } else {
            cart = new HashMap<>();
        }
        Optional<Product> optionalProduct = iProductRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Oder oder = cart.get(id);
            if (oder == null) {
                Oder oder1 = new Oder(id);
                oder1.setCreateDate(new Date());
                oder1.setProduct(optionalProduct.get());
                oder1.setQuantity(1);
                cart.put(id, oder1);
                log.info("add new " + oder1.getProduct().getId() + " So luong ban dau " + oder1.getQuantity());
            } else {
                AtomicInteger count = new AtomicInteger(oder.getQuantity());
                count.incrementAndGet();
                oder.setQuantity(count.get());
                cart.put(id, oder);
                log.info("add có sẵn id= " + oder.getProduct().getId() + " So luong " + oder.getQuantity());
            }
        } else {
            log.info(" Sản phẩm không tồn tại !");
        }

        httpSession.setAttribute("cart", cart);
    }

    public void saveCart(HttpSession httpSession, User user) {
        HashMap<Integer, Oder> cart;

        var cartRaw = httpSession.getAttribute("cart");

        if (cartRaw instanceof HashMap) {
            cart = (HashMap<Integer, Oder>) cartRaw;
            Category category = new Category(user);
            iCategoryRepository.save(category);
            cart.forEach((key, value) -> {
                        value.setStatus(1);
                        value.setCategory(category);
                        iOderRepository.save(value);
                    }
            );
        }
    }
    public void deleteProduct(HttpSession httpSession, int id) {
        HashMap<Integer, Oder> cart;

        var cartRaw = httpSession.getAttribute("cart");

        if (cartRaw instanceof HashMap) {
            cart = (HashMap<Integer, Oder>) cartRaw;
            cart.remove(id);
            httpSession.setAttribute("cart", cart);
        } else

            log.error(" Product id= " + id + " khong ton tai");


    }


}
