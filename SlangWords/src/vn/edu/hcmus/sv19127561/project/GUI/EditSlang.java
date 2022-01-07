package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 12/22/2021 - 7:20 PM
 * Description: ...
 */
public class EditSlang extends JFrame implements ActionListener, ListSelectionListener {
    JButton back;
    JButton searchSlang;
    JLabel searchLb;
    JTextField searchTf;
    JLabel label;
    String old_definition;

    String col[] = {"No.", "Slang", "Definition"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

    SlangWords slangWords;

    JTable table;

    JLabel slangLb;
    JLabel definitionLb;
    JTextField slangTf;
    JTextField definitionTf;
    JButton editBt;

    JComboBox option;

    EditSlang(SlangWords sw) {
        slangWords = sw;
        label = new JLabel();
        label.setText("Edit Slang Words");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);

        back = new JButton("BACK");
        back.addActionListener(this);
        searchLb = new JLabel("Search");
        searchTf = new JTextField(25);

        String opt[] = {"Slang", "Definition", "Show All"};
        option = new JComboBox(opt);

        searchSlang = new JButton("SEARCH");
        searchSlang.addActionListener(this);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLb);
        searchPanel.add(searchTf);
        searchPanel.add(searchSlang);
        searchPanel.add(option);

        JPanel editSlang = new JPanel();
        JPanel editDefinition = new JPanel();
        slangLb = new JLabel("Slang       ");
        definitionLb = new JLabel("Definition");
        slangTf = new JTextField(30);
        slangTf.setEnabled(false);
        definitionTf = new JTextField(30);
        editBt = new JButton("SAVE");
        editBt.addActionListener(this);
        editBt.setEnabled(false);
        editSlang.add(slangLb);
        editSlang.add(slangTf);
        editDefinition.add(definitionLb);
        editDefinition.add(definitionTf);

        // Table
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this::valueChanged);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(back);
        buttonPanel.add(editBt);


        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(label);
        this.add(searchPanel);
        this.add(editSlang);
        this.add(editDefinition);
        this.add(scrollPane);
        this.add(buttonPanel);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Edit Slang Words");
        this.setVisible(true);
        this.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (table.getRowCount() > 0) {
            int row = table.getSelectedRow();
            if (row >= 0){
                slangTf.setText(table.getModel().getValueAt(row, 1).toString());
                definitionTf.setText(table.getModel().getValueAt(row, 2).toString());
                old_definition = table.getModel().getValueAt(row, 2).toString();
                editBt.setEnabled(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchSlang){
            editBt.setEnabled(false);
            this.clearTable();
            String slang = searchTf.getText();
            searchTf.setText("");

            if (option.getSelectedIndex() == 0 || option.getSelectedIndex() == 1) {
                if (slang.equals("")) {
                    JOptionPane.showMessageDialog(this, "Input field blank");
                } else {
                    String[][] result = new String[0][];
                    if (option.getSelectedIndex() == 0)
                        result = slangWords.search_by_slang(slang);
                    else if (option.getSelectedIndex() == 1)
                        result = slangWords.search_by_definition(slang);
                    if (result.length != 0) {
                        for (int i = 0; i < result.length; i++) {
                            String ss[] = result[i];
                            tableModel.addRow(ss);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Not found!");
                    }
                }
            }
            else {
                String[][] result = slangWords.show_All_Slang();
                for (int i = 0; i < result.length; i++) {
                    String ss[] = result[i];
                    tableModel.addRow(ss);
                }
            }
        }
        else if (e.getSource() == editBt){
            slangWords.edit_slang_words(slangTf.getText(), old_definition, definitionTf.getText());
            JOptionPane.showMessageDialog(this, "Edit successfully!");
        }
        else if (e.getSource() == back){
            this.dispose();
            new MenuFrame(slangWords);
        }

    }
    public void clearTable() {
        int rowCount = tableModel.getRowCount();
        System.out.println(rowCount);
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }
}
