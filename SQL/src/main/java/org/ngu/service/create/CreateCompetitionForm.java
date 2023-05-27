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

public class CreateCompetitionForm extends JDialog {
    private JTextField tfName;
    private JPanel registerCompetitionForm;
    private JComboBox cbSection;
    private JComboBox cbAthlete;
    private JButton btnAdd;
    private JButton btnCancel;

    public CreateCompetitionForm(JFrame parent) {
        super(parent);
        setTitle("Регистрация соревнования");
        setContentPane(registerCompetitionForm);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        try {
            updateBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCompetition();
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

    private void addCompetition() {
        if (tfName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заполните все поля", "Окей", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String section = (String) cbSection.getSelectedItem();
        ResultSet result = null;
        try {
            result = SQLController.createCompetition(tfName.getText(), (String) cbAthlete.getSelectedItem(), section.substring(0, section.indexOf(" ")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result != null) {
            JOptionPane.showMessageDialog(this, "Соревнование добавлено.", "Окей", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void updateBox() throws SQLException {
        cbAthlete.removeAllItems();
        cbSection.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllAthletesCoaches);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbAthlete.addItem(id + ' ' + "Спортсмен: " + ' ' + name + ' ' + surname + ' ' + middle_name);
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectAllEmployee);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String middle_name = resultSet.getString("middle_name");
            cbAthlete.addItem(id + ' ' + "Тренер: " + ' ' + name + ' ' + surname + ' ' + middle_name);
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
