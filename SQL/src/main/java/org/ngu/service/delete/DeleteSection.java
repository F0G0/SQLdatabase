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
import static org.ngu.Model.SQLRequests.selectAllSections;

public class DeleteSection extends JDialog {
    private JComboBox cbSection;
    private JButton btnDelete;
    private JButton btnCancel;
    private JPanel deleteSectionPanel;

    public DeleteSection(JFrame parent) {
        super(parent);
        setTitle("Удаление секции");
        setContentPane(deleteSectionPanel);
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
                deleteSection();
            }
        });
        setVisible(true);
    }

    private void deleteSection() {
        String tmp = (String) cbSection.getSelectedItem();
        String[] columns = tmp.split(" ");
        try {
            SQLController.deleteSection(columns[0]);
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateList() throws SQLException {
        cbSection.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllSections);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 3) tmp.append(SQLController.getEmployeeById(resultSet.getString(i)));
                else
                    tmp.append(resultSet.getString(i)).append(", ");
            }

            cbSection.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
