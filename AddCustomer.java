package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomer extends JFrame implements ActionListener {
    private JTextField nameField, nationalityField, aadharField, addressField, phoneField;
    private JRadioButton male, female, other;
    private JButton saveButton;

    public AddCustomer() {
       
        setTitle("Add Customer Details");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(900, 600);
        setLocation(300, 150);

       
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(250, 20, 400, 30);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nameLabel.setBounds(50, 80, 100, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 80, 200, 30);
        add(nameField);

       
        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nationalityLabel.setBounds(50, 130, 100, 30);
        add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(200, 130, 200, 30);
        add(nationalityField);

        
        JLabel aadharLabel = new JLabel("Aadhar:");
        aadharLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        aadharLabel.setBounds(50, 180, 100, 30);
        add(aadharLabel);

        aadharField = new JTextField();
        aadharField.setBounds(200, 180, 200, 30);
        add(aadharField);

        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addressLabel.setBounds(50, 230, 100, 30);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(200, 230, 200, 30);
        add(addressField);

        
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        genderLabel.setBounds(50, 280, 100, 30);
        add(genderLabel);

        male = new JRadioButton("Male");
        male.setBounds(200, 280, 70, 30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(280, 280, 80, 30);
        female.setBackground(Color.WHITE);
        add(female);

        other = new JRadioButton("Other");
        other.setBounds(370, 280, 70, 30);
        other.setBackground(Color.WHITE);
        add(other);

       
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        
        JLabel phoneLabel = new JLabel("Phone No:");
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        phoneLabel.setBounds(50, 330, 100, 30);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(200, 330, 200, 30);
        add(phoneField);

       
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        saveButton.setBounds(200, 400, 120, 40);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this);
        add(saveButton);

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel imageLabel = new JLabel(i1);
        imageLabel.setBounds(450, 80, 280, 400);
        add(imageLabel);

        setVisible(true);
    }

    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == saveButton) {
            String name = nameField.getText();
            String nationality = nationalityField.getText();
            String aadhar = aadharField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            String gender = "";
            if (male.isSelected()) gender = "Male";
            else if (female.isSelected()) gender = "Female";
            else if (other.isSelected()) gender = "Other";
            

            if (name.isEmpty() || nationality.isEmpty() || aadhar.isEmpty() || address.isEmpty() || phone.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            
            try{
                DBConnection conn = new DBConnection();
                String query = "INSERT INTO passenger (name, nationality, aadhar, address, gender, phone) VALUES ('" 
               + name + "', '" 
               + nationality + "', '" 
               + aadhar + "', '" 
               + address + "', '" 
               + gender + "', '" 
               + phone + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Customer Details added successfully!");
                setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
