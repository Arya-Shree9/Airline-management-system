package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BookFlight extends JFrame implements ActionListener {
    private JTextField aadharField, phoneField;
    private JLabel nameValue, nationalityValue, flightCodeLabel;
    private JComboBox<String> sourceBox, destinationBox;
    private JButton saveButton, fetchAadharButton, fetchFlightButton;
    private JSpinner dateSpinner;

    public BookFlight() {
        setTitle("Book Flight");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(900, 500);
        setLocation(300, 150);

        
        JLabel heading = new JLabel("Book Flight");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(320, 20, 500, 30);
        add(heading);

        
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setBounds(50, 80, 100, 30);
        add(aadharLabel);
        
        aadharField = new JTextField();
        aadharField.setBounds(200, 80, 200, 30);
        add(aadharField);
        
        fetchAadharButton = new JButton("Fetch Details");
        fetchAadharButton.setBounds(420, 80, 150, 30);
        fetchAadharButton.addActionListener(this);
        add(fetchAadharButton);

       
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 120, 100, 30);
        add(nameLabel);
        
        nameValue = new JLabel("");  
        nameValue.setBounds(200, 120, 200, 30);
        nameValue.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        add(nameValue);

       
        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 160, 100, 30);
        add(nationalityLabel);
        
        nationalityValue = new JLabel("");
        nationalityValue.setBounds(200, 160, 200, 30);
        nationalityValue.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        add(nationalityValue);

        
        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setBounds(50, 200, 100, 30);
        add(phoneLabel);
        
        phoneField = new JTextField();
        phoneField.setBounds(200, 200, 200, 30);
        add(phoneField);

      
        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(50, 240, 100, 30);
        add(sourceLabel);
        
        sourceBox = new JComboBox<>();
        sourceBox.setBounds(200, 240, 200, 30);
        add(sourceBox);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(50, 280, 100, 30);
        add(destinationLabel);
        
        destinationBox = new JComboBox<>();
        destinationBox.setBounds(200, 280, 200, 30);
        add(destinationBox);

        fetchFlightButton = new JButton("Fetch Flights");
        fetchFlightButton.setBounds(420, 260, 150, 30);
        fetchFlightButton.addActionListener(this);
        add(fetchFlightButton);

      
        JLabel flightCode = new JLabel("Flight Code:");
        flightCode.setBounds(50, 320, 100, 30);
        add(flightCode);
        
        flightCodeLabel = new JLabel();
        flightCodeLabel.setBounds(200, 320, 200, 30);
        flightCodeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Optional
        add(flightCodeLabel);

       
        JLabel dateLabel = new JLabel("Travel Date:");
        dateLabel.setBounds(50, 360, 100, 30);
        add(dateLabel);
        
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setBounds(200, 360, 200, 30);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);
        add(dateSpinner);

        
        saveButton = new JButton("Book Flight");
        saveButton.setBounds(200, 400, 150, 40);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this);
        add(saveButton);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(550, 70, 500, 410);
        add(imageLabel);
     
           
        loadLocations();
        setVisible(true);
    }

    
    private void loadLocations() {
        try {
            DBConnection conn = new DBConnection();
            ResultSet rs = conn.s.executeQuery("SELECT DISTINCT f_source, f_dest FROM flight");
            while (rs.next()) {
                sourceBox.addItem(rs.getString("f_source"));
                destinationBox.addItem(rs.getString("f_dest"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void fetchPassengerDetails() {
        try {
            DBConnection conn = new DBConnection();
            String aadhar = aadharField.getText();
            ResultSet rs = conn.s.executeQuery("SELECT name, nationality, phone FROM passenger WHERE aadhar='" + aadhar + "'");
            if (rs.next()) {
                nameValue.setText(rs.getString("name"));
                nationalityValue.setText(rs.getString("nationality"));
                phoneField.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    private void fetchFlightCode() {
        try {
            DBConnection conn = new DBConnection();
            String source = (String) sourceBox.getSelectedItem();
            String destination = (String) destinationBox.getSelectedItem();
            ResultSet rs = conn.s.executeQuery("SELECT f_code FROM flight WHERE f_source='" + source + "' AND f_dest='" + destination + "'");
            if (rs.next()) {
                flightCodeLabel.setText(rs.getString("f_code"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchAadharButton) {
            fetchPassengerDetails();
        } else if (ae.getSource() == fetchFlightButton) {
            fetchFlightCode();
        } else if (ae.getSource() == saveButton) {
    try {
        DBConnection conn = new DBConnection();

       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(dateSpinner.getValue());

        String query = "INSERT INTO reservation (aadhar, name, nationality, phone, source, destination, flight_code, travel_date) VALUES ('" + 
                aadharField.getText() + "', '" + nameValue.getText() + "', '" + nationalityValue.getText() + "', '" + phoneField.getText() + "', '" + 
                sourceBox.getSelectedItem() + "', '" + destinationBox.getSelectedItem() + "', '" + flightCodeLabel.getText() + "', '" + formattedDate + "')";

        System.out.println("SQL Query: " + query); // Debugging: Print query before execution
        conn.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Flight Booked Successfully!");
        setVisible(false);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
