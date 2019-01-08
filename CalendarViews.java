import java.io.*;
import java.util.*;

public class CalendarViews extends Date {
  private String view;
  private int month;
  private int startDate;
  private int endDate;
  private ArrayList<Event> events;

  public CalendarViews(String v) {
    events = new ArrayList<Event>();
    view = v;
  }

  // returns the view selected
  public String getView() {
    return view;
  }

  // changes the view
  public void setView(String s) {
    view = s;
  }

  public String viewDate() { // might not be useful later
    return "";
  }

  // returns number of days since Jan 1 2019
  public int numberOfDays(int day, int month, int year) {
    if (year < 2019 || month > 12 || month < 1) {
      throw new IllegalArgumentException("Your date inputed is either not real or before Jan 1 2019");
    }
    if (day > 31 || (month == 2 && day > 29) ||
        (((month < 7 && month % 2 == 0) || (month > 8 && month % 2 == 1)) && day > 30)) {
      throw new IllegalArgumentException("Your date inputed is either not real or before Jan 1 2019");
    }
    int result = 0;
    boolean leapYear = false;
    if (year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)) leapYear = true;
    // adding days from years
    if (!leapYear && month == 2 && day > 28) {
      throw new IllegalArgumentException("Your date inputed is either not real or before Jan 1 2019");
    }
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
      if ((i > 7 && i % 2 == 0) || (i < 8 && i % 2 == 1)) {
        tempDays = 31;
      } else if (i == 2) {
        if (leapYear) tempDays = 29;
        else tempDays = 28;
      } else {
        tempDays = 30;
      }
      result += tempDays;
    }
    result += day - 1; // shouldn't count Jan 1st
    return result;
  }

  public String printYear(int y) {
    return "";
  }

  public String printMonth(int m, int y) {
    String result = "";
    result += convertToMonth(m)+y+"\n\n";
    result += "Sun\tMon\tTues\tWed\tThurs\tFri\tSat\n";
    int firstDay = getFirstDayOfMonth(m,y);
    for (int i = 0; i < firstDay; i++) {
      result += "\t";
    }
    for (int i = 1; i <= daysInMonth(m,y); i++) {
      result += i+"\t";
      System.out.println(convertToNum(getWeekday(i,m,y)));
      if (convertToNum(getWeekday(i,m,y)) == 6) {
        result += "\n";
      }
    }
    return result;
  }

  public String printWeek() {
    return "";
  }

  public String printDay() {
    return "";
  }

  // collects data and fills in events array
  /// Event(String name, int year, int month, int day, int startTime, int endTime, note)
  public void getData(File f) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader(f));
    String line = "";
    while ((line = br.readLine()) != null) {
      String[] rows = line.split(",");
      // [name,year,month,day,startTime,endTime,note]
      Event e = new Event(rows[0],Integer.parseInt(rows[1]),
                          Integer.parseInt(rows[2]),Integer.parseInt(rows[3]),
                          Integer.parseInt(rows[4]),Integer.parseInt(rows[5]),
                          rows[6]);
      events.add(e);
    }
  }

  // returns weekday the month starts on (Sun=0, Mon=1 etc)
  public int getFirstDayOfMonth(int month, int year) {
    // Jan 1 2019 = 2 (Tues)
    int days = numberOfDays(1, month, year);
    return ((days % 7) + 2) % 7;
  }

  // return sweekday the year starts on
  public int getFirstDayOfYear(int year) {
    int days = numberOfDays(1,1,year);
    return ((days % 7) + 2) % 7;
  }

  // if the user asks, return weekday that date is on
  public String getWeekday(int day, int month, int year) {
    int d = (getFirstDayOfMonth(month, year) + day - 1) % 7;
    return convertToDay(d);
  }

  // return date that week #_ starts on --> good for printWeek
  // returned String in form month/day/year
  public String getStartOfWeek(int week, int month, int year) {
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

  public static void main (String[] args) {
    CalendarViews test = new CalendarViews("yes");
  /*  try {
      System.out.println(test.numberOfDays(28, 2, 2019));
    //  System.out.println(test.numberOfDays(1, 1, 2018));
  //    System.out.println(test.numberOfDays(1, 13, 2019));
  //    System.out.println(test.numberOfDays(1, 0, 2019));
    //  System.out.println(test.numberOfDays(30, 2, 2020));
      System.out.println(test.numberOfDays(29, 2, 2020));
  //    System.out.println(test.numberOfDays(29, 2, 2019));
    //  System.out.println(test.numberOfDays(31, 9, 2019));
  //    System.out.println(test.numberOfDays(32, 1, 2019));
    } catch (Exception e) {
      System.out.println(e);
    }
    System.out.println(test.numberOfDays(1, 1, 2019));
    System.out.println(test.numberOfDays(1, 2, 2019));
    System.out.println(test.numberOfDays(23, 10, 2019));
    System.out.println(test.numberOfDays(1, 1, 2020));
    System.out.println(test.numberOfDays(1, 7, 2020));
    System.out.println(test.numberOfDays(1, 1, 2021));
    System.out.println(test.getFirstDayOfMonth(8, 2019));
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
    System.out.println(test.getStartOfWeek(4,1,2019)); */
    System.out.println(test.printMonth(1,2019));
  }

}
