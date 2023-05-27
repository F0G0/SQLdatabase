package org.ngu.service.querry;

import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class QuerryTourists extends JDialog {
    private JPanel touristQuerryPanel;
    private JButton btnSection;
    private JButton btnGroup;
    private JButton btnSex;
    private JButton btnBirthday;
    private JButton btnAge;
    private JButton btnBack;
    private JTextArea taAnswer;
    private JButton btnAllTourist;

    public QuerryTourists(JFrame parent) {
        super(parent);
        setTitle("Вывод туристов");
        setContentPane(touristQuerryPanel);
        setMinimumSize(new Dimension(650, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                try {
                    taAnswer.append(SQLController.firstQuerrySectionTourist(name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                try {
                    taAnswer.append(SQLController.firstQuerryGroupTourist(name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        btnSex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String sex = JOptionPane.showInputDialog(parent,
                        "Введите пол: m - мужчина f - женщина.", null);
                if (!Objects.equals(sex, "m") && !Objects.equals(sex, "f"))
                    JOptionPane.showMessageDialog(null, "Неверно введен пол.", "Окей", JOptionPane.ERROR_MESSAGE);
                try {
                    taAnswer.append(SQLController.firstQuerrySexTourist(sex));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBirthday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                try {
                    java.sql.Date bday = java.sql.Date.valueOf(JOptionPane.showInputDialog(parent,
                            "Введите дату рождения", null));
                    taAnswer.append(SQLController.firstQuerryBdayTourist(bday));
                } catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        btnAge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String age = JOptionPane.showInputDialog(parent,
                        "Введите возраст", null);
                try {
                    taAnswer.append(SQLController.firstQuerryAgeTourist(age));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });
        btnAllTourist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                try {
                    taAnswer.append(SQLController.firstQuerryAllTourist());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        setVisible(true);
    }
}
