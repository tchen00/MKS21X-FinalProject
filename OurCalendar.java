import java.io.*;
import java.util.*;

public class OurCalendar extends Date {
    private int year;
    private String file;

    public OurCalendar(String fileName) {
      file = fileName;
    }

    // Note TO BE COMPLETED LATER
    public void clearAll() {

    }

    public void createEvent() {

    }

    public void deleteEvent() {

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
}
