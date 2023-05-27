package org.ngu.service.delete;

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

import static org.ngu.Model.SQLRequests.selectAllDiary;
import static org.ngu.Model.SQLRequests.selectAllEmployee;

public class DeleteEmployee extends JDialog{
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbEmployee;
    private JPanel deleteEmployeePanel;

    public DeleteEmployee(JFrame parent) {
        super(parent);
        setTitle("Удаление работника");
        setContentPane(deleteEmployeePanel);
        setMinimumSize(new Dimension(650,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });

        setVisible(true);
    }

    private void deleteEmployee() {
        String tmp = (String) cbEmployee.getSelectedItem();
        try {
            SQLController.deleteEmployee(tmp.substring(0, tmp.indexOf(" ")));
            updateList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Не удалось удалить работника. Проверьте что он не является руководителем и не является тренером группы.", "Окей", JOptionPane.ERROR_MESSAGE);;
        }
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
