package org.ngu.service.update;

import org.ngu.Controller.ConnectionController;
import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

import static org.ngu.Model.SQLRequests.*;

public class UpdateTouristForm extends JDialog {
    private JComboBox cbTourist;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfMiddlle_name;
    private JComboBox cbSex;
    private JTextField tfBirthday;

    private JTextField tfDifficulty;
    private JButton btnChange;
    private JButton btnCancel;
    private JPanel panel;
    private JComboBox cbPassion;


    public UpdateTouristForm(JFrame parent) {
        super(parent);
        setTitle("Изменение туриста");
        setContentPane(panel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cbTourist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbTourist.getSelectedItem();
                String[] splited = selected.split(" ");
                tfName.setText(splited[1]);
                tfSurname.setText(splited[2]);
                tfMiddlle_name.setText(splited[3]);
                if(Objects.equals(splited[4], "m")) cbSex.setSelectedIndex(0);
                else cbSex.setSelectedIndex(1);
                if ("athlete".equals(splited[7])) {
                    cbPassion.setSelectedIndex(1);
                } else if ("tourist".equals(splited[7])) {
                    cbPassion.setSelectedIndex(0);
                } else
                    cbPassion.setSelectedIndex(2);
                tfBirthday.setText(splited[5]);
                tfDifficulty.setText(splited[8]);

            }
        });
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet result = null;
                String passion;
                if ("Спортсмен".equals((String) cbPassion.getSelectedItem())) {
                    passion = "athlete";
                } else if ("Любитель".equals((String) cbPassion.getSelectedItem())) {
                    passion = "tourist";
                } else if ("Тренер".equals((String) cbPassion.getSelectedItem())) {
                    passion = "coach";
                } else passion = "coach";
                try {
                    String  tmp = (String) cbTourist.getSelectedItem();
                    result = SQLController.updateTourist(tmp.substring(0, tmp.indexOf(" ")), tfName.getText(), tfSurname.getText(), tfMiddlle_name.getText(), (String) cbSex.getSelectedItem(), tfBirthday.getText(),passion,tfDifficulty.getText());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (result != null) {
                    JOptionPane.showMessageDialog(null, "Турист обновлен.", "Окей", JOptionPane.PLAIN_MESSAGE);
                }
                try {
                    ActionListener cb[] = cbTourist.getActionListeners();
                    cbTourist.removeActionListener(cb[0]);
                    updateList();
                    cbTourist.addActionListener(cb[0]);
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
    private void updateList() throws SQLException {
        cbTourist.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllTourists);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbTourist.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
}
