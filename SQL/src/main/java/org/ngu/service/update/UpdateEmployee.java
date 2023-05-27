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

import static org.ngu.Model.SQLRequests.selectAllEmployee;

public class UpdateEmployee extends JDialog {
    private JPanel panel;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JComboBox cbEmployee;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfMiddleName;
    private JTextField tfSex;
    private JTextField tfBirthday;
    private JTextField tfSalary;
    private JTextField tfStartDate;
    private JTextField tfSpecialisation;
    private JTextField tfDifficulty;

    public UpdateEmployee(JFrame parent) {
        super(parent);
        setTitle("Обновление работника");
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
        cbEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbEmployee.getSelectedItem();
                String[] splited = selected.split(" ");
                tfName.setText(splited[1]);
                tfSurname.setText(splited[2]);
                tfMiddleName.setText(splited[3]);
                tfSex.setText(splited[4]);
                tfBirthday.setText(splited[5]);
                tfSalary.setText(splited[7]);
                tfDifficulty.setText(splited[8]);
                tfStartDate.setText(splited[9]);
                tfSpecialisation.setText(splited[11]);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = (String) cbEmployee.getSelectedItem();
                try {
                    SQLController.updateEmployee(tmp.substring(0, tmp.indexOf(" ")),tfName.getText(),tfSurname.getText(),tfMiddleName.getText(),tfSex.getText(),tfBirthday.getText(),tfSalary.getText(),tfDifficulty.getText(),tfStartDate.getText(),tfSpecialisation.getText());
                    ActionListener cb[] = cbEmployee.getActionListeners();
                    cbEmployee.removeActionListener(cb[0]);
                    updateList();
                    cbEmployee.addActionListener(cb[0]);
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
        cbEmployee.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbEmployee.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
