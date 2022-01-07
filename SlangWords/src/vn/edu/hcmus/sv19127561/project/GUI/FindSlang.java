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
    JComboBox option;
    JList history;

    DefaultListModel model = new DefaultListModel();

    String col[] = {"No.", "Slang", "Definition"};
    DefaultTableModel tableModel = new DefaultTableModel(col, 0);

    SlangWords slangWords;

    JTable table;

    FindSlang(SlangWords sw){
        slangWords = sw;
        label = new JLabel("Search");
        back = new JButton("BACK");
        back.addActionListener(this);
        search = new JButton("SEARCH");
        search.addActionListener(this);
        String opt[] = {"Slang", "Definition"};
        option = new JComboBox(opt);
        searchLb = new JLabel("Slang");
        searchTf = new JTextField(20);

        JPanel historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        JLabel historyLabel = new JLabel("HISTORY");
        historyLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        historyLabel.setAlignmentX(CENTER_ALIGNMENT);

        model.addAll(slangWords.getHistory());
        history = new JList();
        history.setModel(model);
        history.setPreferredSize(new Dimension(200, 500));
        JScrollPane historyList = new JScrollPane(history);
        historyPanel.add(historyLabel);
        historyPanel.add(historyList);


        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLb);
        searchPanel.add(searchTf);
        searchPanel.add(search);
        searchPanel.add(option);

        // Table
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        Container con = this.getContentPane();

        con.setLayout(new BorderLayout());
        con.add(searchPanel, BorderLayout.PAGE_START);
        con.add(scrollPane, BorderLayout.CENTER);
        con.add(historyPanel, BorderLayout.EAST);
        con.add(back, BorderLayout.PAGE_END);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Search By Slang");
        this.setVisible(true);
        this.setSize(700, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search){
            this.clearTable();
            if (searchTf.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Input field blank");
            }
            else {
                String keyword = searchTf.getText();
                slangWords.addHistory(keyword);
                model.add(0, keyword);
                if (model.size() > 20) {
                    model.remove(20);
                }
                slangWords.save_history();
                String[][] result = new String[0][];
                if (option.getSelectedIndex() == 0) {
                    result = slangWords.search_by_slang(keyword);
                } else if (option.getSelectedIndex() == 1) {
                    result = slangWords.search_by_definition(keyword);
                }
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
