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
    private Map<String, ArrayList<String>> map = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();



    public SlangWords(){

    }


    public void addHistory(String str){
        history.add(0, str);
    }

    public ArrayList<String> getHistory(){ return history; }

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

    public String[][] show_All_Slang(){
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> def = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
            ArrayList<String> definitionList = entry.getValue();
            for (int i = 0; i < definitionList.size(); i++) {
                key.add(entry.getKey());
                def.add(definitionList.get(i));
            }
        }
        String result[][] = new String[key.size()][3];

        for (int i = 0; i < key.size(); i++) {
            result[i][0] = String.valueOf(i + 1);
            result[i][1] = key.get(i);
            result[i][2] = def.get(i);
        }

        return result;
    }

    public String[][] search_by_slang(String slang){
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> def = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            ArrayList<String> definition;
            if (entry.getKey().toLowerCase().contains(slang.toLowerCase())) {
                definition = entry.getValue();
                for (String s : definition) {
                    key.add(entry.getKey());
                    def.add(s);
                }
            }
        }
        String result[][] = new String[key.size()][3];

        for (int i = 0; i < key.size(); i++) {
            result[i][0] = String.valueOf(i + 1);
            result[i][1] = key.get(i);
            result[i][2] = def.get(i);
        }

        return result;
    }

    public String[][] search_by_definition(String definition){
        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> def = new ArrayList<>();
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet())
        {
            ArrayList<String> definitionList = entry.getValue();
            for (int i = 0; i < definitionList.size(); i++) {
                if (definitionList.get(i).toLowerCase().contains(definition.toLowerCase())) {
                    key.add(entry.getKey());
                    def.add(definitionList.get(i));
                }
            }
        }
        String result[][] = new String[key.size()][3];

        for (int i = 0; i < key.size(); i++) {
            result[i][0] = String.valueOf(i + 1);
            result[i][1] = key.get(i);
            result[i][2] = def.get(i);
        }

        return result;
    }

    public void add_slang_words(String slang, String definition, int opt){
        ArrayList<String> definitionList = new ArrayList<>();
        if (opt == 1) { //Duplicate
            definitionList = map.get(slang);
        }
        definitionList.add(definition);
        map.put(slang, definitionList);
    }

    public void edit_slang_words(String slang, String old_definition, String new_definition){
        ArrayList<String> definitionList;
        definitionList = map.get(slang);
        for (int i = 0; i < definitionList.size(); i++){
            if (definitionList.get(i).equals(old_definition)){
                definitionList.set(i, new_definition);
            }
        }
    }

    public void delete_slang_words(String slang, String definition){
        ArrayList<String> definitionList;
        definitionList = map.get(slang);
        for (int i = 0; i < definitionList.size(); i++){
            if (definitionList.get(i).equals(definition)){
                definitionList.remove(i);
            }
        }
        if (definitionList.size() == 0) map.remove(slang);
        else map.put(slang, definitionList);
    }

    public String random() {
        Random generator = new Random();
        int rand = generator.nextInt(map.size());
        int index = 0;
        String result = new String();
        for (String key : map.keySet()) {
            if (index == rand) {
                result = "Slang: " + key + ". Definition: " + map.get(key).get(0);
                break;
            }
            index++;
        }
        return result;
    }

    public String[] SlangQuiz() {
        Random generator = new Random();
        int rand = generator.nextInt(map.size());
        int index = 0;
        String result[] = new String[5];
        for (String key : map.keySet()) {
            if (index == rand) {
                result[0] = key;
                int correct = generator.nextInt(4) + 1;
                result[correct] = map.get(key).get(0);
                for (int i = 1; i < 5; i++){
                    if (i != correct) {
                        int index1 = 0;
                        rand = generator.nextInt(map.size());
                        for (String wrongSlang : map.keySet()) {
                            if (index1 == rand) {
                                result[i] = map.get(wrongSlang).get(0);
                            }
                            index1++;
                        }

                    }
                }
                break;
            }
            index++;
        }
        return result;
    }

    public boolean checkSlang(String slang, String definition){
        if (map.get(slang) != null){
            if (map.get(slang).get(0).equals(definition))
                return true;
        }
        return false;
    }
}
