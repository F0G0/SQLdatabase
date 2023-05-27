package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryTouristAndHikes extends JDialog {
    private JPanel querryPanel;
    private JTextArea taAnswer;
    private JButton btnAllHike;
    private JButton btnCancel;
    private JButton btnSection;
    private JButton btnGroup;
    private String group;
    private String section;

    public QuerryTouristAndHikes(JFrame parent) {
        super(parent);
        setTitle("Вывод туристов");
        setContentPane(querryPanel);
        setMinimumSize(new Dimension(650, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                section = JOptionPane.showInputDialog(parent,
                        "Введите секцию.", null);
                group = "";
                if (!section.isEmpty())
                    taAnswer.append("Секция: " + section);
            }
        });
        btnGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                group = JOptionPane.showInputDialog(parent,
                        "Введите группу.", null);
                section = "";
                if(!group.isEmpty())
                    taAnswer.append("Группа: "+ group);
            }
        });
        btnAllHike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taAnswer.setText("");
                if(!group.isEmpty())
                    taAnswer.append("Группа: "+ group+"\n");
                if (!section.isEmpty())
                    taAnswer.append("Секция: " + section+"\n");
                try {
                    taAnswer.append(SQLController.thirteenthQuerry(SQLRequests.selectTouristWithAllHikes, group, section));
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
