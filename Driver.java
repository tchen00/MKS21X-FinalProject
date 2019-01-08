import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {
//tested code from online
  public static void main(String[] args) throws IOException {
       Event a = new Event();
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       System.out.println("Input event name: ");
       String myName = myReader.readLine();
       a.setName(myName);
       System.out.println("Event name is : "+ myName);
       System.out.print("Input your event year :");
       String myYear = myReader.readLine();
       System.out.print("Input your event month :");
       String myMonth = myReader.readLine();
       System.out.print("Input your event day :");
       String myDay = myReader.readLine();
       a.setDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay));
       System.out.print("Input your event start time :");
       String myStartTime = myReader.readLine();
       System.out.print("Input your event end time :");
       String myEndTime = myReader.readLine();
       a.setTime(myStartTime, myEndTime);
       System.out.print("Input any additional notes you may have:");
       String myNote = myReader.readLine();
       a.addNote(myNote);
       System.out.println("---------------------------------------------------------------");
       System.out.println(a.toString());
      // System.out.print("Input your start time :");
      // myName = myReader.readLine();
      // System.out.println("Event start time is : "+ myName);
      // String endTime = myRead.readLine();

   }
}
