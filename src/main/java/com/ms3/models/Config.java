package com.ms3.models;

public class Config {

    private int rows;
    private int columns;

    public Config(){
        super();
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = Integer.valueOf(columns);
    }
}
