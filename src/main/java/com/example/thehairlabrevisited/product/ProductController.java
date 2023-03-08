package com.example.thehairlabrevisited.product;

import com.example.thehairlabrevisited.api.pojo.ProductPOJO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "insert")
    public void insertData(@RequestBody List<Product> productList) {
        productService.insertData(productList);
    }

    @GetMapping({"/", "list"})
    public ModelAndView getAllProducts() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("productMap", productService.getProducts());
        return mav;
    }

    @GetMapping(value = "/update")
    public String home(Model model) {
        List<String> options = productService.getCategories();

        model.addAttribute("categoryOptions", options);
        return "main-page";
    }

    @GetMapping("/service")
    public @ResponseBody List<String> getServices(@RequestParam String category) {
        return productService.getServices(category);
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute("product") ProductPOJO product, Model model) {
        productService.updateService(new Product(
                product.getServiceName(),
                product.getCategory(),
                product.getPrice()
        ));

        return "redirect:/";
    }
}
