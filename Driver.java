import java.io.*;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
*/
public class Driver {
//tested code from online
  public static void main(String[] args) throws IOException {
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader br = new BufferedReader(new FileReader("life.csv"));
       String csvFile = "life.csv";
       String line = "";
       String cvsSplitBy = ",";
       FileWriter fw = new FileWriter("life.csv", true);
       PrintWriter out = new PrintWriter(fw);
       Event a = new Event();
       System.out.println("Input event name: ");
       String myName = myReader.readLine();
       a.setName(myName);
       out.append(myName);
       out.append(",");
       System.out.println("Event name is : "+ myName);
       System.out.print("Input your event year :");
       String myYear = myReader.readLine();
       out.append(myYear);
       out.append(",");
       System.out.print("Input your event month :");
       String myMonth = myReader.readLine();
       out.append(myMonth);
       out.append(",");
       System.out.print("Input your event day :");
       String myDay = myReader.readLine();
       out.append(myDay);
       out.append(",");
       a.setDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay));
       System.out.print("Input your event start time :");
       String myStartTime = myReader.readLine();
       out.append(myStartTime);
       out.append(",");
       System.out.print("Input your event end time :");
       String myEndTime = myReader.readLine();
       out.append(myEndTime);
       out.append(",");
       a.setTime(myStartTime, myEndTime);
       System.out.print("Input any additional notes you may have:");
       String myNote = myReader.readLine();
       out.append(myNote);
       out.append("\n");
       out.flush();
       out.close();
       fw.close();
       br.close();
       a.addNote(myNote);
       System.out.println("---------------------------------------------------------------");
       System.out.println(a.toString());
      // System.out.print("Input your start time :");
      // myName = myReader.readLine();
      // System.out.println("Event start time is : "+ myName);
      // String endTime = myRead.readLine();

/*
    TO - DO
      -  figure out how to calculator time from start to end
      -  fix the date function
      -  save the data in CSV file
      */

   }
}
