package com.myretail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection="productprice")
public class ProductPriceEntity {

    @Id
    private String id;
    private Double price;
    private String currencyCode;

    public ProductPriceEntity(String id, Double price, String currencyCode) {
        this.id = id;
        this.price = price;
        this.currencyCode=currencyCode;
    }
    public String getId() {
        return id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceEntity that = (ProductPriceEntity) o;
        return Objects.equals(price, that.price) &&
                Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currencyCode);
    }

    @Override
    public String toString() {
        return "ProductPriceEntity{" +
                "price='" + price + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
