package org.ngu.service.create;

import oracle.jdbc.proxy.annotation.Pre;
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

public class CreateSectionForm extends JDialog {
    private JTextField tfName;
    private JComboBox cbSupervisor;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerSectionPanel;

    public CreateSectionForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация секции");
        setContentPane(registerSectionPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateBox();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Не удалось загрузить работников.", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addSection();
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
    public void addSection() throws SQLException {
        if(tfName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tmpStr = (String) cbSupervisor.getSelectedItem();
        if(SQLController.createSection(tfName.getText(),tmpStr.substring(0, tmpStr.indexOf(" ")))!=null){
            JOptionPane.showMessageDialog(this, "Секция успешно добавлена.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void updateBox() throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");

            cbSupervisor.addItem(id+' '+name+' '+surname+' '+middle_name);
        }
    }
}
