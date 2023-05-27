package org.ngu.service.querry;

import org.ngu.Controller.SQLController;
import org.ngu.Model.SQLRequests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class QuerryTouristHike extends JDialog {
    private JPanel querryHikePanel;
    private JTextArea textArea1;
    private JButton btnSection;
    private JButton btnCancel;
public QuerryTouristHike(JFrame parent) {
    super(parent);
    setTitle("Вывод туристов");
    setContentPane(querryHikePanel);
    setMinimumSize(new Dimension(650, 474));
    setModal(true);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    btnSection.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            textArea1.setText("");
            String name = JOptionPane.showInputDialog(parent,
                    "Введите секцию.", null);
            try {
                textArea1.append(SQLController.secondQuerry(SQLRequests.selectTouristByHikeType, name));
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
