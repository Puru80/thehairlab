package com.example.thehairlabrevisited.product;

import com.example.thehairlabrevisited.api.enums.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

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
            if (productRepository.findProductsByCategoryAndServiceName(p.getCategory(),
                    p.getServiceName()).size() >= 1)
                System.out.println("Product Already Exists");
            else {
                System.out.println("Inserting Product " + (++i));
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

    /*
    public void updatePrice(){
        Currency currency = Currency.getInstance("INR");

        List<Product> productList = productRepository.findAll();

        for (Product p : productList) {
            p.setPrice(currency.getSymbol() + p.getPrice());
        }

        productRepository.saveAll(productList);
    }*/

    //TODO: Edit Form
    /*public void updateService(Product product){
        Product p = productRepository.findProductByCategoryAndServiceName(product.getCategory(),
                product.getServiceName());
        if(p!=null) {
            p.setPrice(product.getPrice());
            productRepository.save(p);
        }
        else
            productRepository.save(product);
    }

    public void deleteService(Product product){
        product = productRepository.findProductByCategoryAndServiceName(product.getCategory(),
                product.getServiceName());
        productRepository.delete(product);
    }*/

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
