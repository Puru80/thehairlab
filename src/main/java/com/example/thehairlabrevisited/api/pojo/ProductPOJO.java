package com.example.thehairlabrevisited.api.pojo;

import com.example.thehairlabrevisited.api.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.Getter;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ProductPOJO {

    private String serviceName;
    private Category category;
    private String price;

    @Override
    public String toString() {
        return new Gson().toJson(ProductPOJO.builder()
                .category(category)
                .serviceName(serviceName)
                .price(price)
                .build());
    }
}
