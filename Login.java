package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton btnSubmit, btnReset, btnClose;
    JTextField txtUsername;
    JPasswordField txtPassword;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(20, 20, 100, 20);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 20, 150, 20);
        add(txtUsername);

       
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(20, 60, 100, 20);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 60, 150, 20);
        add(txtPassword);

        
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(20, 100, 100, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

       
        btnReset = new JButton("Reset");
        btnReset.setBounds(140, 100, 100, 30);
        btnReset.addActionListener(this);
        add(btnReset);

        
        btnClose = new JButton("Close");
        btnClose.setBounds(260, 100, 100, 30);
        btnClose.addActionListener(this);
        add(btnClose);

        
        setSize(400, 200);
        setLocation(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSubmit) {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword()); 

            try {
                DBConnection c = new DBConnection();
                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new Home(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == btnClose) {
            setVisible(false);
        } else if (ae.getSource() == btnReset) {
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
