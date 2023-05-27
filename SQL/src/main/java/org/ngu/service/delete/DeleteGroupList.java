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

import static org.ngu.Model.SQLRequests.selectAllGroupList;
import static org.ngu.Model.SQLRequests.selectAllHikeGroups;

public class DeleteGroupList extends JDialog{
    private JPanel deleteGroupListPanel;
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbGroupList;

    public DeleteGroupList(JFrame parent) {
        super(parent);
        setTitle("Удаление туриста из группы");
        setContentPane(deleteGroupListPanel);
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
                deleteGroupList();
            }
        });
        setVisible(true);
    }

    private void deleteGroupList() {
        String tmp = (String) cbGroupList.getSelectedItem();
        try {
            SQLController.deleteGroupList(tmp.substring(0, tmp.indexOf(" ")), tmp.substring(tmp.indexOf(" ")+1));
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateList() throws SQLException {
        cbGroupList.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllGroupList);
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            cbGroupList.addItem(SQLController.returnGroupWithTourist(resultSet.getString(1), resultSet.getString(2)));
            tmp.setLength(0);
        }
    }
}
