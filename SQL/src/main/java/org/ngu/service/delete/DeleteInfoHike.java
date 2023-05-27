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

import static org.ngu.Model.SQLRequests.selectAllHikeInfo;
import static org.ngu.Model.SQLRequests.selectAllHikes;

public class DeleteInfoHike extends JDialog {
    private JComboBox cbInfoHike;
    private JButton btnDelete;
    private JButton btnCancel;
    private JPanel deleteInfoHikePanel;
    private String id_hike;

    public DeleteInfoHike(JFrame parent) {
        super(parent);
        setTitle("Удаление остановки");
        setContentPane(deleteInfoHikePanel);
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
                deleteHikeInfo();
            }
        });
        setVisible(true);
    }

    private void deleteHikeInfo() {
        String tmp = (String) cbInfoHike.getSelectedItem();
        String[] columns = tmp.split(" ");
        try {
            SQLController.deleteHikeInfo(columns[0]);
            SQLController.updateLength(id_hike, false);
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateList() throws SQLException {
        cbInfoHike.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikeInfo);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 2) {
                    tmp.append(SQLController.getHikeById(resultSet.getString(i))).append(" ");
                    id_hike = resultSet.getString(i);
                } else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbInfoHike.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}

