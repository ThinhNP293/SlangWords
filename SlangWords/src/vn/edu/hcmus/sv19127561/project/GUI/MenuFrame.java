package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 12/19/2021 - 4:15 PM
 * Description: ...
 */
public class MenuFrame extends JFrame implements ActionListener {
    JButton search;
    JButton add;
    JButton edit;
    JButton delete;
    JButton reset;
    JButton random;
    JButton slang_quiz;
    JButton definition_quiz;

    public static SlangWords slangWords;

    public MenuFrame(SlangWords sw) {
        slangWords = sw;

        JLabel label = new JLabel("Slang Words Dictionary");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);


        search = new JButton("SEARCH");
        search.addActionListener(this);

        add = new JButton("ADD SLANG WORD");
        add.addActionListener(this);

        edit = new JButton("EDIT SLANG WORD");
        edit.addActionListener(this);

        delete = new JButton("DELETE SLANG WORD");
        delete.addActionListener(this);

        reset = new JButton("RESET SLANG WORD LIST");
        reset.addActionListener(this);

        random = new JButton("8. Random Slang Word");
        random.addActionListener(this);

        slang_quiz = new JButton("SLANG QUIZ");
        slang_quiz.addActionListener(this);

        definition_quiz = new JButton("DEFINITION QUIZ");
        definition_quiz.addActionListener(this);

        JPanel center = new JPanel();
        // panelCenter.setBackground(Color.gray);
        center.setLayout(new GridLayout(10, 1, 0, 10));
        center.add(search);
        center.add(add);
        center.add(edit);
        center.add(delete);
        center.add(reset);
        center.add(random);
        center.add(slang_quiz);
        center.add(definition_quiz);


        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 30)));
        con.add(center);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Menu Window");
        this.setVisible(true);
        this.setSize(500, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            this.dispose();
            new FindSlang(slangWords);
        }
        else if (e.getSource() == add){
            this.dispose();
            new AddSlang(slangWords);
        }
        else if (e.getSource() == edit){
            this.dispose();
            new EditSlang(slangWords);
        }
        else if (e.getSource() == delete){
            this.dispose();
            new DeleteSlang(slangWords);
        }
        else if (e.getSource() == random){
            this.dispose();
            new Random(slangWords);
        }
        else if (e.getSource() == slang_quiz){
            this.dispose();
            new SlangQuiz(slangWords);
        }
        else if (e.getSource() == reset){
            int n = JOptionPane.showConfirmDialog(this, "Do you really want to reset Slang Word?", "Confirm Reset",
                    JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                slangWords.load_slang_words("original-slang.txt");
                JOptionPane.showMessageDialog(this, "Reset successfully!");
            }
        }

    }
}
