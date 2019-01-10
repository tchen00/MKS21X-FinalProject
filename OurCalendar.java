import java.io.*;

public class OurCalendar {
    private int year;
    private File data;

    public OurCalendar(String fileName) {

    }

    public void clearAll() {

    }

    public void createEvent() {

    }

    public void deleteEvent() {

    }

    public void listEvent(String fileName, String type) throws FileNotFoundException, IOException{
      // type determines alphabetical vs chronological
      // for now, we're just printing out the events as seen in the csv file
      System.out.println("\nYOUR EVENTS: \n");
      File f = new File(fileName);
      BufferedReader br = new BufferedReader(new FileReader(f));
      String line = "";
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
      System.out.println("\n");
    }

    public static void main(String[] args) {
      try {
        OurCalendar c = new OurCalendar("life.csv");
        c.listEvent("life.csv","yes");
      } catch (Exception e) {
        System.out.println(e);
      }

    }
}
