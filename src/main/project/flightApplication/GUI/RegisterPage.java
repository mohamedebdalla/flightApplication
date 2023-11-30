package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.project.flightApplication.RegisteredUser;
import main.project.flightApplication.Controller.DBcore;

public class RegisterPage extends JFrame{
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField addressField;
    DBcore dbcore = DBcore.getInstance();
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public RegisterPage(){
        JLabel title = new JLabel("Register");
        title.setVerticalAlignment(JLabel.CENTER);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //create components 
        JLabel nameLabel = new JLabel("Name");
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel emailLabel = new JLabel("Email");
        JLabel addressLabel = new JLabel("Address");

        nameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(60);
        addressField = new JTextField(200);  

        JButton registerButton = new JButton("Create account");
        registerButton.addActionListener(e -> {
            if(register()){
                showRegisteredPage(nameField.getText());
            }
        });

        //create layout panels
        JPanel mainPanel = new JPanel(new BorderLayout());

        //panel for the title 
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Register");
        titlePanel.add(titleLabel);

        //panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(new JLabel());

        //add form panel and button to main panel
        mainPanel.add(BorderLayout.NORTH, titlePanel);
        mainPanel.add(BorderLayout.CENTER, panel);
        mainPanel.add(BorderLayout.SOUTH, registerButton);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        //add panel to card panel
        cardPanel.add(mainPanel, "Registration");
        //add layout to the frame
        getContentPane().add(cardPanel);
        cardLayout.show(cardPanel, "Registration");
    }

    //function to register user
    private boolean register(){
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String email = emailField.getText();
        String address = addressField.getText();

        //validate the input fields
        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || address.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //insert the user into the database
        try{
            String query = "INSERT INTO users (name, username, password, email, address, userType) VALUES (?, ?, ?, ?, ?, ?)";

            try(PreparedStatement preparedStatement = dbcore.getConnection().prepareStatement(query)){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, address);
                preparedStatement.setString(6, "registered user");

                int rowsAffected = preparedStatement.executeUpdate();
                

                if(rowsAffected > 0){
                    JOptionPane.showMessageDialog(this, "User created successfully");
                    RegisteredUser newUser = new RegisteredUser(name, username, password, email, address);
                    //showRegisteredPage(name);
                    return true;
                } else{
                    JOptionPane.showMessageDialog(this, "Error creating user", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating user", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void showRegisteredPage(String name){
        JLabel greetingLabel = new JLabel("Welcome " + name + "!");
        //create buttons for new page
        JButton bookFlightButton = new JButton("Book a flight");
        JButton managePromos = new JButton("See promotions");
        JButton creditCard = new JButton("Apply for credit card");
        JButton registeredLogout = new JButton("Logout");

        //add components to the layout 
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.add(bookFlightButton);
        menuPanel.add(managePromos);
        menuPanel.add(creditCard);
        menuPanel.add(registeredLogout);
        menuPanel.add(greetingLabel);

        //add action listeners to buttons
        bookFlightButton.addActionListener(e -> showBookFlightsPanel());
        managePromos.addActionListener(e -> showPromosPanel());
        creditCard.addActionListener(e -> showCreditCardPanel());

        cardPanel.add(menuPanel, "Registered");
        cardLayout.show(cardPanel, "Registered");

        getContentPane().add(cardPanel);
        revalidate();
        repaint();
    }

    private JPanel bookFlights(){
        JPanel bookFlightPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Book a flight");
        titlePanel.add(titleLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        bookFlightPanel.add(BorderLayout.NORTH, titlePanel);
        bookFlightPanel.add(BorderLayout.CENTER, panel);

        return bookFlightPanel;
    }

    private JPanel seePromos(){
        JPanel promosPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Promotions");
        titlePanel.add(titleLabel);

        // Add more components for additional options
        JButton monthlyNewsletterButton = new JButton("See Monthly Newsletter");
        JButton freeCompanionTicketButton = new JButton("Check for Free Companion Ticket");
        JButton loungeDiscountButton = new JButton("Get Lounge Discount Promo Code");

        // Add action listeners to the buttons
        monthlyNewsletterButton.addActionListener(e -> showMonthlyNewsletter());
        freeCompanionTicketButton.addActionListener(e -> checkFreeCompanionTicket());
        loungeDiscountButton.addActionListener(e -> showLoungeDiscountPromoCode());

        // Create a panel to hold the buttons
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(3, 1));
        optionsPanel.add(monthlyNewsletterButton);
        optionsPanel.add(freeCompanionTicketButton);
        optionsPanel.add(loungeDiscountButton);

        // Add components to the main panel
        promosPanel.add(BorderLayout.NORTH, titlePanel);
        promosPanel.add(BorderLayout.CENTER, optionsPanel);

        return promosPanel;
    }

    private JPanel creditCard(){
        JPanel creditCardPanel = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Credit card");
        titlePanel.add(titleLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        creditCardPanel.add(BorderLayout.NORTH, titlePanel);
        creditCardPanel.add(BorderLayout.CENTER, panel);

        return creditCardPanel;
    }

    private void showBookFlightsPanel() {
        JPanel bookFlightsPanel = bookFlights();
    
        // Create a new JPanel for the "Book flights" page and add the components
        JPanel pagePanel = new JPanel(new BorderLayout());
        pagePanel.add(bookFlightsPanel, BorderLayout.CENTER);
    
        // Add the "previous" button to navigate back to the "Registered" page
        JButton prevButton = new JButton("Back to menu");
        prevButton.addActionListener(e -> cardLayout.show(cardPanel, "Registered"));
        pagePanel.add(prevButton, BorderLayout.SOUTH);
    
        // Add the new panel to the card panel with a unique name
        cardPanel.add(pagePanel, "BookFlightsPage");
    
        // Show the new panel in the card layout
        cardLayout.show(cardPanel, "BookFlightsPage");
    
        // Repaint and revalidate the frame
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void showPromosPanel(){
        JPanel promosPanel = seePromos();

        //create a new JPanel for the "Promotions" page and add the components
        JPanel pagePanel = new JPanel(new BorderLayout());
        pagePanel.add(promosPanel, BorderLayout.CENTER);

        //add the "previous" button to navigate back to the "Registered" page
        JButton prevButton = new JButton("Back to menu");
        prevButton.addActionListener(e -> cardLayout.show(cardPanel, "Registered"));
        pagePanel.add(prevButton, BorderLayout.SOUTH);

        //add the new panel to the card panel with a unique name
        cardPanel.add(pagePanel, "PromosPage");

        //show the new panel in the card layout
        cardLayout.show(cardPanel, "PromosPage");

        //repaint and revalidate the frame
        getContentPane().revalidate();
        getContentPane().repaint();

    }

    private void showMonthlyNewsletter() {
        // Implement logic to display the monthly newsletter
        JOptionPane.showMessageDialog(this, "Displaying Monthly Newsletter");
    }

    private void checkFreeCompanionTicket() {
        // Implement logic to check if the user has a free companion ticket
        JOptionPane.showMessageDialog(this, "Checking for Free Companion Ticket");
    }

    private void showLoungeDiscountPromoCode() {
        // Implement logic to generate and display the lounge discount promo code
        JOptionPane.showMessageDialog(this, "Your Lounge Discount Promo Code is: XYZ123");
    }

    private void showCreditCardPanel(){
        JOptionPane.showMessageDialog(this, "Thank you for applying for the credit card!");
        /*JPanel creditCardPanel = creditCard();

        //create a new JPanel for the "Credit Card" page and add the components
        JPanel pagePanel = new JPanel(new BorderLayout());
        pagePanel.add(creditCardPanel, BorderLayout.CENTER);

        //add the "previous" button to navigate back to the "Registered" page
        JButton prevButton = new JButton("Back to menu");
        prevButton.addActionListener(e -> cardLayout.show(cardPanel, "Registered"));
        pagePanel.add(prevButton, BorderLayout.SOUTH);

        //add the new panel to the card panel with a unique name
        cardPanel.add(pagePanel, "CreditCardPage");

        //show the new panel in the card layout
        cardLayout.show(cardPanel, "CreditCardPage");

        //repaint and revalidate the frame
        getContentPane().revalidate();
        getContentPane().repaint();*/
    }
    
}
