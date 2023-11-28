package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;

public class SeatOption extends JFrame {
    private SeatMap seatMap;
    public SeatOption(){
        this.setTitle("Seat Option");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);

        JButton ordinary = new JButton("Ordinary");
        JButton comfort = new JButton("Comfort");
        JButton business = new JButton("Business");

        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(100,30));
        topPanel.add(new JLabel("Choose Seat Type"));

        mainPanel.setPreferredSize(new Dimension(100,100));
        mainPanel.add(ordinary);
        mainPanel.add(comfort);
        mainPanel.add(business);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.SOUTH);

        //Action Listener
        ordinary.addActionListener(e ->{
            seatMap = new SeatMap();
            seatMap.getOrdinarySeat();
//            topPanel.setVisible(false);
//            mainPanel.setVisible(false);
//            this.add(seatMap.topPanel, BorderLayout.NORTH);
//            this.add(seatMap.mainPanel, BorderLayout.CENTER);
        });
        comfort.addActionListener(e ->{
            seatMap = new SeatMap();
            seatMap.getComfortSeat();
        });
        business.addActionListener(e ->{
            seatMap = new SeatMap();
            seatMap.getBusinessSeat();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeatOption());
        System.out.println("Hello World!");
    }
}
