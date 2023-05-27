package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryHikeSpecial extends JDialog{
    private JPanel querryHikePanel;
    private JTextArea taAnswer;
    private JButton btnSection;
    private JButton btnGroups;
    private JButton btnCoach;
    private JButton btnStop;
    private JButton btnLength;
    private JButton btnDifficulty;
    private JButton btnCancel;

    public QuerryHikeSpecial(JFrame parent) {
        super(parent);
        setTitle("Вывод походов");
        setContentPane(querryHikePanel);
        setMinimumSize(new Dimension(950, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeBySection, name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnGroups.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите количество групп который были в походе.", null);
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeByGroupCount, name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnCoach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeByCoach, null));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите остановку.", null);
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeByStop, name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnLength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите длину.", null);
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeByLength, name));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnDifficulty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String name = JOptionPane.showInputDialog(parent,
                        "Введите сложность.", null);
                try {
                    taAnswer.append(SQLController.eithQuerry(SQLRequests.selectHikeByDifficulty, name));
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
