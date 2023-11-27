package main.project.flightApplication.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import main.project.flightApplication.Admin;

public class DisplayUsers extends JFrame {
    private Admin admin;

    public DisplayUsers() {

        admin = new Admin();
        // Fetch users from the database
        ArrayList<String> users = admin.getRegisteredUsers();
        System.out.println("Users:");
        for(String user : users){
            System.out.println(user);
        }

        // Create table model and set column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Users");

        // Add users to the table model
        for (String user : users) {
            model.addRow(new Object[]{user});
        }

        // Create the table
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add scroll pane to the frame
        this.add(scrollPane, BorderLayout.CENTER);

        this.setTitle("User List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DisplayUsers::new);
    }
}
