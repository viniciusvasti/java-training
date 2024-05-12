package com.vas.java8.intefaces;

import com.vas.mocks.products.Product;

@FunctionalInterface
public interface ProductsFilter {
    boolean filter(Product product);
}
