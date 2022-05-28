package com.ms3.models;

import java.util.List;

public class JsonInput {
    private Config config;
    private List<Item> items;


    public JsonInput(){
        super();
    }

    public JsonInput(List<Item> items, Config config) {
        this.items = items;
        this.config = config;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
