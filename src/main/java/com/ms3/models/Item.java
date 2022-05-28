package com.ms3.models;

public class Item {
    private String name;
    private int amount;
    private String price;


    public Item(){
        super();
    }

    public Item(String name, int amount, String price) {
        super();
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
