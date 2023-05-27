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

import static org.ngu.Model.SQLRequests.*;

public class DeleteInfoSchedule extends JDialog {
    private JComboBox cbScheduleInfo;
    private JButton btnDelete;
    private JButton btnCancel;
    private JPanel deleteScheduleInfo;
    private String id_schedule;

    public DeleteInfoSchedule(JFrame parent) {
        super(parent);
        setTitle("Удаление записи");
        setContentPane(deleteScheduleInfo);
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
                deleteScheduleInfo();
            }
        });
        setVisible(true);
    }

    private void deleteScheduleInfo() {
        String tmp = (String) cbScheduleInfo.getSelectedItem();
        String[] columns = tmp.split(" ");
        try {
            SQLController.deleteScheduleInfo(columns[0]);
            SQLController.updateAmmount(id_schedule, false);
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateList() throws SQLException {
        cbScheduleInfo.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllScheduleInfo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 2) {
                    tmp.append(SQLController.getScheduleById(resultSet.getString(i))).append(", ");
                    id_schedule = resultSet.getString(i);
                } else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbScheduleInfo.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
