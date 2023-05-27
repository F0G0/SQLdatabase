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

import static org.ngu.Model.SQLRequests.selectAllScheduleInfo;

public class UpdateScheduleInfo extends JDialog {
    private JComboBox cbScheduleInfo;

    public static <T> T[] subArray(T[] array, int beg, int end) {
        return Arrays.copyOfRange(array, beg, end + 1);
    }

    private JTextField tfPlace;
    private JTextField tfTime;
    private JPanel panel;
    private JButton btnUpdate;
    private JButton btnCancel;

    public UpdateScheduleInfo(JFrame parent) {
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
        cbScheduleInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbScheduleInfo.getSelectedItem();
                String[] splited = selected.split(" ");
                String[] place = subArray(splited, 3, splited.length - 3);

                tfPlace.setText(String.join(" ", place));
                String time = splited[splited.length - 2] + " " + splited[splited.length - 1];
                tfTime.setText(time);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = (String) cbScheduleInfo.getSelectedItem();
                try {
                    SQLController.updateScheduleInfo(tmp.substring(0, tmp.indexOf(" ")), tfPlace.getText(),tfTime.getText());
                    ActionListener cb[] = cbScheduleInfo.getActionListeners();
                    cbScheduleInfo.removeActionListener(cb[0]);
                    updateList();
                    cbScheduleInfo.addActionListener(cb[0]);
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
        cbScheduleInfo.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllScheduleInfo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 2) {
                    tmp.append(SQLController.getScheduleById(resultSet.getString(i))).append(" ");
                } else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbScheduleInfo.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
