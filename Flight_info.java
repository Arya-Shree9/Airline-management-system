package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Flight_info extends JFrame {

    public Flight_info() {
        setTitle("Flight Information");
        setSize(800, 500);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        String[] columnNames = {"Flight Code", "Flight Name", "Source", "Destination"};
        String[][] data = new String[10][4]; 

        try {
            DBConnection db = new DBConnection(); 
            Statement s = db.getStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM flight");

            
            int i = 0;
            while (rs.next() && i < 10) {
                data[i][0] = rs.getString("f_code");
                data[i][1] = rs.getString("f_name");
                data[i][2] = rs.getString("f_source");
                data[i][3] = rs.getString("f_dest");
                i++;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create JTable with fetched data
        JTable table = new JTable(new DefaultTableModel(data, columnNames));

        // Add JTable inside JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 700, 300);

        add(scrollPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Flight_info();
    }
}
