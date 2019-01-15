import java.io.*;
import java.util.*;

public class OurCalendar extends Date {
    private int year;
    private String file;

    public OurCalendar(String fileName) {
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

    private ArrayList<Event> filter(ArrayList<Event>, int week, int mnoth, int year) {
      
    }

    // "a" = alphabetical
    // "c" = chronological
    // "n" = order listed in csv file
    public String listEvent(char type) throws FileNotFoundException, IOException{
      // type determines alphabetical vs chronological
      // for now, we're just printing out the events as seen in the csv file
      String result = "\nYOUR EVENTS: \n\n";
      CalendarViews c = new CalendarViews("list",file);
      ArrayList<Event> sorted = new ArrayList<Event>();
      sorted = c.getEvents();
      if (type == 'a') {
        Date.insertionSortA(sorted);
      }
      if (type == 'c') {
        Date.insertionSortC(sorted);
      }
      for (Event e : sorted) {
        result += e.toString() + "\n\n";
      }
      return result;
    }

    public static void main(String[] args) throws IOException {
      OurCalendar c = new OurCalendar("yayyy.csv");
      c.clearAll();
    }
}
