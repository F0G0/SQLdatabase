package org.ngu.service.create;

import org.ngu.Controller.ConnectionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import static org.ngu.Model.SQLRequests.*;

public class CreateHikeForm extends JDialog {
    private JTextField tfHikeName;
    private JTextField tfDifficulty;
    private JComboBox cbIsPlanned;
    private JButton btnAddHike;
    private JTextField tfStopName;
    private JTextField tfTime;
    private JComboBox cbIsStop;
    private JPanel registerHikePanel;
    private JComboBox cbHike;
    private JButton btnAddStop;
    private JButton btnRefresh;
    private JButton btnCancel;
    private JComboBox cbSupervisor;

    public CreateHikeForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация Похода");
        setContentPane(registerHikePanel);
        setMinimumSize(new Dimension(750,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateBox();
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

        btnAddHike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addHike();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnAddStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addDay();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
    public void updateLength(String id) throws SQLException {
        String findAmmount = "select \"Hike\".\"length\" from \"Hike\"\n" +
                "where \"Hike\".\"id\" = ?";
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(findAmmount);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int ammount = resultSet.getInt("length");
        ammount+= 1;
        preparedStatement = ConnectionController.conn.prepareStatement(updateAmmount);
        preparedStatement.setInt(1, ammount);
        preparedStatement.setString(2, id);
        resultSet = preparedStatement.executeQuery();
    }
    public void addDay() throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addHikeDay);
        String tmpStr = (String) cbHike.getSelectedItem();
        preparedStatement.setString(1,tmpStr.substring(0, tmpStr.indexOf(" ")));
        try {
            preparedStatement.setTimestamp(2, Timestamp.valueOf(tfTime.getText()));
        }
        catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd hh:mm:ss", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        if(Objects.equals((String) cbIsStop.getSelectedItem(), "да")){
            preparedStatement.setString(3,"Y");
        }
        else preparedStatement.setString(3,"N");
        preparedStatement.setString(4,tfStopName.getText());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "Поход успешно добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
            updateLength(tmpStr.substring(0, tmpStr.indexOf(" ")));
        }
    }
    public void addHike() throws SQLException {
        if (tfTime.getText().isEmpty()||tfHikeName.getText().isEmpty()||tfDifficulty.getText().isEmpty()||tfStopName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addHike);
        preparedStatement.setString(1,tfHikeName.getText());
        String tempStr = (String) cbSupervisor.getSelectedItem();
        if(tempStr.contains("Спортсмен")){
            preparedStatement.setString(2,null);
            preparedStatement.setString(3,tempStr.substring(0, tempStr.indexOf(" ")));
        }
        else {
            preparedStatement.setString(2,tempStr.substring(0, tempStr.indexOf(" ")));
            preparedStatement.setString(3,null);
        }
        int diffSuper = Integer.parseInt(tempStr.substring(tempStr.indexOf("ь: ")+3));
        int diffHike = Integer.parseInt(tfDifficulty.getText());
        preparedStatement.setString(4,tfDifficulty.getText());
        if(Objects.equals((String) cbIsPlanned.getSelectedItem(), "да")){
            preparedStatement.setString(5,"Y");
        }
        else preparedStatement.setString(5,"N");
        preparedStatement.setString(6,"0");
        if(diffHike>diffSuper){
            JOptionPane.showMessageDialog(this, "Сложность похода не может быть больше уровня организатора.", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet!=null){
            JOptionPane.showMessageDialog(this, "Поход успешно добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void updateBox() throws SQLException {
        cbHike.removeAllItems();
        cbSupervisor.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikes);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("path_name");
            cbHike.addItem(id + ' ' + name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllAthletesCoaches);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            String difficulty = resultSet.getString("difficulty");
            cbSupervisor.addItem(id+' '+"Спортсмен: "+' '+name+' '+surname+' '+middle_name+' '+"Уровень: "+ difficulty);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            String difficulty = resultSet.getString("difficulty");
            cbSupervisor.addItem(id+' '+"Тренер: "+' '+name+' '+surname+' '+middle_name+' '+"Уровень: "+ difficulty);
        }
    }
}
