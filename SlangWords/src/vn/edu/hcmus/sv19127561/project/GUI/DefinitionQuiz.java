package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 1/7/2022 - 4:33 PM
 * Description: ...
 */

public class DefinitionQuiz extends JFrame implements ActionListener {
    JButton back;
    JLabel label;
    JLabel definition;
    JButton slang1;
    JButton slang2;
    JButton slang3;
    JButton slang4;


    SlangWords slangWords;

    DefinitionQuiz(SlangWords sw){
        slangWords = sw;
        label = new JLabel();
        label.setText("What is Slang of this Definition?");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);

        String s[] = slangWords.DefinitionQuiz();

        definition = new JLabel();
        definition.setText(s[0]);
        definition.setFont(new Font("Arial", Font.PLAIN, 20));
        definition.setAlignmentX(CENTER_ALIGNMENT);

        slang1 = new JButton(s[1]);
        slang1.addActionListener(this);
        slang1.setSize(30, 50);

        slang2 = new JButton(s[2]);
        slang2.addActionListener(this);
        slang2.setSize(30, 50);

        slang3 = new JButton(s[3]);
        slang3.addActionListener(this);
        slang3.setSize(30, 50);

        slang4 = new JButton(s[4]);
        slang4.addActionListener(this);
        slang4.setSize(30, 50);

        JPanel answer = new JPanel();
        answer.setLayout(new GridLayout(2, 2, 10, 10));
        answer.add(slang1);
        answer.add(slang2);
        answer.add(slang3);
        answer.add(slang4);

        back = new JButton("BACK");
        back.addActionListener(this);
        back.setAlignmentX(CENTER_ALIGNMENT);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(label);
        con.add(definition);
        con.add(answer);
        con.add(back);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("On this day Slang");
        this.setVisible(true);
        this.setSize(700, 200);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slang1){
            if (slangWords.checkSlang(slang1.getText(), definition.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                slang1.setBackground(Color.red);
        }
        else if (e.getSource() == slang2){
            if (slangWords.checkSlang(slang2.getText(), definition.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                slang2.setBackground(Color.red);
        }
        else if (e.getSource() == slang3){
            if (slangWords.checkSlang(slang3.getText(), definition.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                slang3.setBackground(Color.red);
        }
        else if (e.getSource() == slang4){
            if (slangWords.checkSlang(slang4.getText(), definition.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                slang4.setBackground(Color.red);
        }
        else if (e.getSource() == back){
            this.dispose();
            new MenuFrame(slangWords);
        }
    }
}

