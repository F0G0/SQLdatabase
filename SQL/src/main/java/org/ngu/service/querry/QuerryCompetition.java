package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryCompetition extends JDialog{
    private JPanel querryCompetitionPanel;
    private JTextArea taAnswer;
    private JButton btnSection;
    private JButton btnAll;
    private JButton btnBack;
public QuerryCompetition(JFrame parent) {
    super(parent);
    setTitle("Вывод соревнований");
    setContentPane(querryCompetitionPanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    btnAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            taAnswer.setText("");
            try {
                taAnswer.append(SQLController.thirdQuerry(SQLRequests.selectAllCompetition, null));
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
                taAnswer.append(SQLController.thirdQuerry(SQLRequests.selectCompetitionBySection, name));
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
