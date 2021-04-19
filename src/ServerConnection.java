import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ServerConnection implements Runnable {

    static int counter;
    static boolean connected;
    static String host;
    static int port;
    static Socket connectionSocket;

    ServerConnection() throws IOException {
        connected = false;
        counter = 1;
        readAddress();
    }

    @Override
    public void run() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                counter++;
//                System.out.println("ServerConnection counter = " + counter);
                try{
                    connectionSocket = new Socket(host, port);
                    connected = connectionSocket.isConnected();
                    System.out.println("1ServerConnection connected = " + connected);
                    stop();
                }catch (NullPointerException | IOException e){
                    connected = false;
                    System.out.println("2ServerConnection connected = " + connected);
                }
            }
        }, 0, 2000);


    }

    private void stop() throws IOException {
        connectionSocket.close();
    }

    //Reads current address for server connection saved by the user.
    public void readAddress() throws IOException {
        try (BufferedReader dirFile = new BufferedReader(new FileReader("address.txt"))){
            String input;
            while ((input = dirFile.readLine()) != null){
                String[] date = input.split("\\|");
                host = date[0];
                port = Integer.parseInt(date[1]);
                System.out.println("ServerConnection host: " + host + " port: " + port);
            }
        }catch (FileNotFoundException e){
//            JOptionPane.showMessageDialog(null, "Error reading details from text file!", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("ServerConnection FileNotFoundException");
        }
    }

}
