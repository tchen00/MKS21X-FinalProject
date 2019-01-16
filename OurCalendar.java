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

    public static void main(String[] args) throws IOException {
    }
}
