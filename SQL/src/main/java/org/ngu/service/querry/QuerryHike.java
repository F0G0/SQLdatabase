package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryHike extends JDialog {
    private JTextArea taAnswer;
    private JButton btnCount;
    private JButton btnHikeName;
    private JButton btnTime;
    private JButton btnStop;
    private JPanel querryHikePanel;
    private JButton btnCancel;

    public QuerryHike(JFrame parent) {
        super(parent);
        setTitle("Вывод соревнований");
        setContentPane(querryHikePanel);
        setMinimumSize(new Dimension(650, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String section = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                String group = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                String count = JOptionPane.showInputDialog(parent,
                        "Введите количество походов.", null);
                try {
                    taAnswer.append(SQLController.fifthQuerry(SQLRequests.selectTouristByHikeCount, group, section, count));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnHikeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String section = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                String group = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                String count = JOptionPane.showInputDialog(parent,
                        "Введите поход.", null);
                try {
                    taAnswer.append(SQLController.fifthQuerry(SQLRequests.SelectTouristsByHikeType, count, group, section));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String section = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                String group = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                String count = JOptionPane.showInputDialog(parent,
                        "Введите время.", null);
                try {
                    taAnswer.append(SQLController.fifthQuerryTime(SQLRequests.selectTouristByHikeTime, count, group, section));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                String section = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                String group = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                String count = JOptionPane.showInputDialog(parent,
                        "Введите остановку.", null);
                try {
                    taAnswer.append(SQLController.fifthQuerry(SQLRequests.selectTouristByStop, count, group, section));
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
