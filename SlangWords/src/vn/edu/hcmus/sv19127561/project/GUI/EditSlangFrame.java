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
public class EditSlangFrame extends JFrame implements ActionListener, TableModelListener, ListSelectionListener {
    JButton back;
    JButton search;
    JLabel searchLb;
    JTextField searchTf;
    JLabel label;

    String col[] = {"No.", "Slang", "Definition"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

    SlangWords slangWords;

    JTable table;
    String old_definition;

    EditSlangFrame(SlangWords sw){
        slangWords = sw;
        label = new JLabel("Edit Slang Words");
        back = new JButton("BACK");
        back.addActionListener(this);
        search = new JButton("SEARCH");
        search.addActionListener(this);
        searchLb = new JLabel("Slang");
        searchTf = new JTextField(20);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLb);
        searchPanel.add(searchTf);
        searchPanel.add(search);

        // Table
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getModel().addTableModelListener(this);
        table.getSelectionModel().addListSelectionListener(this::valueChanged);

        JScrollPane scrollPane = new JScrollPane(table);



        this.setLayout(new BorderLayout());
        this.add(searchPanel, BorderLayout.PAGE_START);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(back, BorderLayout.PAGE_END);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Search By Slang");
        this.setVisible(true);
        this.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            this.clearTable();
            String slang = searchTf.getText();
            slangWords.addHistory(slang);
            String[][] result = slangWords.search_by_slang(slang);
            if (result != null) {
                for (int i = 0; i < result.length; i++) {
                    String ss[] = result[i];
                    tableModel.addRow(ss);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Not found!");
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
    public void tableChanged(TableModelEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if (row >= 0 && table.getRowCount() > 0) {
            slangWords.edit_slang_words((String) table.getValueAt(row, 1), old_definition, (String) table.getValueAt(row, 2));
            JOptionPane.showMessageDialog(this, "Edit Successfully!");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (table.getRowCount() > 0) {
            int row = table.getSelectedRow();
            if (row >= 0){
                old_definition = table.getModel().getValueAt(row, 2).toString();
            }
        }
    }
}
