package org.ngu.Model;

public class SQLRequests {
    //ВСЕ СВЯЗАННОЕ С ДОБАВЛЕНИЕМ
    public static String addTourist = "INSERT INTO \"Tourist\" (\"name\",\"surname\",\"middle_name\",\"sex\",\"birthday\",\"passion\",\"difficulty\")" +
            "VALUES (?,?,?,?,?,?,?)";
    public static String addSection = "INSERT INTO \"Section\"(\"name\",\"id_supervisor\")" +
            "VALUES (?,?)";
    public static String addSchedule = "INSERT INTO \"Schedule\"(\"name\",\"id_coach\",\"id_section\",\"hours\",\"ammount\")" +
            "VALUES (?,?,?,?,?)";
    public static String addScheduleDay = "INSERT INTO \"Info_schedule\"(\"id_schedule\",\"Place\",\"time\")" +
            "VALUES(?,?,?)";
    public static String addHike = "INSERT INTO \"Hike\"(\"path_name\",\"id_coach\",\"id_athlete\",\"difficulty\",\"is_planned\",\"length\")" +
            "VALUES(?,?,?,?,?,?)";
    public static String addHikeDay = "INSERT INTO \"Info_hike\"(\"id_hike\",\"time\",\"is_stop\",\"camp_name\")" +
            "VALUES(?,?,?,?)";
    public static String addHikeGroup = "INSERT INTO \"Hike_group\"(\"id_hike\",\"id_tourist\")" +
            "VALUES(?,?)";
    public static String addTouristGroup = "INSERT INTO \"Group_list\"(\"id_group\",\"id_tourist\")" +
            "VALUES(?,?)";
    public static String addGroup = "INSERT INTO \"Group\"(\"name\")" +
            "VALUES(?)";
    public static String addGroups = "INSERT INTO \"Groups\"(\"id_section\", \"id_group\")" +
            "VALUES(?,?)";
    public static String addGroupInfo = "INSERT INTO \"Group_info\"(\"id_group\",\"id_coach\",\"date_start\",\"date_end\")" +
            "VALUES(?,?,?,?)";
    public static String addEmployee = "INSERT INTO \"Employee\"(\"name\",\"surname\",\"middle_name\",\"sex\",\"birthday\",\"salary\",\"difficulty\",\"start_date\",\"specialisation\")" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    public static String addDairy = "INSERT INTO \"Dairy\"(\"id_hike\",\"time\",\"text\")" +
            "VALUES(?,?,?)";
    public static String addCompetition = "INSERT INTO \"Competition\" (\"name\",\"id_coach\",\"id_athlete\",\"id_section\")" +
            "VALUES(?,?,?,?)";


    //ОБНОВЛЕНИЕ ДАННЫХ
    public static String updateAmmount = "update \"Schedule\"\n" +
            "set \"ammount\" = ?" +
            "where \"Schedule\".\"id\" = ?";
    public static String updateLength = "update \"Hike\"\n" +
            "set \"length\" = ?" +
            "where \"length\".\"id\" = ?";
    public static String updateTourist = "update \"Tourist\"" +
            "set \"name\" = ?, \"surname\" = ?, \"middle_name\" = ?, \"sex\" = ?, \"birthday\" = ?, \"passion\" = ?, \"difficulty\"=?" +
            "where \"id\" = ?";
    public static String updateSection = "update \"Section\"" +
            "set \"name\" = ?, \"id_supervisor\" = ?" +
            "where \"id\" = ?";
    public static String updateSchedule = "update \"Schedule\"" +
            "set \"name\" = ?, \"id_coach\"= ?, \"id_section\" = ?, \"hours\" = ?" +
            "where \"id\"=?";
    public static String updateInfoSchedule = "update \"Info_schedule\"" +
            "set \"Place\" = ?, \"time\" = ?" +
            "where \"id\"=?";
    public static String updateInfoHike = "update \"Info_hike\"" +
            "set \"time\"= ?,\"is_stop\"=?,\"camp_name\"=?" +
            "where \"id\" = ?";
    public static String updateHike = "update \"Hike\"" +
            "set \"id_coach\" = ?, \"id_athlete\" = ?, \"difficulty\" = ?, \"path_name\" = ?, \"is_planned\" = ?" +
            "where \"id\" =?";
    public static String updateGroup = "update \"Group\"" +
            "set \"name\" = ?" +
            "where \"id\"= ?";
    public static String updateGroupInfo = "update \"Group_info\"" +
            "set \"date_start\" =?, \"date_end\" = ?, \"id_coach\" = ?" +
            "where \"id_group\"= ?";
    public static String updateEmployee = "update \"Employee\"" +
            "set \"name\" = ?, \"surname\" = ?, \"middle_name\" = ?, \"sex\" = ?, \"birthday\" = ?, \"salary\"=?, \"difficulty\"=?, \"start_date\"=?, \"specialisation\"=?" +
            "where \"id\"=?";
    public static String updateDairy = "update \"Dairy\"" +
            "set \"time\" =?, \"text\" = ?" +
            "where \"id\" = ?";
    public static String updateCompetition = "update \"Competition\"" +
            "set \"name\" = ?, \"id_coach\"=?,\"id_athlete\" = ?, \"id_section\" = ?" +
            "where \"id\"=?";


    //ЧАСТНЫЙ ВЫВОД
    public static String selectGroupIdByName = "select \"Group\".\"id\" from \"Group\" " +
            "where \"Group\".\"name\" = ?";
    public static String selectAllAthletesCoaches = "select * from \"Tourist\" " +
            "where \"Tourist\".\"passion\" = 'athlete' or \"Tourist\".\"passion\" = 'coach'";
    public static String selectInfoHikeByHikeId = "select \"Info_hike\".\"id\" from \"Info_hike\"" +
            "where \"Info_hike\".\"id_hike\" = ?";
    public static String selectDairyByHikeId = "select \"Dairy\".\"id\" from \"Dairy\"" +
            "where \"Dairy\".\"id_hike\" = ?";
    public static String selectInfoScheduleBySchedule = "select * from \"Info_schedule\" " +
            "where \"id_schedule\".\"id\" = ?";
    public static String selectGroupListByGroupId = "select * from \"Group_list\" " +
            "where \"Group_list\".\"id_group\" = ?";
    public static String selectGroupsByGroupId = "select * from \"Groups\" " +
            "where \"Groups\".\"id_group\" = ?";
    public static String selectGroupInfoByGroupId = "select * from \"Group_info\" " +
            "where \"Group_info\".\"id_group\" = ?";
    public static String selectGroupsBySectionId = "select * from \"Groups\" " +
            "where \"Groups\".\"id_section\" = ?";
    public static String selectScheduleBySectionId = "select * from \"Schedule\" " +
            "where \"Schedule\".\"id_section\" = ?";
    public static String selectScheduleByEmployeeId = "select * from \"Schedule\" " +
            "where \"Schedule\".\"id_coach\" = ?";
    public static String selectCompetitionBySectionId = "select * from \"Competition\" " +
            "where \"Competition\".\"id_section\" = ?";
    public static String selectCompetitionByEmployeeId = "select * from \"Competition\" " +
            "where \"Competition\".\"id_coach\" = ?";


    //Вывод по айди
    public static String selectEmployeeById = "select * from \"Employee\" " +
            "where \"Employee\".\"id\" = ?";
    public static String selectSectionById = "select * from \"Section\" " +
            "where \"Section\".\"id\" = ?";
    public static String selectScheduleById = "select * from \"Schedule\" " +
            "where \"Schedule\".\"id\" = ?";
    public static String selectTouristById = "select * from \"Tourist\" " +
            "where \"Tourist\".\"id\" = ?";
    public static String selectHikeById = "select * from \"Hike\" " +
            "where \"Hike\".\"id\" = ?";
    public static String selectGroupById = "select * from \"Group\" " +
            "where \"Group\".\"id\" = ?";


    //ВЫВОД ВСЕХ ДАННЫХ ИЗ ТАБЛИЦЫ
    public static String selectAllEmployee = "select * from \"Employee\"";
    public static String selectAllSections = "select * from \"Section\"";
    public static String selectAllSchedule = "select * from \"Schedule\"";
    public static String selectAllScheduleInfo = "select * from \"Info_schedule\"";
    public static String selectAllHikes = "select * from \"Hike\"";
    public static String selectAllTourists = "select * from \"Tourist\"";
    public static String selectAllGroups = "select * from \"Group\"";
    public static String selectAllGroupInfo = "select * from \"Group_info\"";
    public static String selectAllDiary = "select * from \"Dairy\"";
    public static String selectAllHikeInfo = "select * from \"Info_hike\"";
    public static String selectAllHikeGroups = "select * from \"Hike_group\"";
    public static String selectAllCompetition = "select * from \"Competition\"";
    public static String selectAllGroupList = "select * from \"Group_list\"";


    //УДАЛЕНИЕ ДАННЫХ ПО АЙДИ
    public static String deleteTourist = "delete from \"Tourist\" " +
            "where \"Tourist\".\"id\" = ?";
    public static String deleteGroupList = "delete from \"Group_list\"" +
            "where \"Group_list\".\"id_group\" = ? and \"Group_list\".\"id_tourist\" = ?";
    public static String deleteTouristFromGroupList = "delete from \"Group_list\"" +
            "where \"Group_list\".\"id_tourist\" = ?";
    public static String deleteGroupFromGroupList = "delete from \"Group_list\"" +
            "where \"Group_list\".\"id_group\" = ?";
    public static String deleteHikeGroup = "delete from \"Hike_info\"" +
            "where \"Hike_group\".\"id_hike\" = ? and \"Hike_group\".\"id_tourist\" = ?";
    public static String deleteHikeFromHikeList = "delete from \"Hike_group\"" +
            "where \"Hike_group\".\"id_hike\" = ?";
    public static String deleteTouristFromHikeList = "delete from \"Hike_group\"" +
            "where \"Hike_group\".\"id_tourist\" = ?";
    public static String deleteTouristCompetition = "delete from \"Competition\"" +
            "where \"Competition\".\"id_tourist\" = ?";
    public static String deleteTouristHike = "delete from \"Hike\"" +
            "where \"Hike\".\"id_athlete\" = ?";
    public static String deleteDairy = "delete from \"Dairy\"" +
            "where \"Dairy\".\"id\" = ?";
    public static String deleteHikeInfo = "delete from \"Hike_info\"" +
            "where \"Hike_info\".\"id\" = ?";
    public static String deleteHike = "delete from \"Hike\"" +
            "where \"Hike\".\"id\" = ?";
    public static String deleteCompetition = "delete from \"Competition\"" +
            "where \"Competition\".\"id\" = ?";
    public static String deleteScheduleInfo = "delete from \"Info_schedule\"" +
            "where \"Info_schedule\".\"id\" = ?";
    public static String deleteSchedule = "delete from \"Schedule\"" +
            "where \"Schedule\".\"id\" = ?";
    public static String deleteGroupInfo = "delete from \"Group_info\"" +
            "where \"Group_info\".\"id_group\" = ?";
    public static String deleteGroups = "delete from \"Groups\"" +
            "where \"Groups\".\"id_section\" = ? and \"Groups\".\"id_group\" = ?";
    public static String deleteGroup = "delete from \"Group\"" +
            "where \"Group\".\"id\" = ?";
    public static String deleteSection = "delete from \"Section\"" +
            "where \"Section\".\"id\" = ?";

    public static String deleteEmployee = "delete from \"Employee\"" +
            "where \"Employee\".\"id\" = ?";


    //ЗАПРОСЫ №1
    public static String countAllTourists = "select count(*) from \"Tourist\"";
    public static String selectTouristBySection = "select distinct \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "join \"Group_list\" on \"Group_list\".\"id_tourist\" = \"Tourist\".\"id\"\n" +
            "join \"Group\" on \"Group\".\"id\"=\"Group_list\".\"id_group\"\n" +
            "join \"Groups\" on \"Groups\".\"id_group\" = \"Group\".\"id\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"Section\".\"name\" = ?\n";
    public static String selectTouristByGroup = "select distinct \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "join \"Group_list\" on \"Group_list\".\"id_tourist\" = \"Tourist\".\"id\"\n" +
            "join \"Group\" on \"Group\".\"id\"=\"Group_list\".\"id_group\"\n" +
            "where \"Group\".\"name\" = ?";
    public static String selectTouristBySex = "select unique  \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "where \"Tourist\".\"sex\" = ?";
    public static String selectTouristByBday = "select unique  \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "where \"Tourist\".\"birthday\" = ?\n";

    public static String selectTouristByAge = "select unique  \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "where extract(year from current_date) - extract(year from \"Tourist\".\"birthday\") = ?";


    //ЗАПРОСЫ №2
    public static String selectAllCoaches = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "left join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is null";
    public static String selectAllCoachesBySection = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "join \"Group_info\" on \"Group_info\".\"id_coach\" = \"Employee\".\"id\"\n" +
            "join \"Group\" on \"Group_info\".\"id_group\" = \"Group\".\"id\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"Section\".\"name\" = ?\n";
    public static String selectAllCoachesBySex = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is null and \"sex\" = ?";
    public static String selectAllCoachesBySalary = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is null and \"salary\" = ?";
    public static String selectAllCoachesBySpecialisation = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is null and \"specialisation\" = ?";

    //ЗАПРОС №3
    public static String selectCompetitionBySection = "select unique  \"Competition\".\"name\" from \"Competition\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Competition\".\"id_section\"\n" +
            "where \"Section\".\"name\" = ?";


    //ЗАПРОС №4
    public static String selectCoachesByTimeFrameGroup = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Group_info\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Group_info\".\"id_coach\"\n" +
            "join \"Group\" on \"Group\".\"id\" = \"Group_info\".\"id_group\"\n" +
            "where (\"Group\".\"name\" = ?) and (\"date_start\">?) and (\"date_end\"<?)";


    //ЗАПРОС №5
    public static String selectTouristByHikeCount = "select \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\", count(\"id_tourist\") from \"Group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"Group\".\"name\" = ? or \"Section\".\"name\" = ?\n" +
            "having count(\"id_tourist\") = ?\n" +
            "group by \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\"";
    public static String SelectTouristsByHikeType = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\" from \"Group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where (\"path_name\" = ?) and (\"Group\".\"name\" = ? or \"Section\".\"name\" = ?)\n";
    public static String selectTouristByHikeTime = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\" from \"Group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"time\" = ? and (\"Group\".\"name\" = ? or \"Section\".\"name\" = ?)\n";
    public static String selectTouristByStop = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\" from \"Group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"path_name\" = ? and (\"Groups\".\"name\" = ? or \"Section\".\"name\" = ?)\n";

    //ЗАПРОС №6
    public static String selectSupervisors = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "left join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is not null";
    public static String selectSupervisorsBySalary = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is not null and \"salary\" = ?";
    public static String selectSupervisorsByBday = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is not null and extract(year from \"Employee\".\"birthday\") = ?";
    public static String selectSupervisorsByAge = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is not null and (extract(year from current_date) - extract(year from \"Employee\".\"birthday\") = ?)";
    public static String selectSupervisorsByWorkYear = "select unique  \"Employee\".\"name\", \"Employee\".\"middle_name\", \"Employee\".\"surname\" from \"Employee\"\n" +
            "full join \"Section\" on \"Section\".\"id_supervisor\" = \"Employee\".\"id\" \n" +
            "where \"id_supervisor\" is not null and extract(year from \"Employee\".\"start_date\") = ?";


    //ЗАПРОС №7
    public static String selectAllScheduleWithTime = "select \"ammount\"*\"hours\", \"Schedule\".\"name\", \"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\", \"Section\".\"name\" from \"Schedule\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Schedule\".\"id_coach\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Schedule\".\"id_section\"\n";
    public static String selectAllScheduleWithTimeBySection = "select \"ammount\"*\"hours\", \"Schedule\".\"name\", \"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\", \"Section\".\"name\" from \"Schedule\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Schedule\".\"id_coach\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Schedule\".\"id_section\"\n" +
            "where \"Section\".\"name\" = ?";
    public static String selectScheduleByTime = "select sum(\"hours\"), \"Schedule\".\"name\", \"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\", \"Section\".\"name\" from \"Schedule\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Schedule\".\"id_coach\"\n" +
            "join \"Info_schedule\" on \"Schedule\".\"id\" = \"Info_schedule\".\"id_schedule\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Schedule\".\"id_section\"\n" +
            "where \"Info_schedule\".\"time\">? and \"Info_schedule\".\"time\"<?\n" +
            "group by \"Schedule\".\"name\", \"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\", \"Section\".\"name\"";


    //ЗАПРОСЫ №8-9
    public static String selectHikeBySection = "select distinct \"path_name\" from \"Group\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "where \"Section\".\"name\" = ?";
    public static String selectHikeByGroupCount = "select count(\"id_group\"), \"path_name\" from \"Group\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "having count(\"id_group\") = ?\n" +
            "group by \"path_name\"";
    public static String selectHikeByCoach = "select \"path_name\" from \"Group\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Group_info\" on \"Group\".\"id\" = \"Group_info\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "where \"Hike\".\"id_coach\" = \"Group_info\".\"id_coach\"";
    public static String selectHikeByStop = "select distinct \"Hike\".\"path_name\" from \"Hike\"\n" +
            "join \"Info_hike\" on \"Hike\".\"id\" = \"Info_hike\".\"id_hike\"\n" +
            "where \"camp_name\" = ?";
    public static String selectHikeByLength = "select distinct \"Hike\".\"path_name\" from \"Hike\"\n" +
            "join \"Info_hike\" on \"Hike\".\"id\" = \"Info_hike\".\"id_hike\"\n" +
            "having count(\"id_hike\")>?\n" +
            "group by \"path_name\"";
    public static String selectHikeByDifficulty = "select distinct \"Hike\".\"path_name\" from \"Hike\"\n" +
            "where \"difficulty\" <= ?";

    //ЗАПРОС №10
    public static String selectTouristByHikeType = "select distinct \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "join \"Group_list\" on \"Group_list\".\"id_tourist\" = \"Tourist\".\"id\"\n" +
            "join \"Group\" on \"Group\".\"id\"=\"Group_list\".\"id_group\"\n" +
            "join \"Groups\" on \"Groups\".\"id_group\" = \"Group\".\"id\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "where \"Section\".\"name\" = ?";

    //ЗАПРОС №11
    public static String selectInstructorsByDifficulty = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\",\"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\" from \"Hike\"\n" +
            "left join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "left join \"Tourist\" on \"Tourist\".\"id\" = \"Hike\".\"id_athlete\"\n" +
            "where \"Employee\".\"difficulty\" = ? or \"Tourist\".\"difficulty\" = ?";
    public static String selectInstructorsByStop = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\",\"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\" from \"Hike\"\n" +
            "left join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "left join \"Tourist\" on \"Tourist\".\"id\" = \"Hike\".\"id_athlete\"\n" +
            "join \"Info_hike\" on \"Hike\".\"id\" = \"Info_hike\".\"id_hike\"\n" +
            "where \"Info_hike\".\"camp_name\" = ? or \"Info_hike\".\"camp_name\" = ?\n";
    public static String selectInstructorByHikeName = "select distinct \"Tourist\".\"name\",\"Tourist\".\"surname\",\"Tourist\".\"middle_name\",\"Employee\".\"name\",\"Employee\".\"surname\",\"Employee\".\"middle_name\" from \"Hike\"\n" +
            "left join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "left join \"Tourist\" on \"Tourist\".\"id\" = \"Hike\".\"id_athlete\"\n" +
            "where \"Hike\".\"path_name\" = ? or \"Hike\".\"path_name\" = ? ";


    //ЗАПРОС №12
    public static String selectInsTourByGroup = "select \"path_name\" from \"Group\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Group_info\" on \"Group\".\"id\" = \"Group_info\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "where \"Hike\".\"id_coach\" = \"Group_info\".\"id_coach\" and \"Group\".\"name\" = ?";
    public static String selectInsTourBySection = "select \"path_name\" from \"Group\"\n" +
            "join \"Groups\" on \"Group\".\"id\" = \"Groups\".\"id_group\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "join \"Group_list\" on \"Group\".\"id\" = \"Group_list\".\"id_group\"\n" +
            "join \"Group_info\" on \"Group\".\"id\" = \"Group_info\".\"id_group\"\n" +
            "join \"Tourist\" on \"Tourist\".\"id\" = \"Group_list\".\"id_tourist\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "join \"Hike\" on \"Hike\".\"id\" = \"Hike_group\".\"id_hike\"\n" +
            "join \"Employee\" on \"Employee\".\"id\" = \"Hike\".\"id_coach\"\n" +
            "where \"Hike\".\"id_coach\" = \"Group_info\".\"id_coach\" and \"Section\".\"name\" = ?";


    //ЗАПРОС №13
    public static String selectTouristWithAllHikes = "select \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\" from \"Tourist\"\n" +
            "join \"Group_list\" on \"Group_list\".\"id_tourist\" = \"Tourist\".\"id\"\n" +
            "join \"Group\" on \"Group\".\"id\"=\"Group_list\".\"id_group\"\n" +
            "join \"Groups\" on \"Groups\".\"id_group\" = \"Group\".\"id\"\n" +
            "join \"Section\" on \"Section\".\"id\" = \"Groups\".\"id_section\"\n" +
            "join \"Hike_group\" on \"Tourist\".\"id\" = \"Hike_group\".\"id_tourist\"\n" +
            "where \"Group\".\"name\" = ? or \"Section\".\"name\" = ?\n" +
            "having count(\"Hike_group\".\"id_tourist\") = (select count(\"Hike\".\"id\") from \"Hike\")\n" +
            "group by \"Tourist\".\"id\",\"Tourist\".\"name\", \"Tourist\".\"middle_name\", \"Tourist\".\"surname\"";

}
