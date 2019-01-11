import java.io.*;
import java.util.*;

public class OurCalendar extends Date {
    private int year;
    private File data;

    public OurCalendar(String fileName) {
      data = new File(fileName);
    }

    public void clearAll() {

    }

    public void createEvent() {

    }

    public void deleteEvent() {

    }

    // "a" = alphabetical
    // "c" = chronological
    // "n" = order listed in csv file
    public String listEvent(String fileName, char type) throws FileNotFoundException, IOException{
      // type determines alphabetical vs chronological
      // for now, we're just printing out the events as seen in the csv file
      String result = "\nYOUR EVENTS: \n\n";
      CalendarViews c = new CalendarViews("list",fileName);
      ArrayList<Event> sorted = c.getEvents();
      if (type == 'c') {
        sorted = Date.insertionSort(c.getEvents());
      }
      for (Event e : sorted) {
        result += e.toString() + "\n\n";
      }
      return result;
/*      BufferedReader br = new BufferedReader(new FileReader(data));
      String line = "";
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
      System.out.println("\n"); */
    }

    public static void main(String[] args) {
      try {
        OurCalendar c = new OurCalendar("life.csv");
        System.out.println(c.listEvent("life.csv","yes"));
      } catch (Exception e) {
        System.out.println(e);
      }
    /*  try {
        PrintWriter pw = new PrintWriter(new File("yayyy.csv"));
      } catch (Exception e) {
        System.out.println(e);
      } */

    }
}
