import java.io.*;

public class CalendarViews {
  private String view;
  private int month;
  private int startDate;
  private int endDate;

  public CalendarViews(String v) {
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

  public String printYear() {
    return "";
  }

  public String printMonth() {
    return "";
  }

  public String printWeek() {
    return "";
  }

  public void getData(File f) {

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

  public int getFirstDayOfWeek(int startDate, int month, int year) {
    return -1;
  }

  // if the user asks, return weekday that date is on
  public String getWeekday(int day, int month, int year) {
    int day = (getFirstDayOfMonth(month, year) + day - 1) % 7;
    return "";
  }

  public static void main (String[] args) {
    CalendarViews test = new CalendarViews("yes");
    try {
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
    System.out.println(test.getFirstDayOfMonth(10, 2023));*/
    System.out.println(test.getFirstDayOfYear(2020));
    System.out.println(test.getFirstDayOfYear(2021));
    System.out.println(test.getFirstDayOfYear(2022));
    System.out.println(test.getFirstDayOfYear(2023));
    System.out.println(test.getFirstDayOfYear(2050));
  }

}
