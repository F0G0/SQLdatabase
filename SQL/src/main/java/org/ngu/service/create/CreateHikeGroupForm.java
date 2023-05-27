package org.ngu.service.create;

import org.ngu.Controller.ConnectionController;
import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.ngu.Model.SQLRequests.*;

public class CreateHikeGroupForm extends JDialog{
    private JComboBox cbTourist;
    private JComboBox cbHike;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerHikeGroupPanel;

    public CreateHikeGroupForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация группы похода");
        setContentPane(registerHikeGroupPanel);
        setMinimumSize(new Dimension(400,474));
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
                    JOptionPane.showMessageDialog(registerHikeGroupPanel, "Человек уже находится в группе", "Окей", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }
    public void updateBox() throws SQLException {
        cbTourist.removeAllItems();
        cbHike.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllTourists);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbTourist.addItem(id + ' ' + name + ' ' + surname + ' ' + middle_name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikes);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("path_name");
            cbHike.addItem(id + ' ' + name);
        }
    }
    public void addPair() throws SQLException {
        String hike = (String) cbHike.getSelectedItem();
        String tourist = (String) cbTourist.getSelectedItem();
        ResultSet resultSet = SQLController.addHikeGroup(hike.substring(0, hike.indexOf(" ")), tourist.substring(0, tourist.indexOf(" ")));
        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "Участник успешно добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
