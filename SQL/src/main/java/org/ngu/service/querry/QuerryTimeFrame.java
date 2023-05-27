package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryTimeFrame extends JDialog{
    private JPanel querryPanel;
    private JButton btnCancel;
    private JButton btnFind;
    private JTextArea taAnswer;
public QuerryTimeFrame(JFrame parent) {
    super(parent);
    setTitle("Вывод тренеров");
    setContentPane(querryPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    btnFind.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                java.sql.Date start = java.sql.Date.valueOf(JOptionPane.showInputDialog(parent,
                        "Введите дату начала", null));
                java.sql.Date end = java.sql.Date.valueOf(JOptionPane.showInputDialog(parent,
                        "Введите дату конца", null));
                taAnswer.append(SQLController.fourhtQuerry(name, start, end));
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
