package org.ngu.service;

import org.ngu.Controller.ConnectionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm extends JDialog {
    private JPanel loginPanel;
    private JTextField tfLogin;
    private JButton btnLogin;
    private JPasswordField tfPassword;
    private JButton btnCancel;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Вход");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(600,250));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginUser()) {
                    dispose();
                    MainMenu menu = new MainMenu(null);
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

    private boolean loginUser() {
        String login = tfLogin.getText();
        String password = new String(tfPassword.getPassword());
        if(login.isEmpty()||password.isEmpty()){
            JOptionPane.showMessageDialog(this, "Пожалуйста, введите все поля.", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        try {
            ConnectionController.connect(login,password);
            if(ConnectionController.conn!= null) System.out.println("All good");
            return true;
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Неверный логин или пароль.", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
