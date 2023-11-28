    package main.project.flightApplication.GUI;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.util.ArrayList;
    import main.project.flightApplication.Admin;
    import main.project.flightApplication.RegisteredUser;

    public class DisplayUsers extends JFrame {
        private Admin admin;

        public DisplayUsers() {

            admin = new Admin();
            // Fetch users from the database
            ArrayList<RegisteredUser> users = admin.getRegisteredUsers();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Username");
            model.addColumn("Password");
            model.addColumn("Email");
            model.addColumn("Address");
            // Add columns for other attributes as needed

            for (RegisteredUser user : users) {
                model.addRow(new Object[]{
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getAddress(),
                        // Add other attributes as needed
                }); 
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
            SwingUtilities.invokeLater(() -> new DisplayUsers());
        }
    }
