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
import java.util.Objects;

import static org.ngu.Model.SQLRequests.*;

public class UpdateGroupInfo extends JDialog {
    private JButton btnUpdate;
    private JButton btnCancel;
    private JComboBox cbInfo;
    private JTextField tfStart;
    private JTextField tfEnd;
    private JPanel panel;
    private JComboBox cbCoach;

    public UpdateGroupInfo(JFrame parent) {
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
        cbInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbInfo.getSelectedItem();
                String[] splited = selected.split(" ");
                tfStart.setText(splited[1]);
                tfEnd.setText(splited[3]);
                int cnt = 0;
                String tmp = (String) cbCoach.getItemAt(cnt);
                while (!Objects.equals(splited[5], tmp.substring(0, tmp.indexOf(" ")))) {
                    cnt++;
                    tmp = (String) cbCoach.getItemAt(cnt);
                }
                cbCoach.setSelectedIndex(cnt);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = (String) cbInfo.getSelectedItem();
                String tmp2 = (String) cbCoach.getSelectedItem();
                try {
                    SQLController.updateGroupInfo(tmp.substring(0, tmp.indexOf(" ")), tfStart.getText(),tfEnd.getText(),tmp2.substring(0, tmp2.indexOf(" ")));
                    ActionListener cb[] = cbInfo.getActionListeners();
                    cbInfo.removeActionListener(cb[0]);
                    updateList();
                    cbInfo.addActionListener(cb[0]);
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
        cbInfo.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllGroupInfo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 4) {
                    tmp.append(resultSet.getString(i)).append(" ");
                    tmp.append(SQLController.getEmployeeById(resultSet.getString(i))).append(" ");
                } else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbInfo.addItem(tmp.toString());
            tmp.setLength(0);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        resultSet = preparedStatement.executeQuery();
        mData = preparedStatement.getMetaData();
        tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbCoach.addItem(tmp.toString());
            tmp.setLength(0);
        }

    }
}
