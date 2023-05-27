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
import java.sql.Timestamp;

import static org.ngu.Model.SQLRequests.*;

public class CreateScheduleForm extends JDialog{
    private JTextField tfName;
    private JPanel registerSchedulePanel;
    private JComboBox cbSection;
    private JComboBox cbCoach;
    private JTextField tfPlace;
    private JTextField tfTimeAndDate;
    private JComboBox cbName;
    private JButton btnAddSchedule;
    private JButton btnAddInfo;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JTextField tfHours;

    public CreateScheduleForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация занятий");
        setContentPane(registerSchedulePanel);
        setMinimumSize(new Dimension(600,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateBox();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnAddSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addSchedule();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        btnAddInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addDay();
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
    public void updateBox() throws SQLException {
        cbSection.removeAllItems();
        cbCoach.removeAllItems();
        cbName.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbCoach.addItem(id+' '+name+' '+surname+' '+middle_name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllSections);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            cbSection.addItem(id+' '+name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllSchedule);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            cbName.addItem(id+' '+name);
        }
    }
    public void addSchedule() throws SQLException {
        if (tfName.getText().isEmpty()||tfHours.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String coach = (String) cbCoach.getSelectedItem();
        String section = (String) cbSection.getSelectedItem();
        ResultSet resultSet = SQLController.createSchedule(tfName.getText(), coach.substring(0, coach.indexOf(" ")), section.substring(0, section.indexOf(" ")), tfHours.getText());
        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "Расписание успешно добавлено.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void addDay() throws SQLException {
        if(tfPlace.getText().isEmpty()||tfTimeAndDate.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tmpStr = (String) cbName.getSelectedItem();

        ResultSet resultSet = null;
        try {
            resultSet = SQLController.createDay(tmpStr.substring(0, tmpStr.indexOf(" ")), tfPlace.getText(),tfTimeAndDate.getText());
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd hh:mm:ss", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "День успешно добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
