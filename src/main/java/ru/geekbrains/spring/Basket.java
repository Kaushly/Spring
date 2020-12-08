package ru.geekbrains.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Basket {

    private List<Product> buys;

    public Basket(List<Product> buys) {
        this.buys = buys;
    }

    public List<Product> getBuys() {
        return buys;
    }

    public void addBasket(Product product) {
        buys.add(product);
    }

    public void deleteBasket(Product product) {
        buys.remove(product);
    }
}
