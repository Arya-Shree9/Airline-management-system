package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    
    public Home() {
        setLayout(null);

        // Fullscreen image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/airfront.png"));
        Image i2 = i1.getImage().getScaledInstance(1600, 900, Image.SCALE_SMOOTH); // Resize image
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1600, 900);
        add(image);

        
        JLabel heading = new JLabel("Air India Welcomes You");
        heading.setBounds(500, 50, 600, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 40));
        heading.setForeground(Color.BLACK); 
        image.add(heading); 
        
        
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        
        JMenu details = new JMenu("Details");
        menubar.add(details);
        
        
        JMenuItem flightDetails = new JMenuItem("Flight Details");
        JMenuItem customerDetails = new JMenuItem("Customer Details");
        JMenuItem reservationDetails = new JMenuItem("Reservation Details");
        JMenuItem bookFlight = new JMenuItem("Book Flight");
        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        JMenuItem ticketCancellation = new JMenuItem("Ticket Cancellation");
        JMenuItem addCustomer = new JMenuItem("Add Customer"); 
        
        
        details.add(flightDetails);
        details.add(customerDetails);
        details.add(reservationDetails);
        details.add(bookFlight);
        details.add(journeyDetails);
        details.add(ticketCancellation);
        details.add(addCustomer); 

       
        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        
        
        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        ticket.add(boardingPass);
        
       
        addCustomer.addActionListener(this);
        flightDetails.addActionListener(this); 

        
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Add Customer")) {
            new AddCustomer(); 
        } else if (command.equals("Flight Details")) {
            new Flight_info(); 
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
