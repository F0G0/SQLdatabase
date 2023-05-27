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
import static org.ngu.Model.SQLRequests.selectAllTourists;

public class DeleteDairy extends JDialog{
    private JPanel deleteDairyPanel;
    private JComboBox cbDairy;
    private JButton btnDelete;
    private JButton btnCancel;
public DeleteDairy(JFrame parent) {
    super(parent);
    setTitle("Удаление записи");
    setContentPane(deleteDairyPanel);
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
            deleteDairy();
        }
    });


    setVisible(true);
}
    private void updateList() throws SQLException {
        cbDairy.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllDiary);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if(i == 2) tmp.append(SQLController.getHikeById(resultSet.getString(i))).append(" ");
                else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbDairy.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
    private void deleteDairy() {
        String tmp = (String) cbDairy.getSelectedItem();
        try {
            SQLController.deleteDiary(tmp.substring(0, tmp.indexOf(" ")));
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
