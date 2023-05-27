package org.ngu.service.create;

import org.ngu.Controller.ConnectionController;
import org.ngu.Controller.PatternMatcher;
import org.ngu.Controller.SQLController;
import org.ngu.Entity.TouristEntity;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.ngu.Model.SQLRequests.addTourist;

public class CreateTouristForm extends JDialog {

    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfDate;
    private JComboBox cbSex;
    private JComboBox cbPassion;
    private JTextField tfDifficulty;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerTouristPanel;
    private JTextField tfMiddleName;

    public CreateTouristForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация туриста");
        setContentPane(registerTouristPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addNewTourist();
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

    private void addNewTourist() throws SQLException {
        if(tfName.getText().isEmpty()||tfMiddleName.getText().isEmpty()||tfSurname.getText().isEmpty()||tfDifficulty.getText().isEmpty()||tfDate.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sex;
        String passion;
        if ("М".equals((String) cbSex.getSelectedItem())) {
            sex = "m";
        } else sex = "f";
        if ("Спортсмен".equals((String) cbPassion.getSelectedItem())) {
            passion = "athlete";
        } else if ("Любитель".equals((String) cbPassion.getSelectedItem())) {
            passion = "tourist";
        } else passion = "coach";

        ResultSet result = null;
        try {
            result = SQLController.createTourist(tfName.getText(), tfSurname.getText(), tfMiddleName.getText(), sex, tfDate.getText(),passion,tfDifficulty.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        if (result != null) {
            JOptionPane.showMessageDialog(this, "Турист добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }

    }
}
