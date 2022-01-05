package vn.edu.hcmus.sv19127561.project.GUI;

import vn.edu.hcmus.sv19127561.project.SlangWords;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * vn.edu.hcmus.sv19127561.project.GUI
 * Created by Thinh
 * Date 12/19/2021 - 10:41 PM
 * Description: ...
 */
public class FindSlang extends JFrame implements ActionListener {
    JButton back;
    JButton search;
    JLabel searchLb;
    JTextField searchTf;
    JLabel label;

    String col[] = {"No.", "Slang", "Definition"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

    SlangWords slangWords;

    JTable table;

    FindSlang(SlangWords sw){
        slangWords = sw;
        label = new JLabel("Search by slang");
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
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }
}
