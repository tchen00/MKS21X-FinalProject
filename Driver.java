import java.io.*;
import java.util.*;

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
    try {
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader br = new BufferedReader(new FileReader("life.csv"));
       String csvFile = "life.csv";
       String line = "";
       String cvsSplitBy = ",";
       FileWriter fw = new FileWriter("life.csv", true);
       PrintWriter out = new PrintWriter(fw);
       System.out.println("Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add an Event \n \t 2. Print Calendar View with Events \n \t 3. List All Upcoming Events \n \t 4. Delete Event (IN THE FUTURE) \n \t 5. Exit Program" );
       System.out.println("Please input your option: ");
       String input = myReader.readLine();
       int inpu = Integer.parseInt(input);
       while (inpu > 5){
         System.out.println("Please enter a valid option: ");
         input = myReader.readLine();
         inpu = Integer.parseInt(input);
       }
       if (inpu == 2){
    //     System.out.print("\033[H\033[2J");
    //     System.out.flush();  
           // INSERT CODE FOR CALENDAR VIEWS
           System.out.println("");
      //     String[] arg = {};
    //       CalendarViews.main(arg);
         }
          else if (inpu == 3){
           System.out.println("");
        } else if (inpu == 4){
           System.out.println("Please wait patiently for this future. Patience is bliss!");
         } else if (inpu == 5){
           System.out.println("Thank you for being organized!");
           System.exit(1);
         }
       else if (inpu == 1) {
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
     }
     }catch (Exception e){
       System.out.println("Please put in valid parameters");
       System.exit(1);
       }
      // System.out.print("Input your start time :");
      // myName = myReader.readLine();
      // System.out.println("Event start time is : "+ myName);
      // String endTime = myRead.readLine();

   }
 }
