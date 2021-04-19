import java.io.IOException;
import java.net.ConnectException;

public class Main {

    public static HomeScreenV2 home;
    public static ConnectionThread ct;
    public static Data data;

    public static void main(String[] args) throws IOException {

//        ServerConnection serverConnection = new ServerConnection();
//        Thread serverThread = new Thread(serverConnection);
//        serverThread.start();
        Data data = new Data();
        ConnectionThread connected = new ConnectionThread(data);
        home = new HomeScreenV2(data);
        Thread homeThread = new Thread(home);
        homeThread.start();
//        connected.setDaemon(true);
//        connected.start();
//        ServerDialog serverDialog = new ServerDialog();
    }
}