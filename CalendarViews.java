import java.io.*;

public class CalendarViews {
  private String view;
  private int month;
  private int startDate;
  private int endDate;

  public CalendarViews(String v) {

  }

  public String getView() {
    return "";
  }

  public void setView(String s) {

  }

  public String viewDate() { // might not be useful later
    return "";
  }

  // returns number of days since Jan 1 2019
  public int numberOfDays(int day, int month, int year) {
    int result = 0;
    // adding days from years
    for (int i = year - 1; i >= 2019; i--) {
      if (i % 4 == 0 && !(i % 100 == 0 && i % 400 != 0)) {
        result += 366;
      } else {
        result += 365;
      }
    }
    // THIS IS NOW 2019 (NOT A LEAP YEAR)
    // adding days from months
    for (int i = month - 1; i > 0; i--) {
      int tempDays = 0; // to keep track of how many days are in that month
      if (i == 12 || i == 10 || i == 8 || i == 7 || i == 5 || i == 3 || i == 1) {
        tempDays = 31;
      } else if (i == 2) {
        tempDays = 28;
      } else {
        tempDays = 30;
      }
      result += tempDays;
    }
    // THIS IS NOT THAT MONTH SPECIFICALLY
    result += day;
    result -= 1;
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

  public int getFirstDayOfMonth(int month, int year) {
    return -1;
  }

  public int getFirstDayOfYear(int year) {
    return -1;
  }

  public int getFirstDayOfWeek(int startDate, int month, int year) {
    return -1;
  }

  public String getWeekday(int day) {
    return "";
  }

  public static void main (String[] args) {
    CalendarViews test = new CalendarViews("yes");
    System.out.println(test.numberOfDays(1, 1, 2020));
  }

}
