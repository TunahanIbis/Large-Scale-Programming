import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class BMIClient extends JFrame {
    private JTextField heightField, weightField; // Create height (cm) and weight (kg) text fields
    private JLabel resultLabel; // Create a label to display the result

    public BMIClient() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the close operation
        setLayout(new GridLayout(4, 2)); // Design the grid layout with 4 rows and 2 columns

        // Set the label and button features
        JLabel heightLabel = new JLabel("Height (cm):");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightField = new JTextField();

        // Also add ActionListener to the buttons to make them work
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());

        resultLabel = new JLabel();

        // Add the label, field and buttons to the layout
        add(heightLabel);
        add(heightField);
        add(weightLabel);
        add(weightField);
        add(calculateButton);
        add(resetButton);
        add(resultLabel);
        add(exitButton);

        // Set another features for layout such as size and visibility
        pack();
        setSize(500, 300);
        setVisible(true);
    }

    // Get input from the user with the GUI using ActionListener
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Make the height and weight values double in order to display them with their fractions
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                // Connect client to the server with socket by using port number and host name
                Socket socket = new Socket("localhost", 12345);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                // Send user input to the Server side
                writer.println(height);
                writer.println(weight);

                // Get results from the server and display them on the GUI
                double bmi = Double.parseDouble(reader.readLine());
                String category = reader.readLine();
                resultLabel.setText(bmi + " - " + category); // Display the calculated bmi value and its category

                // Close the connection
                socket.close();
            } catch (IOException | NumberFormatException ex) {
                // Display an error message when the input is in the wrong format, and etc.
                resultLabel.setText("Please enter valid height and weight values.");
            }
        }
    }

    // Design the ActionListener for reset button
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clear out the fields of height, weight and result when the reset button is pressed
            heightField.setText("");
            weightField.setText("");
            resultLabel.setText("");
        }
    }

    // Design the ActionListener for exit button
    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("System is shutting down..."); // Display a message when the system is shutting down
            System.exit(0); // Exit the system when the exit button is pressed
        }
    }

    // Main method
    public static void main(String[] args) {
        new BMIClient();
    }
}
