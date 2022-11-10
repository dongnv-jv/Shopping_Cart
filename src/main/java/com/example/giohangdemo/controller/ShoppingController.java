package com.example.giohangdemo.controller;

import com.example.giohangdemo.domain.Product;
import com.example.giohangdemo.repository.IProductRepository;
import com.example.giohangdemo.service.ShoppingService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class ShoppingController {

    @Autowired

    private IProductRepository iProductRepository;
    @Autowired
    private ShoppingService shoppingService;



    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Product.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Product> products = csvToBean.parse();

                products.forEach(product -> {
                    product.setImages("/static/images/rin.jpg");
                });

                iProductRepository.saveAll(products);
                // TODO: save users in DB?

                // save users list on model
//                model.addAttribute("users", products);
//                model.addAttribute("status", true);

            } catch (Exception ex) {
//                model.addAttribute("message", "An error occurred while processing the CSV file.");
//                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }

    @PostMapping("/update")
    public ResponseEntity<?> update() {

        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setImages("/static/images/rin.jpg");
            product.setId(i);
            iProductRepository.save(product);
        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/display")
    public String getAllProduct(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        PageRequest pageRequest = PageRequest.of(page, 8);
        Page<Product> listProduct = shoppingService.getAllProduct(pageRequest.previousOrFirst());
//        listProduct.forEach(ddd->{
//            log.info(ddd.getCost()+"getCost");
//            log.info(ddd.getImages()+ " get Imaged");
//            log.info(ddd.getName()+ " get Name");
//            log.info(ddd.getDecription() + " get Decription");
//            log.info(ddd.getId()+"");
//        });

        List<Integer> listPage = IntStream.range(1, listProduct.getTotalPages())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("listPage", listPage);
        model.addAttribute("listProduct", listProduct);
        return "index2";
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete() {

        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
//            product.setImages("/static/images/rin.jpg");
            product.setId(i);

            if (iProductRepository.findById(i).isPresent()) {
                iProductRepository.delete(product);
            }

//            iProductRepository.save(product);
        }
        return ResponseEntity.ok("ok");
    }

}
