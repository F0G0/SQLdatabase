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

import static org.ngu.Model.SQLRequests.selectAllTourists;

public class DeleteTourist extends JDialog{
    private JPanel deleteTouristPanel;
    private JButton btnDelete;
    private JButton btnCancel;
    private JComboBox cbTourist;

    public DeleteTourist(JFrame parent) {
    super(parent);
    setTitle("Удаление туриста");
    setContentPane(deleteTouristPanel);
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
            deleteTourist();
        }
    });
    setVisible(true);
}
    private void updateList() throws SQLException {
        cbTourist.removeAllItems();
        PreparedStatement preparedStatement = ConnectionController.conn.prepareStatement(selectAllTourists);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData  mData = preparedStatement.getMetaData();
        StringBuilder tmp = new StringBuilder();
        while (resultSet.next()) {
            for (int i = 1; i <= mData.getColumnCount(); ++i) {
                tmp.append(resultSet.getString(i)).append(" ");
            }
            cbTourist.addItem(tmp.toString());
            tmp.setLength(0);
        }
    }
    private void deleteTourist() {
        String tmp = (String) cbTourist.getSelectedItem();
        try {
            SQLController.deleteTourist(tmp.substring(0, tmp.indexOf(" ")));
            updateList();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Турист является организатором похода. Сначала удалите поход.", "Окей", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Турист успешно удален.", "Окей", JOptionPane.PLAIN_MESSAGE);
    }
}
