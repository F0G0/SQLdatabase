package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class QuerryCoach extends JDialog {
    private JTextArea taCoach;
    private JButton btnAll;
    private JButton btnSection;
    private JButton btnSex;
    private JButton btnSalary;
    private JButton btnSpecialisation;
    private JButton btnBack;
    private JPanel querryCoachPanel;
public QuerryCoach(JFrame parent) {

    super(parent);
    setTitle("Вывод тренеров");
    setContentPane(querryCoachPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    btnAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taCoach.setText("");
            try {
                taCoach.append(SQLController.secondQuerry(SQLRequests.selectAllCoaches, null));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnSection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taCoach.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите секцию.", null);
            try {
                taCoach.append(SQLController.secondQuerry(SQLRequests.selectAllCoachesBySection, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });
    btnSex.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taCoach.setText("");
            String sex = JOptionPane.showInputDialog(parent,
                    "Введите пол: m - мужчина f - женщина.", null);
            if (!Objects.equals(sex, "m") && !Objects.equals(sex, "f"))
                JOptionPane.showMessageDialog(null, "Неверно введен пол.", "Окей", JOptionPane.ERROR_MESSAGE);
            try {
                taCoach.append(SQLController.secondQuerry(SQLRequests.selectAllCoachesBySex, sex));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnSalary.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taCoach.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите зарплату.", null);
            try {
                taCoach.append(SQLController.secondQuerry(SQLRequests.selectAllCoachesBySalary, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    });
    btnSpecialisation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taCoach.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите специализацию.", null);
            try {
                taCoach.append(SQLController.secondQuerry(SQLRequests.selectAllCoachesBySpecialisation, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    setVisible(true);
}
}
