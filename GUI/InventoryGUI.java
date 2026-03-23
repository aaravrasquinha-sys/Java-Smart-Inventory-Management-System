package gui;

import core.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InventoryGUI {

    private InventoryManager manager = new InventoryManager();
    private ArrayList<Owner> owners = new ArrayList<>();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int buttonWidth = 400;
    int buttonHeight = 100;
    int horizontalCenter = screenSize.width / 2 - buttonWidth / 2;
    int verticalGap = 30;

    Font buttonFont = new Font("Arial", Font.BOLD, 24);
    Font textFont = new Font("Arial", Font.BOLD, 52);

    public InventoryGUI() {
        createMainMenu();
    }

    public void createMainMenu() {
        JFrame mainFrame = new JFrame("Inventory Management System");
        mainFrame.setSize(screenSize.width, screenSize.height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.getContentPane().setBackground(Color.WHITE);

        JButton ownerButton = new JButton("Login as Owner");
        JButton customerButton = new JButton("Login as Customer");
        JButton exitButton = new JButton("Exit");

        ownerButton.setBounds(horizontalCenter, screenSize.height / 2 - buttonHeight - verticalGap, buttonWidth, buttonHeight);
        customerButton.setBounds(horizontalCenter, screenSize.height / 2 + verticalGap, buttonWidth, buttonHeight);
        exitButton.setBounds(screenSize.width - 150, 20, 120, 50);

        ownerButton.setFont(buttonFont);
        customerButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        exitButton.setBackground(Color.RED);
        exitButton.setBorder(null);

        ownerButton.addActionListener(e -> {
            mainFrame.dispose();
            loginOwnerMenu();
        });

        customerButton.addActionListener(e -> {
            mainFrame.dispose();
            customerMenu();
        });

        exitButton.addActionListener(e -> System.exit(0));

        mainFrame.add(ownerButton);
        mainFrame.add(customerButton);
        mainFrame.add(exitButton);

        mainFrame.setVisible(true);
    }

    private void loginOwnerMenu() {
        JFrame frame = new JFrame("Owner Login");
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton createBtn = new JButton("Create Owner");
        JButton backBtn = new JButton("Back");

        userLabel.setBounds(500, 300, 200, 50);
        userField.setBounds(700, 300, 300, 50);

        passLabel.setBounds(500, 400, 200, 50);
        passField.setBounds(700, 400, 300, 50);

        loginBtn.setBounds(700, 500, 150, 60);
        createBtn.setBounds(860, 500, 200, 60);
        backBtn.setBounds(20, 20, 100, 40);

        userLabel.setFont(buttonFont);
        passLabel.setFont(buttonFont);
        loginBtn.setFont(buttonFont);
        createBtn.setFont(buttonFont);

        backBtn.setBackground(Color.RED);

        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            for (Owner o : owners) {
                if (o.getUsername().equals(username) && o.getPassword().equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                    frame.dispose();
                    ownerMenu();
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Invalid Credentials");
        });

        createBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter username:");
            String password = JOptionPane.showInputDialog("Enter password:");

            if (username != null && password != null) {
                owners.add(new Owner(username, password));
                JOptionPane.showMessageDialog(frame, "Owner created");
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            createMainMenu();
        });

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginBtn);
        frame.add(createBtn);
        frame.add(backBtn);

        frame.setVisible(true);
    }

    private void ownerMenu() {
        JFrame frame = new JFrame("Owner Menu");
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);

        JButton add = new JButton("Add Item");
        JButton view = new JButton("View Items");
        JButton remove = new JButton("Remove Item");
        JButton logout = new JButton("Logout");

        add.setBounds(300, 300, 250, 80);
        view.setBounds(600, 300, 250, 80);
        remove.setBounds(900, 300, 250, 80);
        logout.setBounds(20, 20, 120, 40);

        add.setFont(buttonFont);
        view.setFont(buttonFont);
        remove.setFont(buttonFont);

        logout.setBackground(Color.RED);

        add.addActionListener(e -> addItem());
        view.addActionListener(e -> viewItems());
        remove.addActionListener(e -> removeItem());

        logout.addActionListener(e -> {
            frame.dispose();
            createMainMenu();
        });

        frame.add(add);
        frame.add(view);
        frame.add(remove);
        frame.add(logout);

        frame.setVisible(true);
    }

    private void addItem() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField qty = new JTextField();
        JTextField price = new JTextField();

        Object[] fields = {
                "ID:", id,
                "Name:", name,
                "Quantity:", qty,
                "Price:", price
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Item", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            manager.addItem(new NonPerishableItem(
                    Integer.parseInt(id.getText()),
                    name.getText(),
                    Integer.parseInt(qty.getText()),
                    Double.parseDouble(price.getText())
            ));
        }
    }

    private void viewItems() {
        StringBuilder sb = new StringBuilder();

        for (InventoryItem item : manager.getInventory()) {
            sb.append(item.getDetails()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void removeItem() {
        String id = JOptionPane.showInputDialog("Enter ID:");
        if (id != null) {
            manager.removeItem(Integer.parseInt(id));
        }
    }

    private void customerMenu() {
        JFrame frame = new JFrame("Customer");
        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);

        JButton search = new JButton("Search");
        JButton buy = new JButton("Buy");

        search.setBounds(400, 300, 250, 80);
        buy.setBounds(700, 300, 250, 80);

        search.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name:");

            for (InventoryItem item : manager.getInventory()) {
                if (item.getName().equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(frame, item.getDetails());
                    return;
                }
            }

            JOptionPane.showMessageDialog(frame, "Item not found");
        });

        buy.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter name:");
            int qty = Integer.parseInt(JOptionPane.showInputDialog("Quantity:"));
            manager.buyItem(name, qty);
        });

        frame.add(search);
        frame.add(buy);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new InventoryGUI();
    }
}
