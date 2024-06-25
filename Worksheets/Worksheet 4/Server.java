import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        // Create a server socket listening on port 12345
        final int portNumber = 12345;

        // Display a message if the server is listening to the port
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for the port " + portNumber);

            // Accept the connections made by client
            try (Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                // Display a message if there is a connection between client and server
                System.out.println("Client has connected to the server");

                // Make the server shut down if client types 'stop'
                String inputLine;
                while (!(inputLine = in.readLine()).equalsIgnoreCase("stop")) {
                    System.out.println("Client: " + inputLine);
                }

                // Display a message if server is shutting down
                System.out.println("Server is shutting down");

                // Display an error message if none of above is happening
            } catch (IOException e) {
                System.err.println("Error in connecting to the client: " + e.getMessage());
            }

            // Exit the system if there is not a connection
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}
