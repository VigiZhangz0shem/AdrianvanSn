import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class Request {

    //    private String host = "192.168.0.18"; //Address of Ubuntu pc
    private String host;
    private int port;
    private int connected;

    public Request() throws IOException {
        readAddress();
    }
    
    public int getConnected(){
        return connected;
    }

    private void readAddress() throws IOException {
        try (BufferedReader dirFile = new BufferedReader(new FileReader("address.txt"))){
            String input;
            while ((input = dirFile.readLine()) != null){
                String[] date = input.split("\\|");
                host = date[0];
                port = Integer.parseInt(date[1]);
            }
        }catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error reading details from text file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String sendRequest(String criteria){
        return requestSender(criteria);
    }

    private String requestSender(String criteria){
        String result = "";

        try (Socket socket = new Socket(host, port)){

            socket.setSoTimeout(10000);
            BufferedReader receipt = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToSend = new PrintWriter(socket.getOutputStream(), true);

            String response;

            //Send criteria and capture response
            stringToSend.println(criteria);
            System.out.println("Request sent to server at " + LocalDateTime.now() + " -> " + criteria);
            response = receipt.readLine();
            System.out.println("Response received from server at " + LocalDateTime.now() + " -> " + response);
            if (!response.equals("")){
                result = response;
                connected = 1;
            }
        }catch (SocketTimeoutException e){
            JOptionPane.showMessageDialog(null, "Socket timed out!", "Error", JOptionPane.ERROR_MESSAGE);
            connected = 0;
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Couldn't connect to server!", "Error", JOptionPane.ERROR_MESSAGE);
            connected = 0;
        }

        return result;
    }

}
