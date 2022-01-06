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
    JButton find_by_slang;
    JButton find_by_definition;
    JButton history;
    JButton add;
    JButton edit;
    JButton delete;
    JButton reset;
    JButton random;
    JButton slang_quiz;
    JButton definition_quiz;

    SlangWords slangWords;

    public MenuFrame(SlangWords sw) {
        slangWords = sw;

        JLabel label = new JLabel("Slang Words Dictionary");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);


        find_by_slang = new JButton("1. Find Definition by Slang");
        find_by_slang.addActionListener(this);

        find_by_definition = new JButton("2. Find Slang by Definition");
        find_by_definition.addActionListener(this);

        history = new JButton("3. History");
        history.addActionListener(this);

        add = new JButton("4. Add Slang Word");
        add.addActionListener(this);

        edit = new JButton("5. Edit Slang Word");
        edit.addActionListener(this);

        delete = new JButton("6. Delete Slang Word");
        delete.addActionListener(this);

        reset = new JButton("7. Reset Slang Word");
        reset.addActionListener(this);

        random = new JButton("8. Random Slang Word");
        random.addActionListener(this);

        slang_quiz = new JButton("9. Slang Quiz");
        slang_quiz.addActionListener(this);

        definition_quiz = new JButton("10. Definition Quiz");
        definition_quiz.addActionListener(this);

        JPanel center = new JPanel();
        // panelCenter.setBackground(Color.gray);
        center.setLayout(new GridLayout(10, 1, 20, 20));
        center.add(find_by_slang);
        center.add(find_by_definition);
        center.add(history);
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
        if (e.getSource() == find_by_slang){
            this.dispose();
            new FindSlang(slangWords);
        }
        else if (e.getSource() == find_by_definition){
            this.dispose();
            new FindDefinition(slangWords);
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
                slangWords.load_slang_words("slang.txt");
                JOptionPane.showMessageDialog(this, "Reset successfully!");
            }
        }

    }
}
