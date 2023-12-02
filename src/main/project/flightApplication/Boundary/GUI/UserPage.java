package main.project.flightApplication.Boundary.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import main.project.flightApplication.Controller.FlightController;
import main.project.flightApplication.Entity.Flight;

public class UserPage extends JPanel {
    private MainGUI mainGUI;
    public JPanel top = new JPanel();
    public JPanel center = new JPanel();
    public JPanel side = new JPanel();
    private JLabel label = new JLabel("User Page");
    private FlightController flightController = new FlightController();
    private JComboBox<String> originField;
    private JComboBox<String> destField;
    private JComboBox<String> dateField;
    private ArrayList<String> destination = flightController.getAllDest();
    private ArrayList<String> departureOrigin = flightController.getAllDepartureOrigin();
    private ArrayList<String> dates = flightController.getAllDepartureDates();

    public UserPage(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        JButton browse = new JButton("Search Flights");

        top.setPreferredSize(new Dimension(100, 40));
        top.add(label);
        top.setBackground(Color.decode("#63ace5"));
        side.setPreferredSize(new Dimension(150, 100));
        side.setLayout(new FlowLayout(FlowLayout.LEFT));
        side.add(browse);


        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(side, BorderLayout.WEST);

        browse.addActionListener(e -> {
            label.setText("Book a flight");
            Browse();

        });
    }

    public void addBack(){
        JButton back = new JButton("Back to Main");
        side.add(back);
        back.addActionListener(e->{
            mainGUI.dispose();
            MainGUI newGui = new MainGUI();
        });
    }

    public void Browse(){
        //header panel with all the information displayed
        JPanel info = new JPanel();
        //origin selection
        JLabel originLabel = new JLabel("From:");
        originField = new JComboBox<>(departureOrigin.toArray(new String[departureOrigin.size()]));
        updateOriginField();
        //destination selection
        JLabel destLabel = new JLabel("To:");
        destField = new JComboBox<>(destination.toArray(new String[destination.size()]));
        updateDestinationField();
        //date field
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JComboBox<>();
        //search and fetch buttons
        JButton searchButton = new JButton("Search");
        JButton fetchDatesButton = new JButton("Fetch Dates");

        //action listener to fetch dates
        fetchDatesButton.addActionListener(e -> {
            String selectedOrigin = (String) originField.getSelectedItem();
            String selectedDestination = (String) destField.getSelectedItem();
            ArrayList<String> fetchedDates = flightController.getDatesByOriginAndDestination(selectedOrigin, selectedDestination);
            updateDateField(fetchedDates);
        });

        JPanel display = new JPanel(); // Panel to hold flight details
        display.setBackground(Color.decode("#e7eff6"));

        searchButton.addActionListener(e -> {
            mainGUI.setSize(new Dimension(1030,600));
            System.out.println("search clicked");
            String selectedOrigin = (String) originField.getSelectedItem();
            String selectedDestination = (String) destField.getSelectedItem();
            String selectedDate = (String) dateField.getSelectedItem();
            ArrayList<Flight> flights = flightController.getFlightByInput(selectedOrigin, selectedDestination, selectedDate);

            display.removeAll(); // Clear previous flight details

            for (Flight flight : flights) {
                // Create a panel for each flight
                JPanel flightPanel = new JPanel();
                flightPanel.setBackground(Color.decode("#e7eff6"));
                flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.X_AXIS)); // Horizontal layout

                JLabel flightImageLabel = createImageLabel("flightPic.jpg"); // Load an image for each flight

                // Create a panel for flight details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Vertical layout

                JButton bookButton = new JButton("Book Flight");

                bookButton.addActionListener(bookingEvent ->{
                    bookFlight(flight);
                });

                // Add flight details to the details panel
                JLabel flightNumberLabel = new JLabel("Flight Number: " + flight.getFlightNumber());
                JLabel theOriginLabel = new JLabel("Origin: " + flight.getOrigin());
                JLabel destinationLabel = new JLabel("Destination: " + flight.getDestination());
                JLabel departureLabel = new JLabel("Departure: " + flight.getDepartureDate());
                JLabel arrivalLabel = new JLabel("Arrival:      " + flight.getArrivalDate());

                detailsPanel.add(flightNumberLabel);
                detailsPanel.add(theOriginLabel);
                detailsPanel.add(destinationLabel);
                detailsPanel.add(departureLabel);
                detailsPanel.add(arrivalLabel);

                // Add the image panel and details panel to the flight panel
                flightPanel.add(flightImageLabel, BorderLayout.EAST);
                flightPanel.add(detailsPanel, BorderLayout.CENTER);
                flightPanel.add(bookButton, BorderLayout.SOUTH);

                display.add(flightPanel); // Add each flight panel to the display panel
            }

            display.revalidate(); // Refresh the display
            display.repaint();

            // Update the UI after adding new flight details

            center.add(display, BorderLayout.CENTER);
            revalidate();
            repaint();

        });

        //properties of the info bar
        info.setBackground(Color.decode("#adcbe3"));
        info.setPreferredSize(new Dimension(500, 40));
        info.add(originLabel);
        info.add(originField);
        info.add(destLabel);
        info.add(destField);
        info.add(fetchDatesButton);
        info.add(dateLabel);
        info.add(dateField);
        info.add(searchButton);

        //adding info bar to layout
        center.setLayout(new BorderLayout());
        center.add(info, BorderLayout.NORTH);
        revalidate();
        repaint();
    }



    public void updateDestinationField() {
        int size = destination.size();
        String[] locationArray = destination.toArray(new String[size]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(locationArray);
        destField.setModel(model);
    }

    public void updateOriginField() {
        int size = departureOrigin.size();
        String[] departureOriginArray = departureOrigin.toArray(new String[size]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(departureOriginArray);
        originField.setModel(model);
    }

    public void updateDateField(ArrayList<String> dates) {
        String[] datesArray = dates.toArray(new String[0]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(datesArray);
        dateField.setModel(model);
    }

    public JLabel createImageLabel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        return new JLabel(scaledIcon);
    }

    private void bookFlight(Flight flight){
        SeatOption seatOption = new SeatOption(flight);
    }

    public void forUsers(String name){
        JButton managePromos = new JButton("Promotions");
        JButton creditCard = new JButton("Get Credit Card");
        JButton registeredLogout = new JButton("Logout");

        managePromos.addActionListener(e -> {
            PromotionManager promotionPage = new PromotionManager();
        });
        creditCard.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thank you for applying for the credit card!");
        });
        registeredLogout.addActionListener(e ->{
            JOptionPane.showMessageDialog(this, "Successfully Logged Out!");
            mainGUI.dispose();
            MainGUI newGui = new MainGUI();
        });

        side.add(creditCard);
        side.add(managePromos);
        side.add(registeredLogout);
        label.setText("Welcome "+ name +"!");

    }





}
