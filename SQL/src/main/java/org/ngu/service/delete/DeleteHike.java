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
import java.util.Objects;

import static org.ngu.Model.SQLRequests.selectAllDiary;
import static org.ngu.Model.SQLRequests.selectAllHikes;

public class DeleteHike extends JDialog {
    private JPanel deleteHikePanel;
    private JComboBox cbHike;
    private JButton btnDelete;
    private JButton btnCancel;


    public DeleteHike(JFrame parent) {
        super(parent);
        setTitle("Удаление похода");
        setContentPane(deleteHikePanel);
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
                deleteHike();
            }
        });
        setVisible(true);
    }

    private void updateList() throws SQLException {
        cbHike.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikes);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if((i==2) && (!Objects.equals(resultSet.getString(i), "null"))) tmp.append(SQLController.getEmployeeById(resultSet.getString(i))).append(" ");
                else if((i==3) && (!Objects.equals(resultSet.getString(i), "null"))) tmp.append(SQLController.getTouristById(resultSet.getString(i))).append(" ");
                else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbHike.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
    private void deleteHike() {
        String tmp = (String) cbHike.getSelectedItem();
        try {
            SQLController.deleteHike(tmp.substring(0, tmp.indexOf(" ")));
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
