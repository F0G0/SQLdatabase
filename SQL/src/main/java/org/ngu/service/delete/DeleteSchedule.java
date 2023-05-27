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

import static org.ngu.Model.SQLRequests.selectAllSchedule;
import static org.ngu.Model.SQLRequests.selectAllScheduleInfo;

public class DeleteSchedule extends JDialog {
    private JPanel deleteSchedule;
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbSchedule;


    public DeleteSchedule(JFrame parent) {
        super(parent);
        setTitle("Удаление расписания");
        setContentPane(deleteSchedule);
        setMinimumSize(new Dimension(650, 474));
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
                deleteSchedule();
            }
        });
        setVisible(true);
    }

    private void deleteSchedule() {
        String tmp = (String) cbSchedule.getSelectedItem();
        String[] columns = tmp.split(" ");
        try {
            SQLController.deleteSchedule(columns[0]);
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateList() throws SQLException {
        cbSchedule.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllSchedule);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 4) tmp.append(SQLController.getEmployeeById(resultSet.getString(i))).append(", ");
                else if (i == 5) tmp.append(SQLController.getSectionById(resultSet.getString(i))).append(", ");
                else tmp.append(resultSet.getString(i)).append(", ");
            }
            cbSchedule.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
