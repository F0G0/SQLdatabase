package org.ngu.service;

import org.ngu.service.delete.DeleteTourist;
import org.ngu.service.querry.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuerryMenu extends JDialog {
    private JPanel querryMenuPanel;
    private JComboBox cbQuerry;
    private JButton btnContinue;
    private JButton btnCancel;

    public QuerryMenu(JFrame parent) {
        super(parent);
        setTitle("Удаление соревнования");
        setContentPane(querryMenuPanel);
        setMinimumSize(new Dimension(850,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbQuerry.getSelectedIndex()) {
                    case 0 -> {
                        QuerryTourists querryTourists = new QuerryTourists(null);
                    }
                    case 1 -> {
                        QuerryCoach querryCoach = new QuerryCoach(null);
                    }
                    case 2 -> {
                        QuerryCompetition querryCompetition = new QuerryCompetition(null);
                    }
                    case 3 -> {
                        QuerryTimeFrame querryTimeFrame = new QuerryTimeFrame(null);
                    }
                    case 4->{
                        QuerryHike querryHike = new QuerryHike(null);
                    }
                    case 5 -> {
                        QuerrySupervisor querrySupervisor = new QuerrySupervisor(null);
                    }
                    case 6 -> {
                        QuerrySchedule querrySchedule = new QuerrySchedule(null);
                    }
                    case 7 -> {
                        QuerryHikeSpecial querryHikeSpecial = new QuerryHikeSpecial(null);
                    }
                    case 8 ->{
                        QuerryTouristHike querryTouristHike = new QuerryTouristHike(null);
                    }
                    case 9 -> {
                        QuerryInstructors querryInstructors = new QuerryInstructors(null);
                    }
                    case 10 ->{
                        QuerrySameInstructor querrySameInstructor = new QuerrySameInstructor(null);
                    }
                    case 11 ->{
                        QuerryTouristAndHikes querryTouristAndHikes = new QuerryTouristAndHikes(null);
                    }
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
