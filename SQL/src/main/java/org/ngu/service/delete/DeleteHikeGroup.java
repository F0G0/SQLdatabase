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

import static org.ngu.Model.SQLRequests.selectAllHikeGroups;
import static org.ngu.Model.SQLRequests.selectAllHikeInfo;

public class DeleteHikeGroup extends JDialog{
    private JPanel deleteHikeGroupPanel;
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbHikeGroup;

    public DeleteHikeGroup(JFrame parent) {
        super(parent);
        setTitle("Удаление туриста из группы");
        setContentPane(deleteHikeGroupPanel);
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
                deleteHikeGroup();
            }
        });
        setVisible(true);
    }
    private void updateList() throws SQLException {
        cbHikeGroup.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikeGroups);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
//            for (int i = 1; i <= mData.getColumnCount(); ++i) {
//                tmp.append(resultSet.getString(i)).append(" ");
//            }
            cbHikeGroup.addItem(SQLController.returnHikeWithTourist(resultSet.getString(1), resultSet.getString(2)));
            tmp.setLength(0);
        }
    }
    private void deleteHikeGroup() {
        String tmp = (String) cbHikeGroup.getSelectedItem();
        try {
            SQLController.deleteHikeGroup(tmp.substring(0, tmp.indexOf(" ")), tmp.substring(tmp.indexOf(" ")+1));
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
