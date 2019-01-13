import java.io.*;
import java.util.*;

public class CalendarViews extends Date {
  private String view;
  private int month;
  private int startDate;
  private int endDate; // might not be useful --> calculated by user
  private ArrayList<Event> events;

  public CalendarViews(String v, String file) throws FileNotFoundException, IOException{
    events = new ArrayList<Event>();
    view = v;
    getData(file);
  }

  public CalendarViews(){

  }
  // returns the view selected
  public String getView() {
    return view;
  }

  // changes the view
  public void setView(String s) {
    view = s;
  }

  public ArrayList<Event> getEvents() {
    return events;
  }

  // returns number of days since Jan 1 2019
  public int numberOfDays(int day, int month, int year) {
    if (!validDate(year, month, day)) {
      throw new IllegalArgumentException("Your date inputed is either not real or befoe Jan 1 2019");
    }
    int result = 0;
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
        result += "****+";
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

  public String printWeek() {
    return "";
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
    try {
      System.out.println(test.numberOfDays(28, 2, 2019));
    //  System.out.println(test.numberOfDays(1, 1, 2018));
  //    System.out.println(test.numberOfDays(1, 13, 2019));
    //  System.out.println(test.numberOfDays(1, 0, 2019));
  //    System.out.println(test.numberOfDays(30, 2, 2020));
      System.out.println(test.numberOfDays(29, 2, 2020));
  //    System.out.println(test.numberOfDays(29, 2, 2019));
  //    System.out.println(test.numberOfDays(31, 9, 2019));
  //    System.out.println(test.numberOfDays(32, 1, 2019));
    } catch (Exception e) {
      System.out.println(e);
    }
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
  }

}
