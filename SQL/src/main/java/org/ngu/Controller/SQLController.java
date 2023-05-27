package org.ngu.Controller;

import java.sql.*;
import java.util.Objects;

import static org.ngu.Model.SQLRequests.*;

public class SQLController {
    static ResultSet resultSet;

    public static ResultSet createSection(String name, String supervisorID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addSection);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, supervisorID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet createDay(String schID, String place, String time) throws SQLException, IllegalArgumentException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addScheduleDay);
        preparedStatement.setString(1, schID);
        preparedStatement.setString(2, place);
        preparedStatement.setTimestamp(3, Timestamp.valueOf(time));

        resultSet = preparedStatement.executeQuery();
        if (resultSet != null) {
            return updateAmmount(schID, true);
        }
        return null;
    }

    public static ResultSet updateAmmount(String id, boolean up) throws SQLException {
        String findAmmount = "select \"Schedule\".\"ammount\" from \"Schedule\"\n" +
                "where \"Schedule\".\"id\" = ?";
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(findAmmount);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int ammount = resultSet.getInt("ammount");
        if (up)
            ammount += 1;
        else ammount -= 1;
        preparedStatement = ConnectionController.conn.prepareStatement(updateAmmount);
        preparedStatement.setInt(1, ammount);
        preparedStatement.setString(2, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet updateLength(String id, boolean up) throws SQLException {
        String findAmmount = "select \"Hike\".\"length\" from \"Hike\"\n" +
                "where \"Hike\".\"id\" = ?";
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(findAmmount);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int ammount = resultSet.getInt("length");
        if (up)
            ammount += 1;
        else ammount -= 1;
        preparedStatement = ConnectionController.conn.prepareStatement(updateLength);
        preparedStatement.setInt(1, ammount);
        preparedStatement.setString(2, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet createSchedule(String name, String coachID, String sectionID, String hours) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addSchedule);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, coachID);
        preparedStatement.setString(3, sectionID);
        preparedStatement.setString(4, hours);
        preparedStatement.setString(5, "0");
        return preparedStatement.executeQuery();
    }

    public static ResultSet addHikeGroup(String hikeID, String touristID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addHikeGroup);
        preparedStatement.setString(1, hikeID);
        preparedStatement.setString(2, touristID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet createEmployee(String name, String surname, String middle_name, String sex, String birthday, String salary, String difficulty, String start_date, String specialisation) throws SQLException, IllegalArgumentException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addEmployee);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, middle_name);
        preparedStatement.setString(4, sex);
        preparedStatement.setDate(5, Date.valueOf(birthday));
        preparedStatement.setString(6, salary);
        preparedStatement.setString(7, difficulty);
        preparedStatement.setDate(8, Date.valueOf(start_date));
        preparedStatement.setString(9, specialisation);
        return preparedStatement.executeQuery();

    }

    public static ResultSet createDairy(String hikeID, String time, String text) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addDairy);
        preparedStatement.setString(1, hikeID);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(time));
        preparedStatement.setString(3, text);
        return preparedStatement.executeQuery();
    }

    public static ResultSet createCompetition(String name, String competitor, String sectionID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addCompetition);
        preparedStatement.setString(1, name);
        if (competitor.contains("Спортсмен")) {
            preparedStatement.setString(2, null);
            preparedStatement.setString(3, competitor.substring(0, competitor.indexOf(" ")));
        } else {
            preparedStatement.setString(2, competitor.substring(0, competitor.indexOf(" ")));
            preparedStatement.setString(3, null);
        }
        preparedStatement.setString(4, sectionID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet createTourist(String name, String surname, String middle_name, String sex, String birthday, String passion, String difficulty) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(addTourist);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, middle_name);
        preparedStatement.setString(4, sex);
        preparedStatement.setDate(5, Date.valueOf(birthday));
        preparedStatement.setString(6, passion);
        preparedStatement.setString(7, difficulty);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteTourist(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteTouristFromHikeList);
        preparedStatement.setString(1, id);
        preparedStatement.executeQuery();

        preparedStatement = ConnectionController.conn.prepareStatement(deleteTouristFromGroupList);
        preparedStatement.setString(1, id);
        preparedStatement.executeQuery();


        preparedStatement = ConnectionController.conn.prepareStatement(deleteTouristCompetition);
        preparedStatement.setString(1, id);
        preparedStatement.executeQuery();

        preparedStatement = ConnectionController.conn.prepareStatement(deleteTourist);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();

    }

    public static ResultSet deleteDiary(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteDairy);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteHikeInfo(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteHikeInfo);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteHikeGroup(String hikeID, String touristID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteHikeGroup);
        preparedStatement.setString(1, hikeID);
        preparedStatement.setString(2, touristID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteHike(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectInfoHikeByHikeId);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteHikeInfo(res.getString("id"));
        }
        preparedStatement = ConnectionController.conn.prepareStatement(selectDairyByHikeId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteDiary(res.getString("id"));
        }
        preparedStatement = ConnectionController.conn.prepareStatement(deleteHikeFromHikeList);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();

        preparedStatement = ConnectionController.conn.prepareStatement(deleteHike);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static String returnHikeWithTourist(String hikeID, String touristID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectHikeById);
        preparedStatement.setString(1, hikeID);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("path_name")).append(" ");
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectTouristById);
        preparedStatement.setString(1, touristID);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append(" ");
        }
        return ret.toString();
    }

    public static ResultSet deleteCompetition(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteCompetition);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteScheduleInfo(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteScheduleInfo);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteSchedule(String id) throws SQLException {

        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectInfoScheduleBySchedule);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteScheduleInfo(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(deleteSchedule);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteGroupInfo(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteGroupInfo);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteGroupList(String groupID, String touristID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteGroupList);
        preparedStatement.setString(1, groupID);
        preparedStatement.setString(2, touristID);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteGroups(String sectionID, String groupID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(deleteGroups);
        preparedStatement.setString(1, sectionID);
        preparedStatement.setString(2, groupID);
        return preparedStatement.executeQuery();
    }

    public static String returnGroupWithTourist(String groupID, String touristID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectGroupById);
        preparedStatement.setString(1, groupID);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectTouristById);
        preparedStatement.setString(1, touristID);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append(" ");
        }
        return ret.toString();
    }

    public static String returnSectionWithGroup(String sectionID, String groupID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectSectionById);
        preparedStatement.setString(1, sectionID);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectGroupById);
        preparedStatement.setString(1, groupID);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
        }
        return ret.toString();
    }

    public static ResultSet deleteGroup(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectGroupListByGroupId);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteGroupList(res.getString("group_id"), res.getString("tourist_id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectGroupInfoByGroupId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteGroupInfo(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectGroupsByGroupId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteGroupList(res.getString("id_section"), res.getString("group_id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(deleteGroup);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet deleteSection(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectGroupsBySectionId);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteGroup(res.getString("group_id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectScheduleBySectionId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteSchedule(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectCompetitionBySectionId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteCompetition(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(deleteSection);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();

    }

    public static ResultSet deleteEmployee(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectCompetitionByEmployeeId);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteCompetition(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(selectScheduleByEmployeeId);
        preparedStatement.setString(1, id);
        res = preparedStatement.executeQuery();
        while (res.next()) {
            deleteSchedule(res.getString("id"));
        }

        preparedStatement = ConnectionController.conn.prepareStatement(deleteEmployee);
        preparedStatement.setString(1, id);
        return preparedStatement.executeQuery();
    }

    public static String getEmployeeById(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectEmployeeById);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append(" ");
        }
        return ret.toString();
    }

    public static String getTouristById(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristById);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append(" ");
        }
        return ret.toString();
    }

    public static String getSectionById(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectSectionById);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
        }
        return ret.toString();
    }

    public static String getScheduleById(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectScheduleById);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("name")).append(" ");
        }
        return ret.toString();
    }

    public static String getHikeById(String id) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectHikeById);
        preparedStatement.setString(1, id);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("path_name")).append(" ");
        }
        return ret.toString();
    }

    public static String firstQuerryAllTourist() throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllTourists);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String firstQuerrySectionTourist(String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristBySection);
        preparedStatement.setString(1, name);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String firstQuerryGroupTourist(String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristByGroup);
        preparedStatement.setString(1, name);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String firstQuerrySexTourist(String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristBySex);
        preparedStatement.setString(1, name);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String firstQuerryBdayTourist(Date bday) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristByBday);
        preparedStatement.setDate(1, bday);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String firstQuerryAgeTourist(String bday) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectTouristByAge);
        preparedStatement.setString(1, bday);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String secondQuerry(String querry, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (name != null) {
            preparedStatement.setString(1, name);
        }
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String thirdQuerry(String querry, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (name != null) {
            preparedStatement.setString(1, name);
        }
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String fourhtQuerry(String name, Date start, Date end) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectCoachesByTimeFrameGroup);
        preparedStatement.setString(1, name);
        preparedStatement.setDate(2, start);
        preparedStatement.setDate(3, end);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }
    public static String fifthQuerry(String querry, String groupName, String sectionName, String hikeCount) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        preparedStatement.setString(1, groupName);
        preparedStatement.setString(2, sectionName);
        preparedStatement.setString(3, hikeCount);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }
    public static String fifthQuerryTime(String querry, String groupName, String sectionName, String hikeCount) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        preparedStatement.setDate(1, Date.valueOf(groupName));
        preparedStatement.setString(2, sectionName);
        preparedStatement.setString(3, hikeCount);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        while (res.next()) {
            count++;
            ret.append(res.getString("name")).append(" ");
            ret.append(res.getString("surname")).append(" ");
            ret.append(res.getString("middle_name")).append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }

    public static String seventhQuerry(String querry, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (name != null) {
            preparedStatement.setString(1, name);
        }
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append("Нагрузка в часах: ");
            ret.append(res.getString(1)).append(" ");
            ret.append(res.getString(2)).append(" ");
            ret.append("Тренер: ");
            ret.append(res.getString(3)).append(" ");
            ret.append(res.getString(4)).append(" ");
            ret.append(res.getString(5)).append(" ");
            ret.append("Секция: ");
            ret.append(res.getString(6)).append("\n");
        }
        return ret.toString();
    }

    public static String seventhQuerry(Date start, Date end) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectScheduleByTime);
        preparedStatement.setDate(1, start);
        preparedStatement.setDate(2, end);
        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append("Нагрузка в часах: ");
            ret.append(res.getString(1)).append(" ");
            ret.append(res.getString(2)).append(" ");
            ret.append("Тренер: ");
            ret.append(res.getString(3)).append(" ");
            ret.append(res.getString(4)).append(" ");
            ret.append(res.getString(5)).append(" ");
            ret.append("Секция: ");
            ret.append(res.getString(6)).append("\n");
        }
        return ret.toString();
    }

    public static String eithQuerry(String querry, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (name != null)
            preparedStatement.setString(1, name);

        ResultSet res = preparedStatement.executeQuery();
        StringBuilder ret = new StringBuilder();
        while (res.next()) {
            ret.append(res.getString("path_name")).append("\n");

        }
        return ret.toString();
    }
    public static String eleventhQuerry(String querry, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (name != null) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);
        }
        ResultSet res = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        int columnsNumber = rsmd.getColumnCount();
        while (res.next()) {
            count++;
            for (int i = 1; i <= columnsNumber; i++) {
                if (res.getString(i)!=null) ret.append(res.getString(i)).append(" ");
            }
            ret.append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }
    public static String thirteenthQuerry(String querry, String group, String section) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        if (group != null) {
            preparedStatement.setString(1, group);
            preparedStatement.setString(2, " ");
        }
        if (group != null) {
            preparedStatement.setString(1, " ");
            preparedStatement.setString(2, section);
        }
        ResultSet res = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();
        StringBuilder ret = new StringBuilder();
        int count = 0;
        int columnsNumber = rsmd.getColumnCount();
        while (res.next()) {
            count++;
            for (int i = 1; i <= columnsNumber; i++) {
                if (res.getString(i)!=null) ret.append(res.getString(i)).append(" ");
            }
            ret.append("\n");
        }
        ret.append("Всего: ").append(count);
        return ret.toString();
    }


    public static String getAll(String querry) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(querry);
        ResultSet res = preparedStatement.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();
        StringBuilder ret = new StringBuilder();
        int columnsNumber = rsmd.getColumnCount();
        while (res.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (res.getString(i)!=null) ret.append(res.getString(i)).append(" ");
            }
            ret.append("\n");
        }
        return ret.toString();
    }
    public static ResultSet updateTourist(String id,String name, String surname, String middle_name, String sex, String birthday, String passion, String difficulty) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateTourist);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, middle_name);
        preparedStatement.setString(4, sex);
        preparedStatement.setDate(5, Date.valueOf(birthday));
        preparedStatement.setString(6, passion);
        preparedStatement.setString(7, difficulty);
        preparedStatement.setString(8, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet updateSection(String id, String name, String supervisorID) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateSection);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, supervisorID);
        preparedStatement.setString(3, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet updateSchedule(String id, String name, String coachID, String sectionID, String hours) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateSchedule);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, coachID);
        preparedStatement.setString(3, sectionID);
        preparedStatement.setString(4, hours);
        preparedStatement.setString(5, id);
        return preparedStatement.executeQuery();
    }

    public static ResultSet updateScheduleInfo(String id, String place, String time) throws SQLException, IllegalArgumentException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateInfoSchedule);
        preparedStatement.setString(3, id);
        preparedStatement.setString(1, place);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(time));
        return preparedStatement.executeQuery();
    }

    public ResultSet updateHike(String id, String diffsSuper, String id_coach, String id_athlete, String difficulty, String path_name, String planned) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateHike);
        if (id_coach.isEmpty()) {
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, id_athlete);
        } else {
            preparedStatement.setString(1, id_coach);
            preparedStatement.setString(2, null);
        }
        preparedStatement.setString(3, difficulty);
        preparedStatement.setString(4, path_name);
        preparedStatement.setString(5, planned);
        int diffSuper = Integer.parseInt(diffsSuper);
        int diffHike = Integer.parseInt(difficulty);
        preparedStatement.setString(6, id);
        if (diffHike > diffSuper) {
            throw new SQLException();
        }
        return preparedStatement.executeQuery();
    }
    public ResultSet updateInfoHike(String id, String time, String is_stop, String camp_name) throws SQLException, IllegalArgumentException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateInfoHike);
        preparedStatement.setTimestamp(1, Timestamp.valueOf(time));
        if(Objects.equals((String) is_stop, "да")){
            preparedStatement.setString(2,"Y");
        }
        else preparedStatement.setString(2,"N");
        preparedStatement.setString(3, camp_name);
        preparedStatement.setString(4, id);
        return preparedStatement.executeQuery();
    }
    public static ResultSet updateGroup(String id, String name) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateGroup);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2, id);
        return preparedStatement.executeQuery();

    }
    public static ResultSet updateGroupInfo(String id, String date_start, String date_end, String id_coach) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateGroupInfo);
        preparedStatement.setDate(1, Date.valueOf(date_start));
        preparedStatement.setDate(2, Date.valueOf(date_end));
        preparedStatement.setString(3, id_coach);
        preparedStatement.setString(4, id);
        return preparedStatement.executeQuery();

    }

    public static ResultSet updateEmployee(String id, String name, String surname, String middle_name, String sex, String birthday, String salary, String difficulty, String start_date, String specialisation) throws SQLException, IllegalArgumentException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateEmployee);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, middle_name);
        preparedStatement.setString(4, sex);
        preparedStatement.setDate(5, Date.valueOf(birthday));
        preparedStatement.setString(6, salary);
        preparedStatement.setString(7, difficulty);
        preparedStatement.setDate(8, Date.valueOf(start_date));
        preparedStatement.setString(9, specialisation);
        preparedStatement.setString(10, id);
        return preparedStatement.executeQuery();
    }
    public static ResultSet updateDairy(String id, String time, String text) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateDairy);
        preparedStatement.setString(3, id);
        preparedStatement.setTimestamp(1, Timestamp.valueOf(time));
        preparedStatement.setString(2, text);
        return preparedStatement.executeQuery();
    }
    public static ResultSet updateCompetition(String id, String name, String id_coach, String id_athlete, String id_section) throws SQLException {
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(updateCompetition);
        preparedStatement.setString(1, name);
        if (id_coach.isEmpty()) {
            preparedStatement.setString(2, null);
            preparedStatement.setString(3, id_athlete);
        } else {
            preparedStatement.setString(2, id_coach);
            preparedStatement.setString(3, null);
        }
        preparedStatement.setString(4, id_section);
        preparedStatement.setString(5, id);
        return preparedStatement.executeQuery();
    }
}
