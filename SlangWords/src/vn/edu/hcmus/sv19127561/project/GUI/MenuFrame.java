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
        // A Label
        JLabel label = new JLabel("Slang Words");
        label.setAlignmentX(CENTER_ALIGNMENT);
        // label.addMouseListener(this);
        // A Grid
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
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
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


        Dimension size2 = new Dimension(600, 500);
        //center.setMaximumSize(size2);
        //center.setPreferredSize(size2);
        //center.setMinimumSize(size2);
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
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == find_by_slang){
            this.dispose();
            new FindSlangFrame(slangWords);
        }
        else if (e.getSource() == find_by_definition){
            this.dispose();
            new FindDefinitionFrame(slangWords);
        }
        else if (e.getSource() == add){
            this.dispose();
            new AddSlangFrame(slangWords);
        }
        else if (e.getSource() == edit){
            this.dispose();
            new EditSlangFrame(slangWords);
        }
        else if (e.getSource() == delete){
            this.dispose();
            new DeleteSlangFrame(slangWords);
        }
    }
}
