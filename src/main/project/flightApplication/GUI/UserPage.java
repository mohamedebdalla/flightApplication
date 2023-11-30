package main.project.flightApplication.GUI;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import main.project.flightApplication.Flight;
import main.project.flightApplication.Controller.FlightController;

public class UserPage extends JPanel {
    private MainGUI mainGUI;
    public JPanel top = new JPanel();
    public JPanel center = new JPanel();
    public JPanel side = new JPanel();
    private JLabel label = new JLabel("User Page");
    // private JLabel imageLabel = createImageLabel("flightPic.jpg");
    private FlightController flightController = new FlightController();
    private JComboBox<String> originField;
    private JComboBox<String> destField;
    private JComboBox<String> dateField;
    private ArrayList<String> destination = flightController.getAllDest();
    private ArrayList<String> departureOrigin = flightController.getAllDepartureOrigin();
    private ArrayList<String> dates = flightController.getAllDepartureDates();
    private String[] origin = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    private String[] desination = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
    

    public UserPage(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        JButton browse = new JButton("Search Flights");
        JButton cancel = new JButton("Cancel Flight");

        top.setPreferredSize(new Dimension(100, 40));
        top.add(label);
        top.setBackground(Color.BLUE);
        center.setBackground(Color.CYAN);
        side.setPreferredSize(new Dimension(150, 100));
        side.setLayout(new FlowLayout(FlowLayout.LEFT));
        side.add(browse);
        side.add(cancel);

        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(side, BorderLayout.WEST);

        browse.addActionListener(e -> {
            label.setText("Book a flight");
            tryBrowse();

        });

        cancel.addActionListener(e -> {
            label.setText("Cancel a flight");
            Cancel();
        });
    }

    public void registeredOptions() {
        JButton apply = new JButton("Get Credit Card");
        side.add(apply);
        apply.addActionListener(e -> {
            System.out.println("Apply for cc");
        });
    }

    public void tryBrowse(){
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
        display.setBackground(Color.GRAY);

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
                flightPanel.setBackground(Color.lightGray);
                flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.X_AXIS)); // Horizontal layout

                // Create a panel for the image
                JLabel flightImageLabel = createImageLabel("flightPic.jpg"); // Load an image for each flight
                //JPanel imagePanel = new JPanel();
                /*imagePanel.setBackground(Color.GREEN);
                imagePanel.add(flightImageLabel);*/

                // Create a panel for flight details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setBackground(Color.MAGENTA);
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Vertical layout

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
        info.setBackground(Color.green);
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

    public void Browse() {
        JPanel info = new JPanel();
        JPanel display = new JPanel(new GridLayout(0, 1)); // Panel to hold flight details
        JLabel originLabel = new JLabel("From:");
        originField = new JComboBox<>(departureOrigin.toArray(new String[departureOrigin.size()]));
        updateOriginField();
        JLabel destLabel = new JLabel("To:");
        destField = new JComboBox<>(destination.toArray(new String[destination.size()]));
        updateDestinationField();
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JComboBox<>();
        JButton searchButton = new JButton("Search");
        JButton fetchDatesButton = new JButton("Fetch Dates");

        fetchDatesButton.addActionListener(e -> {
            String selectedOrigin = (String) originField.getSelectedItem();
            String selectedDestination = (String) destField.getSelectedItem();
            ArrayList<String> fetchedDates = flightController.getDatesByOriginAndDestination(selectedOrigin, selectedDestination);
            updateDateField(fetchedDates);
        });

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
                flightPanel.setBackground(Color.lightGray);
                flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.X_AXIS)); // Horizontal layout
        
                // Create a panel for the image
                JLabel flightImageLabel = createImageLabel("flightPic.jpg"); // Load an image for each flight
                JPanel imagePanel = new JPanel();
                imagePanel.setBackground(Color.GREEN);
                imagePanel.add(flightImageLabel);
        
                // Create a panel for flight details
                JPanel detailsPanel = new JPanel();
                detailsPanel.setBackground(Color.MAGENTA);
                detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS)); // Vertical layout
        
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
                flightPanel.add(imagePanel);
                flightPanel.add(detailsPanel);
        
                display.add(flightPanel); // Add each flight panel to the display panel
            }
        
            display.revalidate(); // Refresh the display
            display.repaint();
    
            

            // Update the UI after adding new flight details
            center.removeAll();
            center.add(display, BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        info.setBackground(Color.green);
        info.setPreferredSize(new Dimension(500, 40));
        info.add(originLabel);
        info.add(originField);
        info.add(destLabel);
        info.add(destField);
        info.add(fetchDatesButton);
        info.add(dateLabel);
        info.add(dateField);
        info.add(searchButton);

        center.setLayout(new BorderLayout());
        center.add(info, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    public void Cancel() {
        // Similar structure to the Browse method, but for canceling flights
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
}
