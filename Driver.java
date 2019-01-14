import java.io.*;
import java.util.*;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
*/
public class Driver {
//tested code from online
  public static void main(String[] args) throws IOException {
    try {
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
       BufferedReader br = new BufferedReader(new FileReader("life.csv"));
       String csvFile = "life.csv";
       String line = "";
       String cvsSplitBy = ",";
       FileWriter fw = new FileWriter(csvFile, true);
       PrintWriter out = new PrintWriter(fw);
       System.out.println("Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add an Event \n \t 2. Print Calendar View with Events \n \t 3. List All Upcoming Events \n \t 4. Delete Event (IN THE FUTURE) \n \t 5. Exit Program" );
       System.out.println("Please input your option: ");
       String input = myReader.readLine();
       int inpu = Integer.parseInt(input);
       // NOT AN OPTION
       while (inpu > 5){
         System.out.println("Please enter a valid option: ");
         input = myReader.readLine();
         inpu = Integer.parseInt(input);
       }
       // VIEWING THE CALENDAR
       if (inpu == 2) {
        // CalendarViews test = new CalendarViews();
         System.out.println("Please select your view option: \n \t 1. Year \n \t 2. Month \n \t 3. Week \n **NOTE: Must be lowercase -- for now**");
         String myView = myReader.readLine();
         if (myView.equals("year")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           System.out.println(yeary);
           int yearr = Integer.parseInt(yeary);
           CalendarViews test = new CalendarViews("year",csvFile);
           System.out.println(test.printYear(yearr));
         } else if (myView.equals("month")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           int yearr = Integer.parseInt(yeary);
           System.out.println("Please input the month you would like to see: ");
           String monthy = myReader.readLine();
           int monthh = Integer.parseInt(monthy);
           CalendarViews test = new CalendarViews("month",csvFile);
           System.out.println(test.printMonth(monthh, yearr));
         } else if (myView.equals("week")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           int yearr = Integer.parseInt(yeary);
           System.out.println("Please input the month you would like to see: ");
           String monthy = myReader.readLine();
           int monthh = Integer.parseInt(monthy);
           System.out.println("Please input a day in the week: ");
           String dayye = myReader.readLine();
           int dayy = Integer.parseInt(dayye);
           CalendarViews test = new CalendarViews("week",csvFile);
           test.printWeek(dayy, monthh, yearr);
         } else {
           System.out.println("Please put in a valid option");
           System.exit(1);
         }
       }
       // LISTING THE EVENTS
       else if (inpu == 3) {
         OurCalendar calendar = new OurCalendar(csvFile);
         System.out.println("How would you like to list your events?");
         System.out.println("\t1.Alphabetically\n\t2.Chronologically\n\t3.How I inputed it");
         System.out.println("Please enter your option:");
         input = myReader.readLine();
         inpu = Integer.parseInt(input);
         if (inpu > 3) {
           System.out.println("Please enter a valid option: ");
           input = myReader.readLine();
           inpu = Integer.parseInt(input);
         }
         if (inpu == 1) {
           System.out.println(calendar.listEvent('a'));
         } else if (inpu == 2) {
           System.out.println(calendar.listEvent('c'));
         } else if (inpu == 3) {
           System.out.println(calendar.listEvent('z'));
         }
       }
       // DELETING EVENTS
       else if (inpu == 4){
         System.out.println("Please wait patiently for this future. Patience is bliss!");
       }
       // LEAVING THE PROGRAM
       else if (inpu == 5){
         System.out.println("Thank you for being organized!");
         System.exit(1);
       }
       // ADDING EVENTS
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
     } catch (Exception e){
      // System.out.println(e);
       System.out.println("Please put in valid parameters");
       System.exit(1);
       }
      // System.out.print("Input your start time :");
      // myName = myReader.readLine();
      // System.out.println("Event start time is : "+ myName);
      // String endTime = myRead.readLine();

   }
 }
