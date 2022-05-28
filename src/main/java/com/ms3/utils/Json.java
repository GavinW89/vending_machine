package com.ms3.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.ms3.models.Config;
import com.ms3.models.Item;
import com.ms3.models.JsonInput;

public class Json {
    List<Item> listItems;
    PrintF printF = new PrintF();
    public void JsonInit() throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file and convert to java object
        InputStream fileInputStream = new FileInputStream("src/main/resources/input.json");
        JsonInput jsonInput = mapper.readValue(fileInputStream, JsonInput.class);
        fileInputStream.close();

        Config config = jsonInput.getConfig();
        
        int rows = config.getRows();
        int columns = config.getColumns();


        String[][] itemsNameList = new String[rows][columns];
        Item[][] allItems = new Item[rows][columns];
        String[][] locationLetterList = new String[rows][columns];
        Integer[][] locationNumberList = new Integer[rows][columns];
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        int counter = 0;
        for(int i = 0; i < rows; i++){
            for(int y = 0; y < columns; y++){
                locationNumberList[i][y] = y;
                locationLetterList[i][y] = alphabet[i];
                if(counter < jsonInput.getItems().size()){

                    if(jsonInput.getItems().get(counter) != null){
                        String display = jsonInput.getItems().get(counter).getName() + " " + jsonInput.getItems().get(counter).getPrice();
                        itemsNameList[i][y] = display;


                        allItems[i][y] = jsonInput.getItems().get(counter);


                    }
                }else{

                    itemsNameList[i][y] = "Empty Slot";
                }

                counter ++;
            }
        }

        printF.insertMoney(jsonInput, columns, rows, itemsNameList, locationLetterList, locationNumberList, allItems);
        printF.printTable(jsonInput, itemsNameList, locationLetterList, locationNumberList, rows , columns, allItems);

    }


}



