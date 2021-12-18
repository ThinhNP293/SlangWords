package vn.edu.hcmus.sv19127561.project;

public class Main {
    public static void main(String[] args) {
        SlangWordsManagement swm = new SlangWordsManagement();
        swm.load_slang_words("slang.txt");
        swm.search_by_slang(":)");
        swm.search_by_definition("Happy");
    }
}
