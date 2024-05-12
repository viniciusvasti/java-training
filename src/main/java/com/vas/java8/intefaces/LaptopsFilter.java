package com.vas.java8.intefaces;

import com.vas.mocks.products.Product;

public class LaptopsFilter implements ProductsFilter {
    @Override
    public boolean filter(Product product) {
        return product.category().name().equals("Laptops");
    }
}
