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
public class SlangWords {
    private Map<String, ArrayList<String>> map = new TreeMap<>();
    private ArrayList<String> history = new ArrayList<>();

    //Map<String, SlangWords> map = new HashMap<String, SlangWords>();

    /*
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
     */

    public void load_slang_words(String file_name){
        try {
            File myObj = new File(file_name);
            Scanner myReader = new Scanner(myObj);
            String key_prev = "";
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                ArrayList<String> definition = new ArrayList<>();
                if (line.indexOf('`') == -1){
                    definition = map.get(key_prev);
                    definition.add(line);
                    map.put(key_prev, definition);
                }
                else{
                    String[] data = line.split("`");
                    key_prev = data[0];
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

    public String[][] search_by_slang(String slang){
        ArrayList<String> definition = map.get(slang);
        String result[][] = new String[definition.size()][3];
        for(int i = 0; i < definition.size(); i++){
            result[i][0] = String.valueOf(i);
            result[i][1] = slang;
            result[i][2] = definition.get(i);
        }
        return result;
    }

    public void search_by_definition(String definition){
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet())
        {
            if(entry.getValue().contains(definition))
                System.out.println(entry.getKey());
        }
    }

    public void add_slang_words(){

    }
}
