package main.project.flightApplication.GUI;

import main.project.flightApplication.Controller.DBcore;
import main.project.flightApplication.Admin;

import javax.swing.*;
import java.awt.*;

public class AdminPage extends JFrame{
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AdminPage(){
        JLabel title = new JLabel("Admin Portal");
        title.setVerticalAlignment(JLabel.CENTER);                                                  
        setSize(800, 600);
    
        JButton browseButton = new JButton("Browse");
        JButton manageButton = new JButton("Manage");
        JButton printButton = new JButton("Print Users");
        JButton logoutButton = new JButton("Logout");
    
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(browseButton);
        menuPanel.add(manageButton);
        menuPanel.add(printButton);
        menuPanel.add(logoutButton);

        //add a border below the top menu
        menuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        //create a card panel 
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        //create panels for different pages
        JPanel browsePanel = browsePage();
        JPanel managePanel = managePage();
        JPanel printPanel = printPage(this);


        //add panels to card panel
        cardPanel.add(browsePanel, "Browse");
        cardPanel.add(managePanel, "Manage");
        cardPanel.add(printPanel, "Print");

        //set the deafult panel to browse
        cardLayout.show(cardPanel, "Browse");

        //add action listeners to buttons
        browseButton.addActionListener(e -> cardLayout.show(cardPanel, "Browse"));
        manageButton.addActionListener(e -> cardLayout.show(cardPanel, "Manage"));
        printButton.addActionListener(e -> cardLayout.show(cardPanel, "Print"));

        getContentPane().setLayout(new BorderLayout());

        //add border to the right of the left panel 
        cardPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));

        getContentPane().add(BorderLayout.NORTH, menuPanel);
        getContentPane().add(BorderLayout.WEST, cardPanel);

        setVisible(false);

    }

    private JPanel browsePage(){
        JPanel browsePanel = new JPanel(new BorderLayout());

        JButton browseFlights = new JButton("Flights");
        JButton browseCrew = new JButton("Crew");
        JButton browseAircrafts = new JButton("Aircrafts");

        //left panel for browse options
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(150, 0));
        leftPanel.add(browseFlights);
        leftPanel.add(browseCrew);
        leftPanel.add(browseAircrafts);

        browsePanel.add(leftPanel, BorderLayout.WEST);

        JButton searchFlights = new JButton("Search Flights");

        //add right panel for the main content, it should only render the corresponding page when each button is pressed
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        //rightPanel.setPreferredSize(new Dimension(600, 0));
        rightPanel.add(searchFlights);

        browsePanel.add(rightPanel, BorderLayout.EAST);

        return browsePanel;
    }

    private JPanel managePage(){
        JPanel managePanel = new JPanel(new BorderLayout());

        JButton manageFlights = new JButton("Flights");
        JButton manageCrew = new JButton("Crew");
        JButton manageAircrafts = new JButton("Aircrafts");
        JButton manageDestinations = new JButton("Destinations");

        //left panel for manage options
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(150, 0));
        leftPanel.add(manageFlights);
        leftPanel.add(manageCrew);
        leftPanel.add(manageAircrafts);
        leftPanel.add(manageDestinations);

        managePanel.add(leftPanel, BorderLayout.WEST);

        return managePanel;
    }

    private JPanel printPage(AdminPage adminPage){
        JPanel printPanel = new JPanel();
        printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));

        JLabel printLabel = new JLabel("Print Page");
        printPanel.add(printLabel);



        return printPanel;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new AdminPage());
    }
}
