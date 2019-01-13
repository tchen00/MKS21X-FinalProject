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

  /// Useful?
  // changes the view
  public void setView(String s) {
    view = s;
  }

  // used in OurCalendar
  public ArrayList<Event> getEvents() {
    return events;
  }

  // returns number of days since Jan 1 2019
  public int numberOfDays(int day, int month, int year) {
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
      tempDays = daysInMonth(i,year);
      result += tempDays;
    }
    result += day - 1; // shouldn't count Jan 1st
    return result;
  }

  // prints out all 12 months in one long column
  public String printYear(int y) {
    if (!validDate(y,1,1)) {
      throw new IllegalArgumentException("Your year inputed is before 2019. We do not dwell in the past, please enter another.");
    }
    String result = "";
    for (int i = 1; i < 13; i++) {
      result += printMonth(i,y);
    }
    return result;
  }

  // prints out month with dot system to mark events
  public String printMonth(int m, int y) {
    if (!validDate(y,m,1)) {
      throw new IllegalArgumentException("Your year and month inputed are invalid.");
    }
    String result = "";
    result += "\n"+convertToMonth(m).toUpperCase()+" "+y+"\n\n";
    result += "Sun\tMon\tTues\tWed\tThurs\tFri\tSat\n\n";
    int firstDay = getFirstDayOfMonth(m,y);
    for (int i = 0; i < firstDay; i++) {
      result += "\t";
    }
    for (int i = 1; i <= daysInMonth(m,y); i++) {
      result += i+" ";
      String currentDate = m+"/"+i+"/"+y;
      int count = 0;
      for (Event e : events) {
        if (currentDate.equals(e.getDate())) {
          count += 1;
          // later, have to figure out tabbing and stuff if not too many stars
        }
      }
      if (count > 4) {
        result += "****+"; // dot system, if too many
      } else {
        for (int x = 0; x < count; x++) {
          result += "*";
        }
      }
      //System.out.println(convertToNum(getWeekday(i,m,y)));
      result += "\t";
      if (convertToNum(getWeekday(i,m,y)) == 6) {
        result += "\n\n";
      }
    }
    return result+"\n";
  }

  // prints out week that the day given is in
  public void printWeek(int day, int month, int year) {
    int dayOfWeek = convertToNum(getWeekday(day, month, year));
    int startDate = day - dayOfWeek; // gets the Sunday of that week
    // figuring out how many rows of table to have
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
      table[0][i] = (startDate+i) + " " + getWeekday(startDate+i, month, year);
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
    for (String[] row : table) {
      System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", row);
    }
  }

  public String printDay() {
    return "";
  }

  // collects data and fills in events array
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
  public int getFirstDayOfMonth(int month, int year) {
    if (!validDate(year,month,1)) {
      throw new IllegalArgumentException("Your month and year inputed are not valid.");
    }
    // Jan 1 2019 = 2 (Tues)
    int days = numberOfDays(1, month, year);
    return ((days % 7) + 2) % 7;
  }

  // returns weekday the year starts on
  public int getFirstDayOfYear(int year) {
    if (!validDate(year,1,1)) {
      throw new IllegalArgumentException("Your year inputed is before 2019. We do not dwell in the past. Please input a valid year");
    }
    int days = numberOfDays(1,1,year);
    return ((days % 7) + 2) % 7;
  }

  // if the user asks, return weekday that date is on
  public String getWeekday(int day, int month, int year) {
    if (!validDate(year,month,day)) {
      throw new IllegalArgumentException("Your date inputed is not valid.");
    }
    int d = (getFirstDayOfMonth(month, year) + day - 1) % 7;
    return convertToDay(d);
  }

  // returns number of Sundays in a month
  // helper method for getStartOfWeek
  private int numberOfWeeks(int month, int year) {
    int sundays = 0;
    for (int i = 0; i < daysInMonth(month, year); i++) {
      if (getWeekday(i,month,year).equals("Sunday")) {
        sundays++;
      }
    }
    return sundays;
  }

  // return date that week #_ starts on --> good for printWeek
  // returned String in form month/day/year
  public String getStartOfWeek(int week, int month, int year) {
    if (!validDate(year,month,1)) {
      throw new IllegalArgumentException("Your month and year inputed are not valid.");
    }
    if (week < 1 || week > numberOfWeeks(month,year)) {
      throw new IllegalArgumentException("Your week inputed is not valid.");
    }
    int numSundays = 0;
    int d = 0;
    while (numSundays != week) {
      d++;
      if (getWeekday(d, month, year).equals("Sunday")) {
        numSundays++;
      }
    }
    return month+"/"+d+"/"+year;
  }

  public static void main (String[] args) throws FileNotFoundException, IOException{
    CalendarViews test = new CalendarViews("month","life.csv");
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
    test.printWeek(7,1,2019);
  }

}
