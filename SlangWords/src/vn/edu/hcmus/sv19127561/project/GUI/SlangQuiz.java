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
public class SlangQuiz extends JFrame implements ActionListener {
    JButton back;
    JLabel label;
    JLabel slang;
    JButton definition1;
    JButton definition2;
    JButton definition3;
    JButton definition4;


    SlangWords slangWords;

    SlangQuiz(SlangWords sw){
        slangWords = sw;
        label = new JLabel();
        label.setText("On this day slang word");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);

        String s[] = slangWords.SlangQuiz();

        slang = new JLabel();
        slang.setText(s[0]);
        slang.setFont(new Font("Arial", Font.PLAIN, 20));
        slang.setAlignmentX(CENTER_ALIGNMENT);

        definition1 = new JButton(s[1]);
        definition1.addActionListener(this);
        definition1.setSize(30, 50);

        definition2 = new JButton(s[2]);
        definition2.addActionListener(this);
        definition2.setSize(30, 50);

        definition3 = new JButton(s[3]);
        definition3.addActionListener(this);
        definition3.setSize(30, 50);

        definition4 = new JButton(s[4]);
        definition4.addActionListener(this);
        definition4.setSize(30, 50);

        JPanel answer = new JPanel();
        answer.setLayout(new GridLayout(2, 2, 10, 10));
        answer.add(definition1);
        answer.add(definition2);
        answer.add(definition3);
        answer.add(definition4);

        back = new JButton("BACK");
        back.addActionListener(this);
        back.setAlignmentX(CENTER_ALIGNMENT);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(label);
        con.add(slang);
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
        if (e.getSource() == definition1){
            if (slangWords.checkSlang(slang.getText(), definition1.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                JOptionPane.showMessageDialog(this, "Wrong Answer.");
        }
        else if (e.getSource() == definition2){
            if (slangWords.checkSlang(slang.getText(), definition2.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                JOptionPane.showMessageDialog(this, "Wrong Answer.");
        }
        else if (e.getSource() == definition3){
            if (slangWords.checkSlang(slang.getText(), definition3.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                JOptionPane.showMessageDialog(this, "Wrong Answer.");
        }
        else if (e.getSource() == definition4){
            if (slangWords.checkSlang(slang.getText(), definition4.getText()) == true)
                JOptionPane.showMessageDialog(this, "Correct Answer.");
            else
                JOptionPane.showMessageDialog(this, "Wrong Answer.");
        }
        else if (e.getSource() == back){
            this.dispose();
            new MenuFrame(slangWords);
        }
    }
}
