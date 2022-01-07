package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 1/5/2022 - 3:12 PM
 * Description: ...
 */
public class Random extends JFrame implements ActionListener {
    JButton back;
    JLabel label;
    JLabel slangOfDay;

    SlangWords slangWords;
    RandomThread r;

    Random(SlangWords sw){
        slangWords = sw;
        label = new JLabel();
        label.setText("On this day slang word");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);

        slangOfDay = new JLabel();
        r = new RandomThread(slangOfDay, slangWords);
        r.start();
        //slangOfDay.setText(slangWords.random());
        slangOfDay.setFont(new Font("Arial", Font.PLAIN, 20));
        slangOfDay.setAlignmentX(CENTER_ALIGNMENT);

        back = new JButton("BACK");
        back.addActionListener(this);
        back.setAlignmentX(CENTER_ALIGNMENT);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(label);
        con.add(slangOfDay);
        con.add(back);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("On this day Slang");
        this.setVisible(true);
        this.setSize(700, 120);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            this.dispose();
            new MenuFrame(slangWords);
        }
    }
}
