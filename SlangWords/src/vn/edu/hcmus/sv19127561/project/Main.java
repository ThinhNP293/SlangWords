package vn.edu.hcmus.sv19127561.project;

import vn.edu.hcmus.sv19127561.project.GUI.MenuFrame;

public class Main {
    public static void main(String[] args) {
        SlangWords slangWords = new SlangWords();
        slangWords.load_slang_words("slang.txt");
        new MenuFrame(slangWords);
    }
}
