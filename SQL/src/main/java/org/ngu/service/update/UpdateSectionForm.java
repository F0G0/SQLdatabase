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
import java.util.Objects;

import static org.ngu.Model.SQLRequests.*;

public class UpdateSectionForm extends JDialog {
    private JPanel panel;
    private JComboBox cbSection;
    private JTextField tfName;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JComboBox cbSupervisor;

    public UpdateSectionForm(JFrame parent) {
        super(parent);
        setTitle("Изменение секции");
        setContentPane(panel);
        setMinimumSize(new Dimension(750, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = (String) cbSection.getSelectedItem();
                String tmp2 = (String) cbSupervisor.getSelectedItem();
                try {
                    SQLController.updateSection(tmp.substring(0, tmp.indexOf(" ")), tfName.getText(), tmp2.substring(0, tmp2.indexOf(" ")));
                    ActionListener cb[] = cbSection.getActionListeners();
                    cbSection.removeActionListener(cb[0]);
                    updateList();
                    cbSection.addActionListener(cb[0]);
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
        cbSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbSection.getSelectedItem();
                String[] splited = selected.split(" ");
                tfName.setText(splited[1]);
                int cnt = 0;
                String tmp = (String) cbSupervisor.getItemAt(cnt);
                while(!Objects.equals(splited[2], tmp.substring(0, tmp.indexOf(" ")))) {
                    cnt++;
                    tmp = (String) cbSupervisor.getItemAt(cnt);
                }
                cbSupervisor.setSelectedIndex(cnt);
            }
        });
        setVisible(true);

    }

    private void updateList() throws SQLException {
        cbSection.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllSections);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if (i == 3) {
                    tmp.append(resultSet.getString(i)).append(" ");
                    tmp.append(SQLController.getEmployeeById(resultSet.getString(i)));
                }
                else
                    tmp.append(resultSet.getString(i)).append(" ");
            }

            cbSection.addItem(tmp.toString());
            tmp.setLength(0);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        resultSet = preparedStatement.executeQuery();
        mData = preparedStatement.getMetaData();
        tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbSupervisor.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
