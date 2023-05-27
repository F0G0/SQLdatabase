package org.ngu.service.create;

import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateEmployeeForm extends JDialog {
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfMiddleName;
    private JComboBox cbSex;
    private JTextField tfBirthday;
    private JTextField tfSalary;
    private JTextField tfDifficulty;
    private JTextField tfStartDate;
    private JTextField tfSpecialisation;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel registerEmployeePanel;
    public CreateEmployeeForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация работника");
        setContentPane(registerEmployeePanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        setVisible(true);
    }

    public void addEmployee() {
        if (tfBirthday.getText().isEmpty()||tfDifficulty.getText().isEmpty()||tfName.getText().isEmpty()||tfMiddleName.getText().isEmpty()||tfSalary.getText().isEmpty()||tfSpecialisation.getText().isEmpty()||tfStartDate.getText().isEmpty()||tfSurname.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String sex;
        if (cbSex.getSelectedItem() == "М") {
            sex = "m";
        } else sex = "f";
        ResultSet result = null;
        try {
            result = SQLController.createEmployee(tfName.getText(), tfSurname.getText(), tfMiddleName.getText(), sex, tfBirthday.getText(), tfSalary.getText(), tfDifficulty.getText(), tfStartDate.getText(), tfSpecialisation.getText());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result != null){
            JOptionPane.showMessageDialog(this, "Работник добавлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
