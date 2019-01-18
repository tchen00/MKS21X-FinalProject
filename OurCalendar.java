import java.io.*;
import java.util.*;

public class OurCalendar extends CalendarViews {
    private int year;
    private String file;
    private String output;

    public OurCalendar(String fileName) throws FileNotFoundException, IOException {
      super("",fileName);
      file = fileName;
    }

    // Note TO BE COMPLETED LATER
    public void clearAll() throws IOException {
      File f = new File(file);
      FileWriter fw = new FileWriter(f,false);
      fw.write("");
      fw.close();
    }

    public void createEvent() {

    }

    public void deleteEvent() {

    }

    public String filter(char type, ArrayList<Event> e, int month, int year) throws FileNotFoundException,IOException {
      if (!validDate(year, month, 1)) {
        throw new IllegalArgumentException("Please enter a valid date");
      }
      ArrayList<Event> result = new ArrayList<Event>();
      for (Event current : e) {
        String date = current.getDate();
        int m = Integer.parseInt(date.substring(0,date.indexOf("/")));
        String sub = date.substring(date.indexOf("/")+1);
        int y = Integer.parseInt(sub.substring(sub.indexOf("/")+1));
        if (m == month && y == year) {
          result.add(current);
        }
      }
      return listEvent(type, result);
    }

    // "a" = alphabetical
    // "c" = chronological
    // "n" = order listed in csv file
    public String listEvent(char type, ArrayList<Event> e) throws FileNotFoundException, IOException{
      // e should usually be <someCalendarViewsthing>.getEvents()
      // type determines alphabetical vs chronological
      // for now, we're just printing out the events as seen in the csv file
      String result = "\nYOUR EVENTS: \n\n";
      if (type == 'a') {
        Date.insertionSortA(e);
      }
      if (type == 'c') {
        Date.insertionSortC(e);
      }
      for (Event current : e) {
        result += current.toString() + "\n\n";
      }
      return result;
    }


//This lists a short and more condensed version of listEvent
    public String listEventS(char type, ArrayList<Event> e) throws FileNotFoundException, IOException{
      String result = "\nYOUR EVENTS: \n\n";
      if (type == 'a') {
        Date.insertionSortA(e);
      }
      if (type == 'c') {
        Date.insertionSortC(e);
      }
      int i = 1;
      for (Event current : e) {
        output  += "\t" + "[ " + i +" ] "+ current.toShortString() + "\n\n";
        i++;
      }
      return output;
    }

    public String findEvent(int n) throws FileNotFoundException, IOException{
      String [] outputSplit = output.split("\t");
      String [] outty = Arrays.copyOfRange(outputSplit, 1, outputSplit.length);
      String test = outty[n-1];
      String testy = test.substring(6,test.length());
      return testy;
    }

    // TESTING COLORS -- UNCOMMENT TO TEST
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
        public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
        public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
        public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws IOException {
      try {
      // UNCOMMENT THIS TO TEST COLORS
         System.out.println(ANSI_WHITE_BACKGROUND+ ANSI_RED + "This text is red!" + ANSI_RESET);
         System.out.println(ANSI_YELLOW_BACKGROUND+ ANSI_GREEN + "Hi" + ANSI_RESET);
         System.out.println(ANSI_PURPLE_BACKGROUND+ ANSI_CYAN + "PERSON!! YOU ARE AMAZING!" + ANSI_RESET);

         BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader br = new BufferedReader(new FileReader("life.csv"));
         String csvFile = "life.csv";
         String line = "";
         String cvsSplitBy = ",";
         FileWriter fw = new FileWriter(csvFile, true);
         PrintWriter out = new PrintWriter(fw);
         System.out.println("Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add an Event \n \t 2. Print Calendar View with Events \n \t 3. List All Upcoming Events \n \t 4. Delete Event (IN THE FUTURE) \n \t 5. Exit Program" );
         System.out.println("\nIf you are a new user who would like to start afresh and delete all old data, please input clear down below");
         System.out.println("\nPlease input your option: ");
         String input = myReader.readLine();
         if (input.equals("clear")) {
           System.out.println("Be aware that this deletes all old data such as events stored in the calendar forever.");
           System.out.println("Would you still like to clear? (y/n)");
           String answer = myReader.readLine();
           while (!answer.equals("y") && !answer.equals("n")) {
             System.out.println("Would you still like to clear? (y/n)");
             answer = myReader.readLine();
           }
           if (answer.equals("y")) {
             OurCalendar clearing = new OurCalendar(csvFile);
             clearing.clearAll();
             System.out.println("Cleared!");
           }
           System.exit(1);
         }
         int inpu = Integer.parseInt(input);
         // NOT AN OPTION
         while (inpu > 5){
           System.out.println("-----------------------------------------------------------------------------------------");
           System.out.println("Please enter a valid option: ");
           input = myReader.readLine();
           inpu = Integer.parseInt(input);
         }
         // VIEWING THE CALENDAR
         if (inpu == 2) {
          // CalendarViews test = new CalendarViews();
           System.out.println("-----------------------------------------------------------------------------------------");
           System.out.println("Please select your view option: \n \t 1. Year \n \t 2. Month \n \t 3. Week \n ");
           String myView = myReader.readLine();
           int optionView = Integer.parseInt(myView);
           if (optionView == 1){
             System.out.println("Please input the year you would like to see: ");
             String yeary = myReader.readLine();
             System.out.println(yeary);
             int yearr = Integer.parseInt(yeary);
             CalendarViews test = new CalendarViews("year",csvFile);
             System.out.println(test.printYear(yearr));
           } else if (optionView == 2){
             System.out.println("Please input the year you would like to see: ");
             String yeary = myReader.readLine();
             int yearr = Integer.parseInt(yeary);
             System.out.println("Please input the month you would like to see: ");
             String monthy = myReader.readLine();
             int monthh = Integer.parseInt(monthy);
             CalendarViews test = new CalendarViews("month",csvFile);
             System.out.println(test.printMonth(yearr, monthh));
           } else if (optionView == 3){
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
             test.printWeek(yearr, monthh, dayy);
           } else {
             System.out.println("Please put in a valid option");
             System.exit(1);
           }
         }
         // LISTING THE EVENTS
         else if (inpu == 3) {
           System.out.println("-----------------------------------------------------------------------------------------");
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
           char type = 'p';
           if (inpu == 1) type = 'a';
           else if (inpu == 2) type = 'c';
           else if (inpu == 3) type = 'z';
           CalendarViews c = new CalendarViews("list",csvFile);
           System.out.println("Would you like to filter your events by month? (y/n)");
           String filterReply = myReader.readLine();
           while (!filterReply.equals("y") && !filterReply.equals("n")) {
             System.out.println("Please enter a valid input (y/n)");
             filterReply = myReader.readLine();
           }
           if (filterReply.equals("y")) {
             System.out.println("Choose a year: ");
             int inputYear = Integer.parseInt(myReader.readLine());
             System.out.println("Choose a month: ");
             int inputMonth = Integer.parseInt(myReader.readLine());
             System.out.println(calendar.filter(type, c.getEvents(), inputMonth, inputYear));
           } else if (filterReply.equals("n")) {
             System.out.println(calendar.listEvent(type, c.getEvents()));
           }
         }
         // DELETING EVENTS
         else if (inpu == 4){
           CalendarViews c = new CalendarViews("list",csvFile);
           OurCalendar listing = new OurCalendar("life.csv");
           System.out.println("Here are all your events: ");
           System.out.println("-------------------------- Event + Date -----------------  ");
           System.out.println(listing.listEventS('a',c.getEvents()));
           System.out.print("Select the event you wish to" + ANSI_RED + " DELETE: " + ANSI_RESET);
           String selection = myReader.readLine();
           System.out.println(listing.findEvent(Integer.parseInt(selection)));
          // System.out.println("Please wait patiently for this future. Patience is bliss!");
         }
         // LEAVING THE PROGRAM
         else if (inpu == 5){
           System.out.println("-----------------------------------------------------------------------------------------");
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
         //System.out.println(e);
         System.out.println("Please put in valid parameters");
         System.exit(1);
         }
        // System.out.print("Input your start time :");
        // myName = myReader.readLine();
        // System.out.println("Event start time is : "+ myName);
        // String endTime = myRead.readLine();

     }
}
