package com.example.thehairlabrevisited.product;

import com.example.thehairlabrevisited.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductsByCategoryAndServiceName(Category category, String serviceName);

    Product findProductByCategoryAndServiceName(Category category, String serviceName);
}
