package com.vas.java8.intefaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.vas.mocks.products.Category;
import com.vas.mocks.products.Product;
import com.vas.mocks.products.Utils;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        var products = Utils.getProducts();
        var categories = Utils.getCategories();
        // Using a class that implements the functional interface
        System.out.println("Laptops Products: " + filterProducts(products, new LaptopsFilter()).size());
        // Using a lambda expression
        System.out.println("Smartphones Products: "
                + filterProducts(products, (p) -> p.category().name().equals("Smartphones")).size());
        System.out.println("Products with price over 1000: "
                + filterProducts(products, (p) -> p.price() > 999).size());
        // Using a predicate
        System.out.println("Products with stock units over 40: "
                + filterProductsPredicate(products, (p) -> p.stockUnits() > 40).size());

        // Combining predicates with and
        Predicate<Product> stockFilter = (p) -> p.stockUnits() > 40;
        Predicate<Product> priceFilter = (p) -> p.price() > 500;
        System.out.println("Products with stock units over 40 and price over 500: "
                + filterProductsPredicate(products, stockFilter.and(priceFilter)).size());

        // Using and combining consumers
        Consumer<Product> printCategory = (p) -> System.out.print(p.category().name() + " - ");
        Consumer<Product> printProduct = (p) -> System.out.println(p.name());
        filterProductsPredicate(products, stockFilter.and(priceFilter)).forEach(printCategory.andThen(printProduct));

        // Using and combining Function
        Function<Product, Double> costOfWholeStock = (p) -> p.price() * p.stockUnits();
        Function<Double, Double> discountOf90Percent = (d) -> d * 0.9;
        for (Product product : products) {
            System.out.println("Cost of whole stock of " + product.name() + " with 90% discount: "
                    + costOfWholeStock.andThen(discountOf90Percent).apply(product));
        }

        // Using Function.identity()
        Map<String, Category> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::name, Function.identity()));
        System.out.println(categoryMap);

        // Using BinaryOperator
        BinaryOperator<Product> sum = (a, b) -> a.price() > b.price() ? a : b;
        Product mostExpensiveProduct = products.stream().reduce(sum).get();
        System.out.println("Most expensive product: " + mostExpensiveProduct.name());

        // Using Method Reference
        categories.forEach(System.out::println);
    }

    public static List<Product> filterProducts(List<Product> products, ProductsFilter filter) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (filter.filter(product)) {
                result.add(product);
            }
        }
        return result;
    }

    public static List<Product> filterProductsPredicate(List<Product> products, Predicate<Product> filter) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (filter.test(product)) {
                result.add(product);
            }
        }
        return result;
    }
}
