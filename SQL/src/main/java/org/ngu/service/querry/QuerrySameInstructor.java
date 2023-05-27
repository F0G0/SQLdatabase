package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerrySameInstructor extends JDialog {
    private JPanel querryPanel;
    private JButton btnGroup;
    private JButton btnCancel;
    private JTextArea taAnswer;
    private JButton btnSection;
public QuerrySameInstructor(JFrame parent) {
    super(parent);
    setTitle("Вывод туристов");
    setContentPane(querryPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    btnGroup.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите группу.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectInsTourByGroup, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnSection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите секцию.", null);
            try {
                taAnswer.append(SQLController.secondQuerry(SQLRequests.selectInsTourByGroup, name));
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
