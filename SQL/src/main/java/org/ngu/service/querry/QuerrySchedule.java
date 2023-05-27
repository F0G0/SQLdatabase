package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerrySchedule extends JDialog {
    private JPanel querrySchedulePanel;
    private JTextArea textArea1;
    private JButton btnAll;
    private JButton btnSection;
    private JButton btnDate;
    private JButton btnCancel;
public QuerrySchedule(JFrame parent) {
    super(parent);
    setTitle("Вывод расписания");
    setContentPane(querrySchedulePanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    btnAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            try {
                textArea1.append(SQLController.seventhQuerry(SQLRequests.selectAllScheduleWithTime, null));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnSection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите секцию.", null);
            try {
                textArea1.append(SQLController.seventhQuerry(SQLRequests.selectAllScheduleWithTimeBySection, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnDate.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            try {
                java.sql.Date start = java.sql.Date.valueOf(JOptionPane.showInputDialog(parent,
                        "Введите дату начала", null));
                java.sql.Date end = java.sql.Date.valueOf(JOptionPane.showInputDialog(parent,
                        "Введите дату конца", null));
                textArea1.append(SQLController.seventhQuerry(start, end));
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Неверный формат даты: yyyy-mm-dd", "Окей", JOptionPane.ERROR_MESSAGE);
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
