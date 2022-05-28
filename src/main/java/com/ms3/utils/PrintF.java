package com.ms3.utils;

import com.ms3.models.Item;
import com.ms3.models.JsonInput;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class PrintF {

    String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    AllItemsFunctions allItemsFunctions = new AllItemsFunctions();

    Scanner scanner = new Scanner(System.in);

    double money = 0;
    DecimalFormat df = new DecimalFormat("##.##");
    boolean firstItem = false;

    public void printTable(JsonInput jsonInput, String[][] itemList, String[][] locationLetterList, Integer[][] locationNumberList, int rows, int columns, Item[][] allItems) {

        // Print jsonInput Objects


        String leftAlignFormat = " %-27s |";
        String vertSeparator = "%n+----+-----------------------------+-----------------------------+-----------------------------+-----------------------------+-----------------------------+-----------------------------+-----------------------------+-----------------------------+%n";
        String horzSeparator = "  |";
        String numberHeaders = "|    |";

//        Add column headers to a string
        for (int x = 0; x < columns; x++) {
            numberHeaders = numberHeaders  + "                            " + locationNumberList[1][x]+ "|";
        }
//      Print column header
        System.out.format(vertSeparator);
        System.out.format(numberHeaders);

//        Print items with letter locators

        boolean locationPrinted = false;
        String availableRows = "";
        int counter = 0;
        for (int x = 0; x < rows; x++) {
            if (locationPrinted) {
                locationPrinted = false;
            }
            System.out.format(vertSeparator);
            for (int y = 0; y < columns; y++) {
                if (!locationPrinted) {
                    System.out.format("| " + locationLetterList[x][y] + horzSeparator);
                    availableRows += locationLetterList[x][y];
                    locationPrinted = true;
                }
                if(counter < jsonInput.getItems().size()){
                        if(allItems[x][y].getAmount() == 0){
                            System.out.format(leftAlignFormat, "Empty Slot" + horzSeparator);
                        }else{
                            System.out.format(leftAlignFormat, itemList[x][y]);
                        }

                }else{
                    System.out.format(leftAlignFormat, itemList[x][y]);
                }


                counter ++;

            }
        }
        System.out.format(vertSeparator);
        selectItem(jsonInput, columns, rows, availableRows, itemList, locationLetterList, locationNumberList, allItems);
    }


    public void insertMoney(JsonInput jsonInput, int columns, int rows, String[][] itemList, String[][] locationLetterList, Integer[][] locationNumberList, Item[][] allItems){

        boolean inserting = true;


        System.out.println("Please Insert Cash");
        System.out.println("Accepting Bills: $1, $5, $10; Coins: 5¢, 10¢, 25¢");
        System.out.print("\nType 1 for $1\nType 2 for $5\nType 3 for $10\nType 4 for 5¢\nType 5 for 10¢\nType 6 for 25¢\nType 0 if you are out of cash\n");

        while(inserting){

            int input = scanner.nextInt();

            switch (input){

                case 1:
                    System.out.println("Added $1 to available cash");
                    money += 1;
                    break;
                case 2:
                    System.out.println("Added $5 to available cash");
                    money += 5;
                    break;
                case 3:
                    System.out.println("Added $10 to available cash");
                    money += 10;
                    break;
                case 4:
                    System.out.println("Added 5¢ to available cash");
                    money += .05;
                    break;
                case 5:
                    System.out.println("Added 10¢ to available cash");
                    money += .10;
                    break;
                case 6:
                    System.out.println("Added 25¢ to available cash");
                    money += .25;
                    break;
                case 0:
                    inserting = false;
                    break;
            }

        }

        while(firstItem){
            printTable(jsonInput, itemList, locationLetterList, locationNumberList, rows, columns, allItems);
        }

        System.out.println("You available cash is $" + money);
    }

    public void selectItem(JsonInput jsonInput, int columns, int rows, String availableRows,String[][] itemList, String[][] locationLetterList, Integer[][] locationNumberList, Item[][] allItems){

        System.out.println("Please select the column");
        int columnSelection = scanner.nextInt();
        System.out.println("Please select the row");
        String rowSelection = scanner.next();

//        Take first char of userInput
        int charRowSelection = Arrays.asList(alphabet).indexOf(rowSelection);


        String itemPrice = allItemsFunctions.getPrice(allItems, charRowSelection,columnSelection);
//        Set price variable to a double
        if(itemPrice == "Empty Slot"){
            System.out.println("You chose an empty slot, please select a slot with product.");
            printTable(jsonInput, itemList, locationLetterList, locationNumberList, rows, columns, allItems);
        }

        String stringPrice = itemPrice.substring(1);
        double newPrice = Double.parseDouble(stringPrice);

        if(money > newPrice){
            allItemsFunctions.decreaseAmount(charRowSelection, columnSelection, allItems);
            subtractPrice(jsonInput, allItems[charRowSelection][columnSelection].getPrice(), allItems, itemList, locationLetterList, locationNumberList, rows, columns);
            firstItem = true;
        }else{
            System.out.println("You do not have enough money for this item.");
            System.out.println("Please insert more money or pick another item.");
            System.out.println("Type 1 to insert more money\nType 2 to select another item\nType 3 to take change and end");
            int input = scanner.nextInt();
            switch (input){
                case 1:
                    insertMoney(jsonInput, columns,  rows, itemList,  locationLetterList,  locationNumberList, allItems);
                    printTable(jsonInput, itemList, locationLetterList, locationNumberList, rows, columns, allItems);
                    break;
                case 2:
                    printTable(jsonInput, itemList, locationLetterList, locationNumberList, rows, columns, allItems);
                    break;
                case 3:
                    getChange();
                    break;
            }
        }



    }

//    Give Change method
    public void subtractPrice(JsonInput jsonInput, String price, Item[][] allItems, String[][] itemsNameList, String[][] locationLetterList, Integer[][] locationNumberList, int rows, int columns){

//        Set price variable to a double
        String stringPrice = price.substring(1);
        double newPrice = Double.parseDouble(stringPrice);

//        Subtract price of item from money
        money -= newPrice;

        System.out.println("Your remaining credit is $" + df.format(money));

        buyAnotherItem( jsonInput, allItems,  itemsNameList, locationLetterList,  locationNumberList, rows, columns);

    }

//    Ask user if they would like to buy another item, add money, or take change
    public void buyAnotherItem(JsonInput jsonInput, Item[][] allItems, String[][] itemsNameList, String[][] locationLetterList, Integer[][] locationNumberList, int rows, int columns){

        System.out.format("%nType 1 to buy another item%nType 2 to insert more money%nType 3 to get your change and end%n");

        int input = scanner.nextInt();

        switch (input){

            case 1:
                printTable(jsonInput, itemsNameList, locationLetterList, locationNumberList, rows, columns, allItems);
                break;
            case 2:
                insertMoney(jsonInput, columns,  rows, itemsNameList,  locationLetterList,  locationNumberList, allItems);
                break;
            case 3:
                getChange();
                break;
        }

    }

    public void getChange(){

        System.out.println(df.format(money) + " is your remaining change. \nHave a Wonderful Day!");

    }

}
