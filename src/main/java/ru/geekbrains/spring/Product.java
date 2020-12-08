package ru.geekbrains.spring;

public class Product {
    private long id;
    private String name;
    private int sale;
    private int count;

    public Product(long id, String name, int sale, int count) {
        this.id = id;
        this.name = name;
        this.sale = sale;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return String.format("id: %-10d | Название: %-20s | Цена: %-10d рублей\n", id, name, sale, count );
    }
}
