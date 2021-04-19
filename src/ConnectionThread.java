import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class ConnectionThread {

    public String host;
    public int port;
    Socket connectionSocket;
    private Data data;

    public ConnectionThread(Data data) throws IOException {
        data = data;
        try {
            readAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            System.out.println("Host: " + host + "    Port: " + port); //For debugging purposes only.
            connectionSocket = new Socket(host, port);
            System.out.println("12");
            data.setServerConnectionStatus(checkConnection());
            System.out.println("13");
        }catch (IOException e){
            System.out.println("Connection exception!"); //For debugging purposes only.
        }
    }

    //Reads current address for server connection saved by the user.
    public void readAddress() throws IOException {
        try (BufferedReader dirFile = new BufferedReader(new FileReader("address.txt"))){
            String input;
            while ((input = dirFile.readLine()) != null){
                String[] date = input.split("\\|");
                host = date[0];
                System.out.println(host);
                port = Integer.parseInt(date[1]);
                System.out.println(port);
            }
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error reading details from text file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setAddress(String host, int port){
        this.host = host;
        this.port = port;
    }

    public String checkConnection() throws IOException {
        System.out.println("checkConnection called");
        return  sendConnectionRequest("connection check");
    }

    private String sendConnectionRequest(String criteria) throws IOException {

        System.out.println("sendConnectionRequest called");
        String result = "";
        try {
            BufferedReader receipt = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            PrintWriter stringToSend = new PrintWriter(connectionSocket.getOutputStream(), true);

            String response;

            //Send criteria and capture response
            stringToSend.println(criteria);
            System.out.println("Request sent to server at " + LocalDateTime.now() + " -> " + criteria);
            response = receipt.readLine();
            System.out.println("Response received from server at " + LocalDateTime.now() + " -> " + response);
            if (!response.equals("")){
                result = "Connected";
            }
        }catch (SocketTimeoutException e){
            result = "SocketTimeoutException!";
//            result = "Socket timed out! " + e.getMessage();
        }catch (IOException e){
            result = "IOException!";
        }catch (NullPointerException e){
            result = "NullPointerException!";
//            System.out.println("Null Pointer Exception!");
        }
        System.out.println("result = " + result);
        return result;
    }
    
}
