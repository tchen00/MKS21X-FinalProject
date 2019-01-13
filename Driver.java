import java.io.*;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
*/
public class Driver {
//tested code from online
  public static void main(String[] args) throws IOException {
    /*
    Date a = new Date();
    System.out.println("" + a.validDate(2019,1,1));
    System.out.println("" + a.validDate(2020,1,11));
    System.out.println("" + a.validDate(3091,10,21));
    System.out.println("" + a.validDate(2018,1,1));
    System.out.println("" + a.validDate(0000,1,1));
    System.out.println("" + a.validDate(2001,1,1));
    System.out.println("" + a.validDate(2019,21,1));
    System.out.println("" + a.validDate(2019,2,29));
    System.out.println("" + a.validDate(2019,111,1));
    System.out.println("" + a.validDate(2019,11,32));
    */
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader br = new BufferedReader(new FileReader("life.csv"));
       String csvFile = "life.csv";
       String line = "";
       String cvsSplitBy = ",";
       FileWriter fw = new FileWriter("life.csv", true);
       PrintWriter out = new PrintWriter(fw);
       System.out.println("Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add Event \n \t 2. Print Calendar \n \t 3. Delete Event" );
       System.out.println("Please input your option: ");
       String input = myReader.readLine();
       int inpu = Integer.parseInt(input);
       if (inpu == 1) {
         Event a = new Event();
         Date b = new Date();
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
         boolean valiDate = false;
         while (!valiDate){
           if (b.validDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay))){
             a.setDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay));
             valiDate = true;
           } else {
             System.out.println("Please make sure you are inputting a valid date: \n NOTE: This calendar will not let you dwell in the past so don't try to set an event from  the past");
             System.out.print("Input your event year :");
             myYear = myReader.readLine();
             out.append(myYear);
             out.append(",");
             System.out.print("Input your event month :");
             myMonth = myReader.readLine();
             out.append(myMonth);
             out.append(",");
             System.out.print("Input your event day :");
             myDay = myReader.readLine();
             out.append(myDay);
             out.append(",");
           }
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
       }
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
}
