package com.myretail.model;

public class ProductData {

    private String id;
    private String name;
    private CurrentPrice current_price;

    public ProductData(String id, String name, CurrentPrice current_price) {
        this.id = id;
        this.name = name;
        this.current_price = current_price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_price(CurrentPrice current_price) {
        this.current_price = current_price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CurrentPrice getCurrent_price() {
        return current_price;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", current_price=" + current_price +
                '}';
    }
}
