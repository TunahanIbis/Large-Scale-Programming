import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Function for converting "cm" to "m" and calculating the BMI
public class Worksheet2 {
    public double calculate(double heightCm, double weightKg) {
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }

    // String values for BMI calculations
    public String BMItoCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Healthy";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}

// Setting frame, field and label features
class BMICalculatorApp {
    private JFrame frame;
    private JTextField heightField, weightField;
    private JLabel resultLabel;

    public BMICalculatorApp() {
        frame = new JFrame("BMI Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel heightLabel = new JLabel("Height (cm):");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonListener());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());

        resultLabel = new JLabel();

        frame.add(heightLabel);
        frame.add(heightField);
        frame.add(weightLabel);
        frame.add(weightField);
        frame.add(calculateButton);
        frame.add(resetButton);
        frame.add(resultLabel);
        frame.add(exitButton);

        frame.pack();
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    // Setting Action Listener in order to give results according to user input
    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                Worksheet2 calculator = new Worksheet2();
                double bmi = calculator.calculate(height, weight);
                String category = calculator.BMItoCategory(bmi);

                resultLabel.setText(bmi + " - " + category);
                // resultLabel.setText(String.format("%.1f - %s", bmi, category));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid height and weight.");
            }
        }
    }

    // Setting "Reset" button's property to reset the inputs
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            heightField.setText("");
            weightField.setText("");
            resultLabel.setText("");
        }
    }

    // Setting "Exit" button's property to exit the system
    private class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Main method
    public static void main(String[] args) {
        new BMICalculatorApp();
    }
}