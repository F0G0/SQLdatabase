package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerrySupervisor extends JDialog{
    private JPanel querrySupervisorPanel;
    private JTextArea taAnswer;
    private JButton btnFull;
    private JButton btnSalary;
    private JButton btnBirthday;
    private JButton btnAge;
    private JButton btnWorkDay;
    private JButton btnCancel;
public QuerrySupervisor(JFrame parent) {
    super(parent);
    setTitle("Вывод тренеров");
    setContentPane(querrySupervisorPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    btnFull.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectSupervisors, null));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnSalary.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите зарплату.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectSupervisorsBySalary, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnBirthday.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите год рождения.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectSupervisorsByBday, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnAge.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите возраст.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectSupervisorsByAge, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnWorkDay.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите год поступления на работу.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectSupervisorsByWorkYear, name));
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
}
