package com.example.thehairlabrevisited.product;


import com.example.thehairlabrevisited.api.enums.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    private String id;
    private String serviceName;
    private Category category;
    private String price;

    public Product(String serviceName, Category category, String price) {
        this.serviceName = serviceName;
        this.category = category;
        this.price = price;
    }

    public String getCategoryString(){
        return this.category.toString();
    }
}
