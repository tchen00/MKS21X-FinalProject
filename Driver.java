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
        // CalendarViews test = new CalendarViews();
         System.out.println("Please select your view option: \n \t 1. Year \n \t 2. Month \n \t 3. Week \n **NOTE: Must be lowercase -- for now**");
         //try {
           //System.out.println(test.numberOfDays(28, 2, 2019));
         //  System.out.println(test.numberOfDays(1, 1, 2018));
       //    System.out.println(test.numberOfDays(1, 13, 2019));
         //  System.out.println(test.numberOfDays(1, 0, 2019));
       //    System.out.println(test.numberOfDays(30, 2, 2020));
         //  System.out.println(test.numberOfDays(29, 2, 2020));
       //    System.out.println(test.numberOfDays(29, 2, 2019));
       //    System.out.println(test.numberOfDays(31, 9, 2019));
       //    System.out.println(test.numberOfDays(32, 1, 2019));
       //  } catch (Exception e) {
         //  System.out.println(e);
         //}
     /*    System.out.println(test.numberOfDays(1, 1, 2019));
         System.out.println(test.numberOfDays(1, 2, 2019));
         System.out.println(test.numberOfDays(23, 10, 2019));
         System.out.println(test.numberOfDays(1, 1, 2020));
         System.out.println(test.numberOfDays(1, 7, 2020));
         System.out.println(test.numberOfDays(1, 1, 2021)); */
     /*    System.out.println(test.getFirstDayOfMonth(8, 2019));
         System.out.println(test.getFirstDayOfMonth(7, 2019));
         System.out.println(test.getFirstDayOfMonth(6, 2019));
         System.out.println(test.getFirstDayOfMonth(12, 2019));
         System.out.println(test.getFirstDayOfMonth(1, 2020));
         System.out.println(test.getFirstDayOfMonth(5, 2020));
         System.out.println(test.getFirstDayOfMonth(10, 2023));
         System.out.println(test.getFirstDayOfYear(2020));
         System.out.println(test.getFirstDayOfYear(2021));
         System.out.println(test.getFirstDayOfYear(2022));
         System.out.println(test.getFirstDayOfYear(2023));
         System.out.println(test.getFirstDayOfYear(2050)); */
       /*  System.out.println(test.getWeekday(1,1,2019));
         System.out.println(test.getWeekday(1,1,2020));
         System.out.println(test.getWeekday(1,1,2021));
         System.out.println(test.getWeekday(1,1,2022));
         System.out.println(test.getWeekday(1,1,2023));
         System.out.println(test.getWeekday(1,1,2050));*/
     /*    System.out.println(test.getStartOfWeek(1,1,2019));
         System.out.println(test.getStartOfWeek(2,1,2019));
         System.out.println(test.getStartOfWeek(3,1,2019));
         System.out.println(test.getStartOfWeek(4,1,2019));
         System.out.println(test.getStartOfWeek(5,1,2019)); */
         //System.out.println(test.printMonth(1,2019));
       //  System.out.println(test.printYear(2019));
     /*    System.out.println(test.printMonth(2,2019));
         System.out.println(test.printMonth(2,2020));
         System.out.println(test.printMonth(4,2019));*/
       /*  try {
           test.getData("life.csv");
           System.out.println(test.events.toString());
         } catch (Exception e) {
           System.out.println(e);
         } */
         String myView = myReader.readLine();
         if (myView.equals("year")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           int yearr = Integer.parseInt(yeary);
           CalendarViews test = new CalendarViews("year","life.csv");
           System.out.println(test.printYear(yearr));
         } else if (myView.equals("month")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           int yearr = Integer.parseInt(yeary);
           String monthy = myReader.readLine();
           int monthh = Integer.parseInt(yeary);
           CalendarViews test = new CalendarViews("month","life.csv");
           System.out.println(test.printMonth(yearr, monthh));
         } else if (myView.equals("week")){
           System.out.println("Please input the year you would like to see: ");
           String yeary = myReader.readLine();
           int yearr = Integer.parseInt(yeary);
           String monthy = myReader.readLine();
           int monthh = Integer.parseInt(yeary);
           String dayye = myReader.readLine();
           int dayy = Integer.parseInt(dayye);
           CalendarViews test = new CalendarViews("week","life.csv");
           System.out.println(test.printWeek(yearr, monthh, dayy));
         }
         // test.printWeek(7,1,2019);
    //     System.out.print("\033[H\033[2J");
    //     System.out.flush();
           // INSERT CODE FOR CALENDAR VIEWS
      //     System.out.println("");
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
