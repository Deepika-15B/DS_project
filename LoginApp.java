import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginApp extends Frame implements ActionListener {
    private TextField usernameField;
    private TextField passwordField;
    private Button loginButton;
    private Label messageLabel;

    public LoginApp() {
        // Set up the frame
        setTitle("Login");
        setSize(400, 300);
        setLayout(new FlowLayout());

        // Set background color for the frame
        setBackground(new Color(144, 238, 144)); // Light color for the background

        // Create UI components
        usernameField = new TextField(20);
        passwordField = new TextField(20);
        passwordField.setEchoChar('*'); // Hide password input
        loginButton = new Button("Login");
        messageLabel = new Label();

        // Set colors for components
        usernameField.setBackground(new Color(255, 255, 255)); // White background for text fields
        usernameField.setForeground(new Color(0, 0, 0)); // Black text

        passwordField.setBackground(new Color(255, 255, 255)); // White background for text fields
        passwordField.setForeground(new Color(0, 0, 0)); // Black text

        loginButton.setBackground(new Color(240, 128, 128)); // Blue color for the button
        loginButton.setForeground(new Color(0, 0, 0)); // White text

        messageLabel.setForeground(new Color(255, 0, 0)); // Red color for error messages

        // Add components to the frame
        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column
        inputPanel.add(new Label("Username or Email:"));
        inputPanel.add(usernameField);
        inputPanel.add(new Label("Password:"));
        inputPanel.add(passwordField);

        // Add the input panel and message label to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH); // Add button at the bottom
        add(messageLabel, BorderLayout.NORTH); // Add message label at the top
        // Add action listener for the button
        loginButton.addActionListener(this);

        // Set frame properties
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if both fields are not empty
        if (!username.isEmpty() && !password.isEmpty()) {
            // Navigate to HospitalManagementSystemAWT
            new HospitalManagementSystemAWT();
            dispose(); // Close the login window
        } else {
            // Display invalid credentials message
            messageLabel.setText("Invalid credentials. Please try again.");
        }
    }

    public static void main(String[] args) {
        new LoginApp();
    }
}


