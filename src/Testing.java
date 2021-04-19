import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Testing {

    public static void main(String[] args) throws IOException {

//        List<String> dates = new ArrayList<>();
//        for (int i = 0; i < 5; i++){
//            dates.add("rates" + (LocalDate.now().minusDays(i).toString().replaceAll("[^0-9]", "")));
//        }
//
//        for (String d : dates){
//            System.out.println(d);
//        }

        String date = "rates20210125|USD";

//        System.out.println(date.contains("-"));
//        date = "2021-01-25";
//        System.out.println(date.contains("-"));

//        System.out.println("\"USD\"");

        Socket socket = new Socket("localhost", 5000);
        BufferedReader receipt = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter stringToSend = new PrintWriter(socket.getOutputStream(), true);
//
        String response;

        //Send criteria and capture response
        stringToSend.println(date);
        System.out.println("Request sent to server at " + LocalDateTime.now() + " -> " + date);
        response = receipt.readLine();
        System.out.println("Response received from server at " + LocalDateTime.now() + " -> " + response);

    }

}
