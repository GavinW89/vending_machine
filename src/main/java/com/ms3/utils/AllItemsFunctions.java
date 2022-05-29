package com.ms3.utils;

import com.ms3.models.Item;

public class AllItemsFunctions {


    public String getPrice(Item[][] allItems, int selectedRow, int columnSelection){

//      Checks to make sure that selected item is not an empty slot || the amount is not 0
        if(allItems[selectedRow][columnSelection] == null || allItems[selectedRow][columnSelection].getAmount() == 0) return "Empty Slot";

        return allItems[selectedRow][columnSelection].getPrice();

    }

    //    Decrease amount of object
    public void decreaseAmount( int row, int column, Item[][] allItems) {

//      Decreases the amount of the Item by 1
        int getAmount = allItems[row][column].getAmount()-1;
        allItems[row][column].setAmount(getAmount);

        System.out.println("You have selected: " + allItems[row][column].getName());
        System.out.println("There are " + allItems[row][column].getAmount() + " left.");

    }

}
