package com.example.giohangdemo.service;

import com.example.giohangdemo.domain.Oder;
import com.example.giohangdemo.domain.Product;
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
                log.info("add new", oder1);
            } else {
                AtomicInteger count = new AtomicInteger(oder.getQuantity());
                count.incrementAndGet();
                oder.setQuantity(count.get());

                cart.put(id, oder);
                log.info("add có sẵn", oder.getQuantity());
                log.info(oder.getQuantity()+"So luong");
            }

        } else {
            System.out.println(" Sản phẩm không tồn tại !");
        }

        httpSession.setAttribute("cart", cart);
    }

}
