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

import static org.ngu.Model.SQLRequests.selectAllHikes;

public class CreateDiaryForm extends JDialog{
    private JComboBox cbHike;
    private JTextField tfDataAndTime;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerDairyPanel;
    private JTextArea taDairy;

    public CreateDiaryForm(JFrame parent) {
        setTitle("Регистрация дня");
        setContentPane(registerDairyPanel);
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
                addDairy();
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

    private void addDairy() {
        if(tfDataAndTime.getText().isEmpty() || taDairy.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ResultSet result = null;
        try {
            String tmpStr = (String) cbHike.getSelectedItem();
            result = SQLController.createDairy(tmpStr.substring(0, tmpStr.indexOf(" ")),tfDataAndTime.getText(),taDairy.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd hh:mm:ss", "Окей", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result != null){
            JOptionPane.showMessageDialog(this, "Запись добавлена.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void updateBox() throws SQLException {
        cbHike.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllHikes);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("path_name");
            cbHike.addItem(id + ' ' + name);
        }
    }
}
