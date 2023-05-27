package org.ngu.service.delete;

import org.ngu.Controller.ConnectionController;
import org.ngu.Controller.SQLController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

import static org.ngu.Model.SQLRequests.selectAllCompetition;
import static org.ngu.Model.SQLRequests.selectAllDiary;

public class DeleteCompetition extends JDialog{
    private JComboBox cbCompetition;
    private JPanel delteCompetitionPanel;
    private JButton btnDelete;
    private JButton btnCancel;

    public DeleteCompetition(JFrame parent) {
        super(parent);
        setTitle("Удаление соревнования");
        setContentPane(delteCompetitionPanel);
        setMinimumSize(new Dimension(650,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCompetition();
            }
        });


        setVisible(true);

    }

    private void updateList() throws SQLException {
        cbCompetition.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllCompetition);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                if((i==3) && (!Objects.equals(resultSet.getString(i), "null"))) tmp.append(SQLController.getEmployeeById(resultSet.getString(i))).append(" ");
                else if((i==4) && (!Objects.equals(resultSet.getString(i), "null"))) tmp.append(SQLController.getTouristById(resultSet.getString(i))).append(" ");
                else if(i==5) tmp.append(SQLController.getSectionById(resultSet.getString(i))).append(" ");
                else tmp.append(resultSet.getString(i)).append(" ");
            }
            cbCompetition.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
    private void deleteCompetition() {
        String tmp = (String) cbCompetition.getSelectedItem();
        try {
            SQLController.deleteCompetition(tmp.substring(0, tmp.indexOf(" ")));
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
