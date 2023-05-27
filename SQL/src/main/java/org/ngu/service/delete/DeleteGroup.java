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
import static org.ngu.Model.SQLRequests.selectAllGroups;

public class DeleteGroup extends JDialog{
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbGroup;
    private JPanel deleteGroupPanel;

    public DeleteGroup(JFrame parent) {
        super(parent);
        setTitle("Удаление записи");
        setContentPane(deleteGroupPanel);
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
                deleteGroup();
            }
        });
        setVisible(true);
    }

    private void deleteGroup() {
            String tmp = (String) cbGroup.getSelectedItem();
            try {
                SQLController.deleteGroup(tmp.substring(0, tmp.indexOf(" ")));
                updateList();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    private void updateList() throws SQLException {
        cbGroup.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllGroups);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbGroup.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
