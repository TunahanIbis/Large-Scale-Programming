import java.io.*;
import java.net.*;

public class BMIServer {
    public static void main(String[] args) {
        try {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                // Display a message when the server is ready to make a connection with client
                System.out.println("Server is listening...");

                // Display a message when the connection is made via socket
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client has connected to the server: " + clientSocket.getInetAddress());

                    // Handle client request in a separate thread
                    Thread clientThread = new Thread(new ClientHandler(clientSocket));
                    clientThread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Design a handler method to process the input that is coming from the client
class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        // Get user inputs from the client
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                // Read the client input
                String heightStr = reader.readLine();
                String weightStr = reader.readLine();

                // Perform the BMI calculation on the server
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                double bmi = calculateBMI(height, weight);
                String category = BMItoCategory(bmi);

                // Send results to the client
                writer.println(bmi);
                writer.println(category);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Make the necessary calculations on the Server side
    private double calculateBMI(double height, double weight) {
        // Perform the usual BMI calculation
        return weight / Math.pow(height / 100, 2);
    }

    // Convert BMI value to a category
    private String BMItoCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Healthy";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}
