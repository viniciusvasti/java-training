package com.vas.mocks.products;

import java.util.List;

public class Utils {
    private Utils() {
    }

    private static final Category smartPhones = new Category("Smartphones", "Mobile phones with smart features");
    private static final Category laptops = new Category("Laptops", "Portable computers");
    private static final Category tablets = new Category("Tablets", "Portable computers with touch screen");

    public static List<Product> getProducts() {

        return List.of(
                new Product("iPhone 12", "Apple iPhone 12", 799.99, 100, smartPhones),
                new Product("Samsung Galaxy S21", "Samsung Galaxy S21", 699.99, 50, smartPhones),
                new Product("Google Pixel 5", "Google Pixel 5", 699.99, 30, smartPhones),
                new Product("OnePlus 9 Pro", "OnePlus 9 Pro", 899.99, 20, smartPhones),
                new Product("Xiaomi Mi 11", "Xiaomi Mi 11", 699.99, 40, smartPhones),
                new Product("MacBook Pro", "Apple MacBook Pro", 1299.99, 30, laptops),
                new Product("Dell XPS 13", "Dell XPS 13", 1199.99, 20, laptops),
                new Product("HP Spectre x360", "HP Spectre x360", 999.99, 10, laptops),
                new Product("Microsoft Surface Laptop 4", "Microsoft Surface Laptop 4", 999.99, 15, laptops),
                new Product("iPad Pro", "Apple iPad Pro", 999.99, 40, tablets),
                new Product("Samsung Galaxy Tab S7", "Samsung Galaxy Tab S7", 799.99, 30, tablets),
                new Product("Amazon Fire HD 10", "Amazon Fire HD 10", 149.99, 50, tablets));
    }

    public static List<Category> getCategories() {
        return List.of(smartPhones, laptops, tablets);
    }
}
