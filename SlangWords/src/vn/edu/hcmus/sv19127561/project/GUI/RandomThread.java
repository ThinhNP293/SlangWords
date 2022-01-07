package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 1/7/2022 - 4:23 PM
 * Description: ...
 */
public class RandomThread extends Thread{
    JLabel random;
    SlangWords slangWords;

    public RandomThread(JLabel r, SlangWords sw){
        random = r;
        slangWords = sw;
    }

    public void run() {
        try {
            while (true) {
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run()
                    {
                        random.setText(slangWords.random());
                    }
                });
                Thread.sleep(5000);
            }
        } catch (InterruptedException | InvocationTargetException e) {
            System.out.println("Error!");
        }
    }
}
