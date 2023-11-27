package main.project.flightApplication.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SeatMap extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel businessPanel = new JPanel();
    private JPanel comfortPanel = new JPanel();
    private JPanel ordinaryPanel = new JPanel();

    private String clickedButton = null;

    public SeatMap(){
        this.setTitle("Seat Map");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(500,500);
        this.setVisible(true);

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");


        backButton.addActionListener(e ->{
            System.out.println("Go Back");
        });

        nextButton.addActionListener(e -> {
            if(clickedButton == null){
                System.out.println("Please select a button");
            }
            else {
                System.out.println(clickedButton);
            }
        });

        topPanel.setPreferredSize(new Dimension(100,30));
        topPanel.add(backButton);
        topPanel.add(nextButton);

        mainPanel.setLayout(new GridLayout(3,1,0,10));

        businessPanel.setPreferredSize(new Dimension(500,200));
        comfortPanel.setPreferredSize(new Dimension(500,300));
        ordinaryPanel.setPreferredSize(new Dimension(500,700));

        businessPanel.setBackground(Color.black);
        comfortPanel.setBackground(Color.black);
        ordinaryPanel.setBackground(Color.black);

        mainPanel.add(businessPanel);
        mainPanel.add(comfortPanel);
        mainPanel.add(ordinaryPanel);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.SOUTH);

    }

    public void getBusinessSeat(){
        businessPanel.setLayout(new GridLayout(3,8,10,10));
        Integer[] specificColumns = {0, 2, 5, 7};
        int num = 1;
        for (int i = 1; i <= 24; i++) {
            JButton button = new JButton("#" + num);
            JPanel emptySpace = new JPanel();
            int rowIndex = (i - 1) / 8; // Calculate the row index based on the button number
            int columnIndex = (i - 1) % 8; // Calculate the column index based on the button number

            if (rowIndex != 1) {
                if (Arrays.asList(specificColumns).contains(columnIndex)) {
                    businessPanel.add(button);
                    num++;
                } else {
                    emptySpace.setBackground(Color.black);
                    businessPanel.add(emptySpace);
                }
            }
            else {
                emptySpace.setBackground(Color.black);
                businessPanel.add(emptySpace);
            }
            button.addActionListener(e ->{
                button.setForeground(Color.red);
                clickedButton = button.getText();
            });
        }
    }

    public void getComfortSeat(){
        comfortPanel.setLayout(new GridLayout(3,8,10,10));
        Integer[] specificColumns = {0, 2, 5, 7};
        int num = 9;
        for (int i = 1; i <= 24; i++) {
            JButton button = new JButton("#" + num);
            JPanel emptySpace = new JPanel();
            int columnIndex = (i - 1) % 8; // Calculate the column index based on the button number

            if (Arrays.asList(specificColumns).contains(columnIndex)) {
                comfortPanel.add(button);
                num++;
            } else {
                emptySpace.setBackground(Color.black);
                comfortPanel.add(emptySpace);
            }
            button.addActionListener(e ->{
                button.setForeground(Color.red);
                clickedButton = button.getText();
            });
        }
    }

    public void getOrdinarySeat(){
        ordinaryPanel.setLayout(new GridLayout(3,8,10,10));
        Integer[] specificColumns = {3, 4};
        int num = 21;
        for (int i = 1; i <= 24; i++) {
            JButton button = new JButton("#" + num);
            JPanel emptySpace = new JPanel();
            int columnIndex = (i - 1) % 8; // Calculate the column index based on the button number

            if (Arrays.asList(specificColumns).contains(columnIndex)) {
                emptySpace.setBackground(Color.black);
                ordinaryPanel.add(emptySpace);
            } else {
                ordinaryPanel.add(button);
                num++;
            }
            button.addActionListener(e ->{
                button.setForeground(Color.red);
                clickedButton = button.getText();
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SeatMap());
        System.out.println("Hello World!");
    }
}
