package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryInstructors extends JDialog {
    private JPanel querryInstructorPanel;
    private JTextArea taAnswer;
    private JButton btnDifficulty;
    private JButton btnInfo;
    private JButton btnHike;
    private JButton btnCancel;

    public QuerryInstructors(JFrame parent) {
    super(parent);
    setTitle("Вывод инструкторов");
    setContentPane(querryInstructorPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    btnDifficulty.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите сложность.", null);
            try {
                taAnswer.append(SQLController.eleventhQuerry(SQLRequests.selectInstructorsByDifficulty, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnHike.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите поход.", null);
            try {
                taAnswer.append(SQLController.eleventhQuerry(SQLRequests.selectInstructorByHikeName, name));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
    btnInfo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите остановку.", null);
            try {
                taAnswer.append(SQLController.eleventhQuerry(SQLRequests.selectInstructorsByStop, name));
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
