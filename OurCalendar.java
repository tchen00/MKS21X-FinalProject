import java.io.*;
import java.util.*;
import java.*;
import java.nio.file.*;

public class OurCalendar extends CalendarViews {
    //variables
    private int year;
    private String CSV;
    private String txt;
    private String output;
    private ArrayList<String> toDoList;

    public OurCalendar(String csvFile, String txtFile) throws FileNotFoundException, IOException {
      //calling from CalendarViews bc w/o it, will return error!
      super("",csvFile);
      CSV = csvFile;
      txt = txtFile;
      toDoList = new ArrayList<String>();
      File f = new File(txtFile);
      Scanner in = new Scanner(f);
      while (in.hasNext()) {
        toDoList.add(in.nextLine());
      }
    }

    public void clearAll() throws IOException {
      File f = new File(CSV);
      FileWriter fw = new FileWriter(f,false);
      fw.write("");
      fw.close();
    }

    public void deleteEvent(String fileName, String removeTerm) {
      // an attempt at deleting events
      String tempFile = "test.csv";
      File oldFile = new File(fileName);
      File newFile = new File(tempFile);
      String eName = ""; String eMonth = ""; String eDay = ""; String eYear = "";
      try {
        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        Scanner x = new Scanner(new File(fileName));
        x.useDelimiter("[,\n]");

        while(x.hasNext()){
          eName = x.next();
          eYear = x.next();
          eMonth = x.next();
          if (!eName.equals(removeTerm)){
            pw.println(eName + "," + eYear + "," + eMonth + "," + eDay);
          }
        }
        x.close();
        pw.flush();
        pw.close();
        oldFile.delete();
        File dump = new File(fileName);
        newFile.renameTo(dump);
      } catch (Exception e){
        e.printStackTrace();
      }
    }

    // print out the to do list
    public String toStringToDo() {
      String result = "";
      for (int i = 0; i < toDoList.size(); i++) {
        result += "\t"+(i+1)+". "+toDoList.get(i)+"\n";
      }
      return result;
    }

    public void addToDo(String s) throws IOException{
      toDoList.add(s);
      FileWriter w = new FileWriter(txt);
      PrintWriter out = new PrintWriter(w);
      for (String i : toDoList) {
        out.write(i+"\n");
      }
      out.flush();
      w.flush();
      out.close();
      w.close();
    }

    public void removeToDo(int s) throws IOException{
      toDoList.remove(s-1);
      FileWriter w = new FileWriter(txt);
      PrintWriter out = new PrintWriter(w);
      for (String i : toDoList) {
        out.write(i+"\n");
      }
      out.flush();
      w.flush();
      out.close();
      w.close();
    }

    // filter events by month
    public String filterM(char type, ArrayList<Event> e, int year, int month) throws FileNotFoundException,IOException {
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

    // filter events by month
    public String filterD(char type, ArrayList<Event> e, int year, int month, int day) throws FileNotFoundException, IOException {
      if (!validDate(year, month, day)) {
        throw new IllegalArgumentException("Please enter a valid date");
      }
      ArrayList<Event> result = new ArrayList<Event>();
      for (Event current : e) {
        String date = current.getDate();
        int m = Integer.parseInt(date.substring(0,date.indexOf("/")));
        String sub = date.substring(date.indexOf("/")+1);
        int d = Integer.parseInt(sub.substring(0,sub.indexOf("/")));
        int y = Integer.parseInt(sub.substring(sub.indexOf("/")+1));
        if (m == month && y == year && d == day) {
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

    // TESTING COLORS -- in terminal
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
      /*
         System.out.println(ANSI_WHITE_BACKGROUND+ ANSI_RED + "This text is red!" + ANSI_RESET);
         System.out.println(ANSI_YELLOW_BACKGROUND+ ANSI_GREEN + "Hi" + ANSI_RESET);
         System.out.println(ANSI_PURPLE_BACKGROUND+ ANSI_CYAN + "PERSON!! YOU ARE AMAZING!" + ANSI_RESET);
     */
         // set default color
         String color = ANSI_CYAN;
         BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader br = new BufferedReader(new FileReader("life.csv"));
         String csvFile = "life.csv";
         String line = "";
         String cvsSplitBy = ",";
         FileWriter fw = new FileWriter(csvFile, true);
         PrintWriter out = new PrintWriter(fw);
         boolean end = false;
         while (!end) {
            // basic menu
             System.out.println(ANSI_WHITE_BACKGROUND + "                                                                                                                                                                                         " + ANSI_RESET);
             System.out.println(color + "Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add an Event \n \t 2. Print Calendar View with Events \n \t 3. List All Upcoming Events \n \t 4. Delete Event \n \t 5. To Do List \n \t 6. Customize Color \n \t 7. Exit Program" );
             System.out.println("\nIf you are a new user who would like to start afresh and delete all old data, please input clear down below");
             System.out.print("\nPlease input your option: ");
             String input = myReader.readLine();
             //clearing of everything
             if (input.equals("clear")) {
               System.out.print("Be aware that this deletes all old data such as events stored in the calendar" + ANSI_RED + " FOREVER. " + ANSI_RESET + color);
               System.out.print("Would you still like to clear(y/n)? ");
               String answer = myReader.readLine();
               while (!answer.equals("y") && !answer.equals("n")) {
                 System.out.println("Would you still like to clear? (y/n)");
                 answer = myReader.readLine();
               }
               if (answer.equals("y")) {
                 OurCalendar clearing = new OurCalendar(csvFile, "todo.txt");
                 clearing.clearAll();
                 System.out.println("Cleared!");
               }
               System.out.println(ANSI_WHITE_BACKGROUND + "                                                                                                                                                                                         " + ANSI_RESET);
               System.out.println(color + "Welcome to your Calendar and Scheduling: " + "\n" + "Below is the menu: \n \t 1. Add an Event \n \t 2. Print Calendar View with Events \n \t 3. List All Upcoming Events \n \t 4. Delete Event \n \t 5. To Do List \n \t 6. Customize Color \n \t 7. Exit Program" );
               System.out.println("\nIf you are a new user who would like to start afresh and delete all old data, please input clear down below");
               System.out.print("\nPlease input your option: ");
               input = myReader.readLine();
               //System.exit(1);
             }
             int inpu = Integer.parseInt(input);
             // NOT AN OPTION
             while (inpu > 7){
               System.out.println("-----------------------------------------------------------------------------------------");
               System.out.print("Please enter a valid option: ");
               input = myReader.readLine();
               inpu = Integer.parseInt(input);
             }
             // VIEWING THE CALENDAR
             if (inpu == 2) {
              // CalendarViews test = new CalendarViews();
               System.out.println("-----------------------------------------------------------------------------------------");
               System.out.println("Please select your view option: \n \t 1. Year \n \t 2. Month \n \t 3. Week \n ");
              System.out.print("Please enter a valid option: ");
               String myView = myReader.readLine();
               int optionView = Integer.parseInt(myView);
               if (optionView == 1){
                 System.out.print("Please input the year you would like to see: ");
                 String yeary = myReader.readLine();
                 //System.out.println(yeary);
                 int yearr = Integer.parseInt(yeary);
                 CalendarViews test = new CalendarViews("year",csvFile);
                 System.out.println(test.printYear(yearr));
               } else if (optionView == 2){
                 System.out.print("Please input the year you would like to see: ");
                 String yeary = myReader.readLine();
                 int yearr = Integer.parseInt(yeary);
                 System.out.print("Please input the month you would like to see: ");
                 String monthy = myReader.readLine();
                 int monthh = Integer.parseInt(monthy);
                 CalendarViews test = new CalendarViews("month",csvFile);
                 System.out.println(test.printMonth(yearr, monthh));
               } else if (optionView == 3){
                 System.out.print("Please input the year you would like to see: ");
                 String yeary = myReader.readLine();
                 int yearr = Integer.parseInt(yeary);
                 System.out.print("Please input the month you would like to see: ");
                 String monthy = myReader.readLine();
                 int monthh = Integer.parseInt(monthy);
                 System.out.print("Please input a day in the week: ");
                 String dayye = myReader.readLine();
                 int dayy = Integer.parseInt(dayye);
                 CalendarViews test = new CalendarViews("week",csvFile);
                 test.printWeek(yearr, monthh, dayy);
               } else {
                 System.out.print("Please put in a valid option");
                 //System.exit(1);
               }
             }
             // LISTING THE EVENTS
             else if (inpu == 3) {
               System.out.println("-----------------------------------------------------------------------------------------");
               OurCalendar calendar = new OurCalendar(csvFile, "todo.txt");
               System.out.println("How would you like to list your events?");
               System.out.println("\t1.Alphabetically\n\t2.Chronologically\n\t3.How I inputed it");
               System.out.print("Please enter your option:");
               input = myReader.readLine();
               inpu = Integer.parseInt(input);
               if (inpu > 3) {
                 System.out.print("Please enter a valid option: ");
                 input = myReader.readLine();
                 inpu = Integer.parseInt(input);
               }
               char type = 'p';
               if (inpu == 1) type = 'a';
               else if (inpu == 2) type = 'c';
               else if (inpu == 3) type = 'z';
               CalendarViews c = new CalendarViews("list",csvFile);
               System.out.print("Would you like to filter your events(y/n)? ");
               String filterReply = myReader.readLine();
               while (!filterReply.equals("y") && !filterReply.equals("n")) {
                 System.out.print("Please enter a valid input (y/n): ");
                 filterReply = myReader.readLine();
               }
               if (filterReply.equals("y")) {
                 System.out.println("How would you like to filter them?\n");
                 System.out.println("\t1.Month\n\t2.Day");
                 String filterType = myReader.readLine();
                 while(!filterType.equals("1") && !filterType.equals("2")) {
                   System.out.print("Please pick one of the options:");
                   filterType = myReader.readLine();
                 }
                 if (filterType.equals("1")) {
                   System.out.print("Choose a year: ");
                   int inputYear = Integer.parseInt(myReader.readLine());
                   System.out.print("Choose a month: ");
                   int inputMonth = Integer.parseInt(myReader.readLine());
                   System.out.println(calendar.filterM(type, c.getEvents(), inputYear, inputMonth));
                 } else if (filterType.equals("2")) {
                   System.out.print("Choose a year: ");
                   int inputYear = Integer.parseInt(myReader.readLine());
                   System.out.print("Choose a month: ");
                   int inputMonth = Integer.parseInt(myReader.readLine());
                   System.out.print("Choose a day: ");
                   int inputDay = Integer.parseInt(myReader.readLine());
                   System.out.println(calendar.filterD(type, c.getEvents(),inputYear,inputMonth,inputDay));
                 }
               } else if (filterReply.equals("n")) {
                 System.out.println(calendar.listEvent(type, c.getEvents()));
               }
             }
             // DELETING EVENTS
             else if (inpu == 4){
               CalendarViews c = new CalendarViews("list",csvFile);
               OurCalendar listing = new OurCalendar("life.csv","todo.txt");
               System.out.println("Here are all your events: \n");
               System.out.println("-------------------------- Event + Date ---------------------  ");
               System.out.println(listing.listEventS('z',c.getEvents()));
               System.out.print("Select the event you wish to" + ANSI_RED + " DELETE: " + color);
               String selection = myReader.readLine();
               System.out.println(listing.findEvent(Integer.parseInt(selection)));
               int select = Integer.parseInt(selection);
               System.out.print("Be aware that this deletes the event forever. You will not be able to recover it back! Would you still like to clear (y/n)? ");
               String answer = myReader.readLine();
               while (!answer.equals("y") && !answer.equals("n")) {
                 System.out.print("Would you still like to clear? (y/n)");
                 answer = myReader.readLine();
               }
               if (answer.equals("y")){
                System.out.print("You are deleting: " + listing.findEvent(Integer.parseInt(selection)));
                String output = listing.findEvent(Integer.parseInt(selection));
                FileInputStream instream = null;
                FileOutputStream outstream = null;
          	    File infile =new File("life.csv");
          	    File outfile =new File("test.csv");
          	    instream = new FileInputStream(infile);
          	    outstream = new FileOutputStream(outfile);
          	    byte[] buffer = new byte[1024];
          	    int length;
                int count = 1;
          	    while ((length = instream.read(buffer)) > 0){
                    outstream.write(buffer, 0, length);
          	    }
          	    instream.close();
          	    outstream.close();
                BufferedReader test = new BufferedReader(new FileReader("test.csv"));
                String strCurrentLine;
                int index = 1;
                OurCalendar clearer = new OurCalendar("life.csv","todo.txt");
                clearer.clearAll();
                while ((strCurrentLine = test.readLine()) != null) {
                  if (index != select){
                   String[] neww = strCurrentLine.split(",");
                   FileWriter fwr = new FileWriter("life.csv", true);
                   for (int i = 0; i < neww.length;i++){
                     fwr.write(neww[i]);
                     fwr.write(",");
                   }
                   fwr.write("\n");
                   fwr.close();
                   //System.out.println("H");
                 } index++;
                }
                test.close();
          	    //System.out.println("File copied successfully!!");

                //String [] arrofStr = (listing.findEvent(Integer.parseInt(selection))).split("|");
                //System.out.println(Arrays.toString(arrofStr));
                //System.out.println(arrofStr[1]);

              //  listing.deleteEvent("life.csv", arrofStr[0]);

               }
               /*
               FileWriter reader2 = new FileWriter(csvFile, true);
               List<String[]> allElements = reader2.readAll();
               allElements.remove(2);
               FileWriter sw = new FileWriter(life.csv);
               CSVWriter writer = new CSVWriter(sw);
               writer.writeAll(allElements);
               writer.close();
               */
              // System.out.println("Please wait patiently for this future. Patience is bliss!");
             }
             // LEAVING THE PROGRAM
             else if (inpu == 7){
               System.out.println("-----------------------------------------------------------------------------------------");
               System.out.println("Thank you for being organized!");
               end = true;
               System.out.println(ANSI_RESET);
               System.exit(1);
             }
             // ADDING EVENTS
             else if (inpu == 1) {
               Event a = new Event();
               Date b = new Date();
               System.out.print("Input event name: ");
               String myName = myReader.readLine();
               //System.out.println("Event name is : "+ myName);
               System.out.print("Input your event year: ");
               String myYear = myReader.readLine();
               System.out.print("Input your event month: ");
               String myMonth = myReader.readLine();
               System.out.print("Input your event day: ");
               String myDay = myReader.readLine();
               boolean valiDate = false;
               while (!valiDate){
                 if (b.validDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay))){
                   a.setDate(Integer.parseInt(myYear),Integer.parseInt(myMonth),Integer.parseInt(myDay));
                   valiDate = true;
                 } else {
                   System.out.println("Please make sure you are inputting a valid date: \n NOTE: This calendar will not let you dwell in the past so don't try to set an event from the past");
                   System.out.print("Input your event year: ");
                   myYear = myReader.readLine();
                   System.out.print("Input your event month: ");
                   myMonth = myReader.readLine();
                   System.out.print("Input your event day: ");
                   myDay = myReader.readLine();
                }
              }
              System.out.print("Input your event start time: ");
              String myStartTime = myReader.readLine();
              System.out.print("Input your event end time: ");
              String myEndTime = myReader.readLine();
              a.setTime(myStartTime, myEndTime);
              System.out.print("Input any additional notes you may have: ");
              String myNote = myReader.readLine();
              a.setName(myName);
              out.append(myName);
              out.append(",");
              out.append(myYear);
              out.append(",");
              out.append(myMonth);
              out.append(",");
              out.append(myDay);
              out.append(",");
              out.append(myStartTime);
              out.append(",");
              out.append(myEndTime);
              out.append(",");
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
             else if (inpu == 5) {
               System.out.println("You've switched to your To Do List mode:");
               System.out.println("\t 1. View \n \t 2. Add \n \t 3. Remove \n");
               System.out.println("Please enter your option: ");
               int toDoChoice = Integer.parseInt(myReader.readLine());
               while (toDoChoice > 3 || toDoChoice < 1) {
                 System.out.println("Please enter a valid option:\n");
                 toDoChoice = Integer.parseInt(myReader.readLine());
               }
               OurCalendar cToDo = new OurCalendar("life.csv","todo.txt");
               if (toDoChoice == 1) {
                 System.out.println("\tTO DO LIST");
                 System.out.println(cToDo.toStringToDo());
               } else if (toDoChoice == 2) {
                 System.out.println("What would you like to add?");
                 String task = myReader.readLine();
                 cToDo.addToDo(task);
               } else if (toDoChoice == 3) {
                 System.out.println(cToDo.toStringToDo());
                 System.out.println("Please enter the number of the task you would like to remove:");
                 int removed = Integer.parseInt(myReader.readLine());
                 cToDo.removeToDo(removed);
               }
             } else if (inpu == 6) {
               System.out.println(ANSI_RESET + "Pick your text color: \n \t 1. " + ANSI_CYAN + "CYAN" + ANSI_RESET + " \n \t 2. " + ANSI_PURPLE + "PURPLE" + ANSI_RESET + " \n \t 3. " + ANSI_GREEN + "GREEN" + ANSI_RESET);
               int choice = Integer.parseInt(myReader.readLine());
               if (choice == 1){
                 color = ANSI_CYAN;
               } else if (choice == 2) {
                 color = ANSI_PURPLE;
               } else if (choice == 3) {
                 color = ANSI_GREEN;
               }
             }
           }
         } catch (Exception e){
            // System.out.println(e);
             System.out.println("Please put in valid parameters");
             System.exit(1);
             }
           }
        // System.out.print("Input your start time :");
        // myName = myReader.readLine();
        // System.out.println("Event start time is : "+ myName);
        // String endTime = myRead.readLine();

}
