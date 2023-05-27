package org.ngu.service.update;

import org.ngu.Controller.ConnectionController;
import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import static org.ngu.Model.SQLRequests.selectAllGroups;

public class UpdateGroup extends JDialog {
    private JButton btnChange;

    public static <T> T[] subArray(T[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end + 1);
    }

    private JButton btnCancel;
    private JComboBox comboBox1;
    private JPanel panel;
    private JTextField tfName;

    public UpdateGroup(JFrame parent) {
        super(parent);
        setTitle("Обновление группы");
        setContentPane(panel);
        setMinimumSize(new Dimension(650, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox1.getSelectedItem();
                String[] splited = selected.split(" ");
                String[] name = subArray(splited, 1, splited.length);
                //tfName.setText(String.join(" ", name));
                tfName.setText(splited[1]);
            }
        });
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = (String) comboBox1.getSelectedItem();
                try {
                    SQLController.updateGroup(tmp.substring(0, tmp.indexOf(" ")), tfName.getText());
                    ActionListener cb[] = comboBox1.getActionListeners();
                    comboBox1.removeActionListener(cb[0]);
                    updateList();
                    comboBox1.addActionListener(cb[0]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void updateList() throws SQLException {
        comboBox1.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllGroups);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            comboBox1.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
