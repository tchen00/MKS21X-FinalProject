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

    public ArrayList<Event> filter(ArrayList<Event> e, int month, int year) {
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
      return result;
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
