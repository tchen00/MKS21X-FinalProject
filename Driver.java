import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
//tested code from online
  public static void main(String[] args) throws IOException {
       Event a = new Event();
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       System.out.print("Input your event name :");
       String myName = myReader.readLine();
       Event.setName(myName);
       System.out.println("Event name is : "+ myName);
       Event.toString();
      // System.out.print("Input your start time :");
      // myName = myReader.readLine();
      // System.out.println("Event start time is : "+ myName);
      // String endTime = myRead.readLine();

   }
}
