package com.example.thehairlabrevisited.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "insert")
    public void insertData(@RequestBody List<Product> productList){
        productService.insertData(productList);
    }

    @GetMapping({"/", "list"})
    public ModelAndView getAllProducts(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("productMap", productService.getProducts());
        return mav;
    }
}
