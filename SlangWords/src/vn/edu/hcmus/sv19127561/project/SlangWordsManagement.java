package vn.edu.hcmus.sv19127561.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * vn.edu.hcmus.sv19127561.project
 * Created by Thinh
 * Date 12/18/2021 - 11:40 PM
 * Description: ...
 */
public class SlangWordsManagement {
    //Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    Map<String, SlangWords> map = new HashMap<String, SlangWords>();


    public void load_slang_words(String file_name){
        try {
            File myObj = new File(file_name);
            Scanner myReader = new Scanner(myObj);
            String key_prev = "";
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.indexOf('`') == -1){
                    SlangWords prev = map.get(key_prev);
                    map.put(key_prev, new SlangWords(prev.getSlang(), line));
                }
                else{
                    String[] data = line.split("`");
                    key_prev = data[0];
                    map.put(data[0], new SlangWords(data[0], data[1]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*


    public void load_slang_words(String file_name){
        try {
            File myObj = new File(file_name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                ArrayList<String> definition = new ArrayList<>();
                if (line.indexOf('`') == -1){
                    definition.add(line);
                }
                else{
                    String[] data = line.split("`");
                    definition.add(data[1]);
                    map.put(data[0], definition);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


     */

    void search_by_slang(String slang){
        SlangWords slangWords;
        slangWords = map.get(slang);
        System.out.println(slangWords.getDefinition());
    }

    void search_by_definition(String definition){
        for(Map.Entry<String, SlangWords> entry : map.entrySet())
        {
            if(entry.getValue().getDefinition().contains(definition))
                System.out.println(entry.getKey());
        }
    }

}
