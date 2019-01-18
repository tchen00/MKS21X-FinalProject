import java.io.*;
import java.util.*;

public class CalendarViews extends Date {
  private String view; // yearly, monthly, weekly, daily
  private ArrayList<Event> events; // all events in csv file

  public CalendarViews(String v, String file) throws FileNotFoundException, IOException{
    events = new ArrayList<Event>();
    view = v;
    getData(file);
  }

  // returns the view selected
  public String getView() {
    return view;
  }

  // used in OurCalendar
  public ArrayList<Event> getEvents() {
    return events;
  }

  // returns number of days since Jan 1 2019
  private int numberOfDays(int year, int month, int day) {
    // exceptions handled
    if (!validDate(year, month, day)) {
      throw new IllegalArgumentException("Your date inputed is either not real or befoe Jan 1 2019");
    }
    int result = 0;
    // adding days from years
    for (int i = year - 1; i >= 2019; i--) {
      if (i % 4 == 0 && !(i % 100 == 0 && i % 400 != 0)) {
        result += 366;
      } else {
        result += 365;
      }
    }
    // adding days from months
    for (int i = month - 1; i > 0; i--) {
      int tempDays = 0; // to keep track of how many days are in that month
      tempDays = daysInMonth(year,i);
      result += tempDays;
    }
    result += day - 1; // shouldn't count Jan 1st
    return result;
  }

  // prints out all 12 months in one long column
  public String printYear(int year) {
    // exceptions handled
    if (!validDate(year,1,1)) {
      throw new IllegalArgumentException("Your year inputed is before 2019. We do not dwell in the past, please enter another.");
    }
    String result = "";
    for (int i = 1; i < 13; i++) {
      result += printMonth(year,i);
    }
    return result;
  }

  // prints out month with dot system to mark events
  public String printMonth(int year, int month) {
    // exceptions handled
    if (!validDate(year,month,1)) {
      throw new IllegalArgumentException("Your year and month inputed are invalid.");
    }
    String result = "";
    result += "\n"+convertToMonth(month).toUpperCase()+" "+year+"\n\n";
    result += "Sun\tMon\tTues\tWed\tThurs\tFri\tSat\n\n";
    int firstDay = getFirstDayOfMonth(year,month);
    for (int i = 0; i < firstDay; i++) {
      result += "\t";
    }
    for (int i = 1; i <= daysInMonth(year,month); i++) {
      result += i+" ";
      String currentDate = month+"/"+i+"/"+year;
      int count = 0; // keeping track of how many events on a certain day
      for (Event e : events) {
        if (currentDate.equals(e.getDate())) {
          count += 1;
        }
      }
      // make sure the tabbing isn't messed up
      // the month will not display 1000000 dots if someone is that busy that day
      if (count > 4) {
        result += "****+"; // plus sign to show more, if too many
      } else {
        for (int x = 0; x < count; x++) {
          result += "*";
        }
      }
      result += "\t";
      if (convertToNum(getWeekday(year,month,i)) == 6) {
        result += "\n\n";
      }
    }
    return result+"\n";
  }

  // prints out week that the day given is in
  // note: this is void because the system directly prints it
  public void printWeek(int year, int month, int day) {
    // exceptions handled
    if (!validDate(year,month,day)) {
      throw new IllegalArgumentException("Your year and month inputed are invalid.");
    }
    int dayOfWeek = convertToNum(getWeekday(year, month, day));
    int startDate = day - dayOfWeek; // gets the Sunday of that week
    // figuring out how many rows of table to have
    // the max events a day of that week has
    int maxEvents = 0;
    for (int i = startDate; i < startDate + 7; i++) {
      int numberOfEvents = 0;
      for (Event e : events) {
        String currentDate = month + "/" + i + "/" + year;
        if (e.getDate().equals(currentDate)) {
          numberOfEvents++;
        }
      }
      if (numberOfEvents > maxEvents) {
        maxEvents = numberOfEvents;
      }
    }
    String[][] table = new String[maxEvents+1][7];
    System.out.println(convertToMonth(month).toUpperCase() +" "+ year);
    for (int i = 0; i < 7; i++) {
      table[0][i] = (startDate+i) + " " + getWeekday(year, month, startDate+i);
      ArrayList<String> tempList = new ArrayList<String>();
      for (Event e : events) {
        String currentDate = month + "/" + (i+startDate) + "/" + year;
      //  System.out.println(currentDate);
        if (e.getDate().equals(currentDate)) {
          String name = e.getName();
          if (e.getName().length() > 18) {
            name = e.getName().substring(0,18);
          }
          tempList.add(name);
        }
      }
      for (int x = 1; x < table.length; x++) {
        if (x-1 < tempList.size()) {
          table[x][i] = tempList.get(x-1);
        } else {
          table[x][i] = "-"; // no events left on this day
        }
      }
    }
    // prints out the table
    for (String[] row : table) {
      System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", row);
    }
  }

  // collects data and fills in events array --> called in constructor
  /// Event(String name, int year, int month, int day, int startTime, int endTime, note)
  public void getData(String fileName) throws FileNotFoundException, IOException {
    File f = new File(fileName);
    BufferedReader br = new BufferedReader(new FileReader(f));
    String line = "";
    while ((line = br.readLine()) != null) {
      String[] rows = line.split(",");
      // [name,year,month,day,startTime,endTime,note]
      Event e = new Event(rows[0],Integer.parseInt(rows[1]),
                          Integer.parseInt(rows[2]),Integer.parseInt(rows[3]),
                          rows[4],rows[5],rows[6]);
      events.add(e);
    }
  }

  // returns weekday the month starts on (Sun=0, Mon=1 etc)
  public int getFirstDayOfMonth(int year, int month) {
    // exceptions handled
    if (!validDate(year,month,1)) {
      throw new IllegalArgumentException("Your month and year inputed are not valid.");
    }
    // Jan 1 2019 = 2 (Tues)
    int days = numberOfDays(year, month, 1);
    return ((days % 7) + 2) % 7;
  }

  // if the user asks, return weekday that date is on
  public String getWeekday(int year, int month, int day) {
    if (!validDate(year,month,day)) {
      throw new IllegalArgumentException("Your date inputed is not valid.");
    }
    int d = (getFirstDayOfMonth(year, month) + day - 1) % 7;
    return convertToDay(d);
  }

  // returns number of Sundays in a month
  // exception checking, but uses getWeekday so it cannot be in Date
  private int numberOfWeeks(int year, int month) {
    int sundays = 0;
    for (int i = 0; i < daysInMonth(year, month); i++) {
      if (getWeekday(year,month,i).equals("Sunday")) {
        sundays++;
      }
    }
    return sundays;
  }

}
