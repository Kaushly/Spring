package ru.geekbrains.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository implements Repository {
    private List<Product> products;

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }


    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(111L, "Яблоки", 79,10));
        products.add(new Product(222L, "Бананы", 64,10));
        products.add(new Product(333L, "Киви", 77,10));
        products.add(new Product(444L, "Капуста", 24,10));
        products.add(new Product(555L, "Сосиски", 180,10));
        products.add(new Product(666L, "Котлета", 200,10));
        products.add(new Product(777L, "Ролтон", 17,10));
        products.add(new Product(888L, "Чай", 154,10));
        products.add(new Product(999L, "Хлеб", 65,10));
    }

}
