package org.ngu.service;

import org.ngu.service.create.*;
import org.ngu.service.delete.*;
import org.ngu.service.update.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JDialog{
    private JPanel menuPanel;
    private JButton btnAddData;
    private JComboBox cbAddData;
    private JButton btnEditData;
    private JComboBox cbEditData;
    private JButton btnDeleteData;
    private JButton btnQuerry;
    private JComboBox cbDeleteData;

    public MainMenu(JFrame parent) {
        super(parent);
        setTitle("Вход");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(500,600));
        setModal(false);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnAddData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbAddData.getSelectedItem().toString()) {
                    case "Турист" -> {
                        CreateTouristForm touristForm = new CreateTouristForm(null);
                    }
                    case "Работник" -> {
                        CreateEmployeeForm employeeForm = new CreateEmployeeForm(null);
                    }
                    case "Секция" -> {
                        CreateSectionForm sectionForm = new CreateSectionForm(null);
                    }
                    case "Группа" -> {
                        CreateGroupForm groupForm = new CreateGroupForm(null);
                    }
                    case "Расписание" -> {
                        CreateScheduleForm scheduleForm = new CreateScheduleForm(null);
                    }
                    case "Соревнование" -> {
                        CreateCompetitionForm competitionForm = new CreateCompetitionForm(null);
                    }
                    case "Поход" -> {
                        CreateHikeForm hikeForm = new CreateHikeForm(null);
                    }
                    case "Дневник" -> {
                        CreateDiaryForm diaryForm = new CreateDiaryForm(null);
                    }
                    case "Добавить туриста в группу" -> {
                        CreateGroupListForm groupListForm = new CreateGroupListForm(null);
                    }
                    case "Добавить туриста в поход" -> {
                        CreateHikeGroupForm hikeGroupForm = new CreateHikeGroupForm(null);
                    }
                }
            }
        });


        btnDeleteData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbDeleteData.getSelectedItem().toString()) {
                    case "Турист" -> {
                        DeleteTourist touristForm = new DeleteTourist(null);
                    }
                    case "Работник" -> {
                        DeleteEmployee deleteEmployee = new DeleteEmployee(null);
                    }
                    case "Секция" -> {
                        DeleteSection sectionForm = new DeleteSection(null);
                    }
                    case "Группа" -> {
                        DeleteGroup deleteGroup = new DeleteGroup(null);
                    }
                    case "Расписание" -> {
                        DeleteSchedule deleteSchedule = new DeleteSchedule(null);
                    }
                    case "Запись расписания" -> {
                        DeleteInfoSchedule deleteInfoSchedule = new DeleteInfoSchedule(null);
                    }
                    case "Соревнование" -> {
                        DeleteCompetition deleteCompetition = new DeleteCompetition(null);
                    }
                    case "Поход" -> {
                        DeleteHike hikeForm = new DeleteHike(null);
                    }
                    case "Дневник" -> {
                        DeleteDairy diaryForm = new DeleteDairy(null);
                    }
                    case "Удалить туриста из группы" -> {
                        DeleteGroupList deleteGroupList = new DeleteGroupList(null);
                    }
                    case "Удалить туриста из похода" -> {
                        DeleteHikeGroup hikeGroupForm = new DeleteHikeGroup(null);
                    }
                    case "Удалить остановку похода" ->{
                        DeleteInfoHike infoHikeForm = new DeleteInfoHike(null);
                    }
                }
            }
        });
        btnQuerry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuerryMenu querryMenu = new QuerryMenu(null);
            }
        });
        btnEditData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbEditData.getSelectedItem().toString()) {
                    case "Туристы" -> {
                        UpdateTouristForm updateTouristForm = new UpdateTouristForm(null);
                    }
                    case "Секции" ->{
                        UpdateSectionForm updateSectionForm = new UpdateSectionForm(null);
                    }
                    case "Расписания" ->{
                        UpdateSchedule updateSchedule = new UpdateSchedule(null);
                    }
                    case "День расписания"->{
                        UpdateScheduleInfo updateScheduleInfo = new UpdateScheduleInfo(null);
                    }
                    case "Работники" ->{
                        UpdateEmployee updateEmployee = new UpdateEmployee(null);
                    }
                    case "Группы" ->{
                        UpdateGroup updateGroup = new UpdateGroup(null);
                    }
                    case "Информация группы" ->{
                        UpdateGroupInfo updateGroupInfo = new UpdateGroupInfo(null);
                    }
                }
            }
        });
        setVisible(true);
    }
}
