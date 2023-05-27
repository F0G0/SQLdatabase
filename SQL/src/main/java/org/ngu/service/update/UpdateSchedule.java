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

public class UpdateSchedule extends JDialog {
    private JComboBox cbSchedule;
    private JTextField tfName;
    private JPanel panel;
    private JComboBox cbCoach;
    private JComboBox cbSection;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JTextField tfHours;

    public UpdateSchedule(JFrame parent) {
        super(parent);
        setTitle("Обновление расписания");
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
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp1 = (String) cbSchedule.getSelectedItem();
                String tmp2 = (String) cbCoach.getSelectedItem();
                String tmp3 = (String) cbSection.getSelectedItem();
                try {
                    SQLController.updateSchedule(tmp1.substring(0, tmp1.indexOf(" ")), tfName.getText(),tmp2.substring(0, tmp2.indexOf(" ")),tmp3.substring(0, tmp3.indexOf(" ")), tfHours.getText());
                    ActionListener cb[] = cbSchedule.getActionListeners();
                    cbSchedule.removeActionListener(cb[0]);
                    updateList();
                    cbSchedule.addActionListener(cb[0]);
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
        cbSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbSchedule.getSelectedItem();
                String[] splited = selected.split(" ");
                tfName.setText(splited[2]);
                tfHours.setText(splited[11]);
                int cnt = 0;
                String tmp = (String) cbCoach.getItemAt(cnt);
                while(!Objects.equals(splited[3], tmp.substring(0, tmp.indexOf(" ")))) {
                    cnt++;
                    tmp = (String) cbCoach.getItemAt(cnt);
                }
                cbCoach.setSelectedIndex(cnt);

                cnt = 0;
                tmp = (String) cbSection.getItemAt(cnt);
                while(!Objects.equals(splited[8], tmp.substring(0, tmp.indexOf(" ")))) {
                    cnt++;
                    tmp = (String) cbSection.getItemAt(cnt);
                }
                cbSection.setSelectedIndex(cnt);

            }
        });

        setVisible(true);
    }
    private void updateList() throws SQLException {
        cbSchedule.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllSchedule);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 4) {
                    tmp.append(resultSet.getString(i)).append(" ");
                    tmp.append(SQLController.getEmployeeById(resultSet.getString(i))).append(" ");
                }
                else if (i == 5) {
                    tmp.append(resultSet.getString(i)).append(" ");
                    tmp.append(SQLController.getSectionById(resultSet.getString(i))).append(" ");
                }
                else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbSchedule.addItem(tmp.toString());
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

        preparedStatement = ConnectionController.conn.prepareStatement(selectAllSections);
        resultSet = preparedStatement.executeQuery();
        mData = preparedStatement.getMetaData();
        tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 3) tmp.append(SQLController.getEmployeeById(resultSet.getString(i)));
                else
                    tmp.append(resultSet.getString(i)).append(" ");
            }

            cbSection.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
