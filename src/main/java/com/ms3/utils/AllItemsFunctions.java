package com.ms3.utils;

import com.ms3.models.Item;

public class AllItemsFunctions {


    public String getPrice(Item[][] allItems, int charRowSelection, int columnSelection){

        if(allItems[charRowSelection][columnSelection] == null) return "Empty Slot";

        return allItems[charRowSelection][columnSelection].getPrice();

    }

    //    Decrease amount of object
    public void decreaseAmount( int row, int column, Item[][] allItems) {

        int getAmount = allItems[row][column].getAmount()-1;
        allItems[row][column].setAmount(getAmount);

        System.out.println("You have selected: " + allItems[row][column].getName());
        System.out.println("There are " + allItems[row][column].getAmount() + " left.");

    }

}
