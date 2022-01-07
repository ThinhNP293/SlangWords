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
public class DeleteSlang extends JFrame implements ActionListener, ListSelectionListener {
    JButton back;
    JButton searchSlang;
    JButton searchDefinition;
    JButton showAll;
    JButton delete;
    JLabel searchLb;
    JTextField searchTf;
    JLabel label;

    String col[] = {"No.", "Slang", "Definition"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

    SlangWords slangWords;

    JTable table;
    String slang;
    String definition;

    JComboBox option;

    DeleteSlang(SlangWords sw){
        slangWords = sw;
        label = new JLabel();
        label.setText("Delete Slang Words");
        label.setFont(new Font("Arial", Font.PLAIN, 25));
        label.setAlignmentX(CENTER_ALIGNMENT);

        back = new JButton("BACK");
        back.addActionListener(this);
        delete = new JButton("DELETE");
        delete.addActionListener(this);
        delete.setEnabled(false);
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


        // Table
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this::valueChanged);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(back);
        buttonPanel.add(delete);

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        //this.add(searchPanel, BorderLayout.PAGE_START);
        //this.add(scrollPane, BorderLayout.CENTER);
        //this.add(buttonPanel, BorderLayout.PAGE_END);
        this.add(label);
        this.add(searchPanel);
        this.add(scrollPane);
        this.add(buttonPanel);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Delete Slang Frame");
        this.setVisible(true);
        this.setSize(600, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchSlang){
            delete.setEnabled(false);
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
        else if (e.getSource() == delete){
            Object[] options = { "Cancel", "Delete" };
            int n = JOptionPane.showOptionDialog(this,
                    "Do you want to delete this slang?", "Confirm Delete",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (n == 1) {
                System.out.println(slang + "  " + definition);
                slangWords.delete_slang_words(slang, definition);
                tableModel.removeRow(table.getSelectedRow());
            }
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (table.getRowCount() > 0) {
            int row = table.getSelectedRow();
            if (row >= 0){
                slang = table.getModel().getValueAt(row, 1).toString();
                definition = table.getModel().getValueAt(row, 2).toString();
                delete.setEnabled(true);
            }
        }
    }
}
