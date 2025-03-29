package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Journey_details extends JFrame implements ActionListener {
    private JTextField nameField, phoneField;
    private JButton fetchButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public Journey_details() {
        setTitle("Journey Details");
        setSize(800, 500);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 20, 100, 30);
        add(nameLabel);
        
        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 30);
        add(nameField);

        
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(400, 20, 100, 30);
        add(phoneLabel);
        
        phoneField = new JTextField();
        phoneField.setBounds(500, 20, 200, 30);
        add(phoneField);

       
        fetchButton = new JButton("Fetch Details");
        fetchButton.setBounds(320, 60, 150, 30);
        fetchButton.addActionListener(this);
        add(fetchButton);

        
        String[] columnNames = {"Aadhar", "Name", "Phone", "Source", "Destination", "Flight Code", "Travel Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 700, 300);
        add(scrollPane);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            fetchJourneyDetails();
        }
    }

    private void fetchJourneyDetails() {
        tableModel.setRowCount(0); 

        try {
            DBConnection db = new DBConnection();
            String name = nameField.getText();
            String phone = phoneField.getText();
            
            String query = "SELECT * FROM reservation WHERE name = ? AND phone = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("aadhar"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("flight_code"),
                    rs.getString("travel_date")
                });
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Journey_details();
    }
}
