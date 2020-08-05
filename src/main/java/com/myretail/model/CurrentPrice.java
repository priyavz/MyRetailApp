package com.myretail.model;

public class CurrentPrice {

    private Double value;
    private String currency_code;

    public CurrentPrice(Double value,String currency_code) {
        this.value = value;
        this.currency_code = currency_code;
    }
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    @Override
    public String toString() {
        return "CurrentPrice{" +
                "value=" + value +
                ", currency_code='" + currency_code + '\'' +
                '}';
    }
}
