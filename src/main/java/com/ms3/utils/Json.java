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

//      Set ints to Rows and Columns from input.json
        int rows = config.getRows();
        int columns = config.getColumns();
//      Set to all Item objects
        Item[][] allItems = new Item[rows][columns];

        int counter = 0;
        for(int i = 0; i < rows; i++){
            for(int y = 0; y < columns; y++){

                if(counter < jsonInput.getItems().size()){

                    if(jsonInput.getItems().get(counter) != null){

                        allItems[i][y] = jsonInput.getItems().get(counter);

                    }
                }

                counter ++;
            }
        }

        printF.insertMoney(jsonInput, columns, rows, allItems);
        printF.printTable(jsonInput, rows , columns, allItems);

    }


}



