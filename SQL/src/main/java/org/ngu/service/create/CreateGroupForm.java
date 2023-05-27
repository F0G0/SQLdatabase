package org.ngu.service.create;

import org.ngu.Controller.ConnectionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static org.ngu.Model.SQLRequests.*;

public class CreateGroupForm extends JDialog {
    private JTextField tfName;
    private JComboBox cbSection;
    private JComboBox cbCoach;
    private JPanel registerGroupPanel;
    private JButton btnAdd;
    private JButton btnCancel;
    private JTextField tfStartDate;
    private JTextField tfEndDate;

    public CreateGroupForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация группы");
        setContentPane(registerGroupPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addGroup();
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
    public void addGroup() throws SQLException {
        if(tfEndDate.getText().isEmpty()||tfStartDate.getText().isEmpty()||tfName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addGroup);
        preparedStatement.setString(1, tfName.getText());

        ResultSet resultSet = preparedStatement.executeQuery();
        preparedStatement = ConnectionController.conn.prepareStatement(selectGroupIdByName);
        preparedStatement.setString(1, tfName.getText());
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String gId = resultSet.getString("id");
        preparedStatement = ConnectionController.conn.prepareStatement(addGroups);
        String tmpStr = (String) cbSection.getSelectedItem();
        preparedStatement.setString(1, tmpStr.substring(0, tmpStr.indexOf(" ")));
        preparedStatement.setString(2, gId);
        resultSet = preparedStatement.executeQuery();

        preparedStatement = ConnectionController.conn.prepareStatement(addGroupInfo);
        tmpStr = (String) cbCoach.getSelectedItem();
        preparedStatement.setString(1, gId);
        preparedStatement.setString(2, tmpStr.substring(0, tmpStr.indexOf(" ")));
        try {
            preparedStatement.setDate(3, Date.valueOf(tfStartDate.getText()));
            preparedStatement.setDate(4, Date.valueOf(tfEndDate.getText()));
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        resultSet = preparedStatement.executeQuery();
    }
    public void updateBox() throws SQLException {
        cbCoach.removeAllItems();
        cbSection.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbCoach.addItem(id + ' ' + name + ' ' + surname + ' ' + middle_name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllSections);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            cbSection.addItem(id + ' ' + name);
        }
    }
}
