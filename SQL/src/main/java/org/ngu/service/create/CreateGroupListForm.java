package org.ngu.service.create;

import org.ngu.Controller.ConnectionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.ngu.Model.SQLRequests.*;

public class CreateGroupListForm extends JDialog{
    private JComboBox cbGroup;
    private JComboBox cbTourist;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerGroupListPanel;

    public CreateGroupListForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация группы");
        setContentPane(registerGroupListPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addPair();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(registerGroupListPanel, "Человек уже находится в группе", "Окей", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }
    public void addPair() throws SQLException {

        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addTouristGroup);
        String tmpStr = (String) cbGroup.getSelectedItem();
        preparedStatement.setString(1, tmpStr.substring(0, tmpStr.indexOf(" ")));
        tmpStr = (String) cbTourist.getSelectedItem();
        preparedStatement.setString(2, tmpStr.substring(0, tmpStr.indexOf(" ")));
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "Участник успешно добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void updateBox() throws SQLException {
        cbTourist.removeAllItems();
        cbGroup.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllTourists);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbTourist.addItem(id + ' ' + name + ' ' + surname + ' ' + middle_name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllGroups);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            cbGroup.addItem(id + ' ' + name);
        }
    }
}
