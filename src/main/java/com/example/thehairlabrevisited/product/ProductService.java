package com.example.thehairlabrevisited.product;

import com.example.thehairlabrevisited.api.enums.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final List<Category> categories = List.of(Category.HAIR,
            Category.COLOUR_TEXTURE,
            Category.LOREAL_HAIR_TREATMENTS,
            Category.LOTUS_FACIALS,
            Category.NAIL_SERVICES,
            Category.THREADING,
            Category.BLEACH_SKIN_POLISH,
            Category.WAXING,
            Category.BODY_ESSENTIALS
    );

    public void insertData(List<Product> productList) {
        int i = 0;
        for (Product p : productList) {
            if (!productRepository.findProductsByCategoryAndServiceName(p.getCategory(),
                    p.getServiceName()).isEmpty())
                log.info("Product Already Exists");
            else {
                log.info("Inserting Product " + (++i));
                Currency currency = Currency.getInstance("INR");
                p.setPrice(currency.getSymbol() + p.getPrice());
                productRepository.insert(p);
            }
        }

    }

    public Map<Category, List<Product>> getProducts() {
        Map<Category, List<Product>> map = new LinkedHashMap<>();
        for (Category c : categories) {
            map.put(c, productRepository.findProductsByCategory(c));
        }

        return map;
    }

    //TODO: Edit Form
    public void updateService(Product product) {
        log.info("Service: {}", product.getServiceName());

        Product p = productRepository.findProductByCategoryAndServiceName(product.getCategory(),
                product.getServiceName());

        Currency currency = Currency.getInstance("INR");

        if (p != null) {
            log.info("Saving product: {}", p);

            p.setPrice(currency.getSymbol() + product.getPrice());
            productRepository.save(p);

        } else
            productRepository.save(product);
    }

    //TODO: Add delete functionality

    public List<String> getCategories() {
        List<String> stringCategories = new ArrayList<>();

        for (Category c : categories)
            stringCategories.add(c.name());

        return stringCategories;
    }

    public List<String> getServices(String category) {

        return productRepository.findProductsByCategory(Category.valueOf(category))
                .stream()
                .map(product -> product.getServiceName())
                .toList();
    }
}
