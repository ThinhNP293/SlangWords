package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 12/20/2021 - 11:44 AM
 * Description: ...
 */
public class AddSlang extends JFrame implements ActionListener {
    private JButton add;
    private JTextField slangTf;
    private JTextField definitionTf;
    private JButton back;

    SlangWords slangWords;

    public AddSlang(SlangWords sw){
        slangWords = sw;
        JLabel slangLb = new JLabel("Slang       ");
        JLabel definitionLb = new JLabel("Definition");

        add = new JButton("ADD");
        add.addActionListener(this);
        back = new JButton("BACK");
        back.addActionListener(this);

        slangTf = new JTextField(20);
        definitionTf = new JTextField(100);

        JLabel label = new JLabel("Input new Slang");

        JPanel slang = new JPanel();
        slang.setLayout(new BoxLayout(slang, BoxLayout.X_AXIS));
        slang.add(slangLb);
        slang.add(slangTf);

        JPanel definition = new JPanel();
        definition.setLayout(new BoxLayout(definition, BoxLayout.X_AXIS));
        definition.add(definitionLb);
        definition.add(definitionTf);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.add(slang);
        form.add(definition);

        JPanel end = new JPanel();
        end.add(back);
        end.add(add);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(label);
        con.add(form);
        con.add(end);


        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Add Slang Word");
        this.setVisible(true);
        this.setSize(300, 150);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add){
            String slang = slangTf.getText();
            String def = definitionTf.getText();
            if (slangWords.checkExist(slang) == false){
                slangWords.add_slang_words(slang, def, 0);
                slangTf.setText("");
                definitionTf.setText("");
                JOptionPane.showMessageDialog(this, "Add Successfully");
            }
            else {
                Object[] options = { "Overwrite", "Duplicate" };
                int n = JOptionPane.showOptionDialog(this,
                        "Slang `" + slang + "` have already exist on  SlangWord  List", "Option",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                if (n == 0) {
                    // Overwrite
                    slangWords.add_slang_words(slang, def, 0);
                    JOptionPane.showMessageDialog(this, "Add successfully!");
                } else if (n == 1) {
                    // Duplicate
                    slangWords.add_slang_words(slang, def, 1);
                    JOptionPane.showMessageDialog(this, "Add successfully!");
                }
            }
        }
        else if (e.getSource() == back){
            this.dispose();
            new MenuFrame(slangWords);
        }
    }

}
