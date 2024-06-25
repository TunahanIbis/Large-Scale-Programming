import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        // Enter an id for the serverHost
        final String serverHost = "127.0.0.1";
        // Connect to the serverPort by using its socket number
        final int serverPort = 12345;

        try (Socket socket = new Socket(serverHost, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            // Display a message if there is a connection between client and server
            System.out.println("Connected to the server");

            String userInputLine;
            do {
                // Choose what client to type in order to exit the server
                System.out.print("Type 'stop' to exit): ");
                userInputLine = userInput.readLine();
                out.println(userInputLine);

                // Exit the system if client types 'stop'
            } while (!userInputLine.equalsIgnoreCase("stop"));

            // Exit the system if there is no connection
        } catch (IOException e) {
            System.err.println("Error in connecting to the server: " + e.getMessage());
        }
    }
}
