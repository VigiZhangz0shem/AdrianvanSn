import java.util.Date;

public class Data {

    private String serverConnectionStatus;

    public Data(){
        serverConnectionStatus = "Not Connected to Server";
    }

    public void setServerConnectionStatus(String status){
        this.serverConnectionStatus = status;
    }

    public String getServerConnectionStatus(){
        return serverConnectionStatus;
    }
}
