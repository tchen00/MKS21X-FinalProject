import java.util.*;

public class Date {

  // convert from int to String month
  public String convertToMonth(int n) {
    if (n == 1) return "January";
    if (n == 2) return "February";
    if (n == 3) return "March";
    if (n == 4) return "April";
    if (n == 5) return "May";
    if (n == 6) return "June";
    if (n == 7) return "July";
    if (n == 8) return "August";
    if (n == 9) return "September";
    if (n == 10) return "October";
    if (n == 11) return "November";
    if (n == 12) return "December";
    return null;
  }

  // checks if a year is a leap year
  public boolean isLeapYear(int year) {
    return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
  }

  // total of days in a month
  // easier to check for exceptions and get numberOfDays
  public int daysInMonth(int m, int y) {
    boolean leapYear = isLeapYear(y);
    if (leapYear && m == 2) return 29;
    else if (m == 2) return 28;
    if ((m > 7 && m % 2 == 0) || (m < 8 && m % 2 == 1)) return 31;
    return 30;
  }

  // from int to String weekday
  public String convertToDay(int n) {
    if (n == 0) return "Sunday";
    if (n == 1) return "Monday";
    if (n == 2) return "Tuesday";
    if (n == 3) return "Wednesday";
    if (n == 4) return "Thursday";
    if (n == 5) return "Friday";
    if (n == 6) return "Saturday";
    return null;
  }

  // from weekday to int
  public int convertToNum(String d) {
    if (d.equals("Sunday")) return 0;
    if (d.equals("Monday")) return 1;
    if (d.equals("Tuesday")) return 2;
    if (d.equals("Wednesday")) return 3;
    if (d.equals("Thursday")) return 4;
    if (d.equals("Friday")) return 5;
    if (d.equals("Saturday")) return 6;
    return -1;
  }

  // used to check for invalid date entered
  // note: years before 2019 are not valid
  public boolean validDate(int year, int month, int day){
    if (year < 2019 || month > 12 || month < 1) {
      return false;
    }
    if (day > 31 || (month == 2 && day > 29) ||
        (((month < 7 && month % 2 == 0) || (month > 8 && month % 2 == 1)) && day > 30)) {
      return false;
    }
    if (!(isLeapYear(year)) && (month == 2 && day == 29)){
      return false;
    } return true;
  }

  // sorts events alphabetically
  public static void insertionSortA(ArrayList<Event> data) {
    for (int i = 1; i < data.size(); i++) {
      Event current = data.get(i);
      int newPlace = i;
      for (int x = i - 1; x >= 0; x--) {
        if (data.get(x).getName().toLowerCase().compareTo(current.getName().toLowerCase()) > 0) {
          data.set(x+1,data.get(x)); // shifting
          newPlace = x; // where should current be
        }
        data.set(newPlace, current);
      }
    }
  }

  // sorts events chronologically
  public static void insertionSortC(ArrayList<Event> data) {
    for (int i = 1; i < data.size(); i++) {
      Event current = data.get(i);
      String date = current.getDate();
      int currentMonth = Integer.parseInt(date.substring(0,date.indexOf("/")));
      date = date.substring(date.indexOf("/")+1);
      int currentDay = Integer.parseInt(date.substring(0,date.indexOf("/")));
      date = date.substring(date.indexOf("/")+1);
      int currentYear = Integer.parseInt(date);
      int newPlace = i;
      for (int x = i - 1; x >= 0; x--) {
        Event checking = data.get(x);
        String checkingDate = checking.getDate();
        int checkingMonth = Integer.parseInt(checkingDate.substring(0,checkingDate.indexOf("/")));
        checkingDate = checkingDate.substring(checkingDate.indexOf("/")+1);
        int checkingDay = Integer.parseInt(checkingDate.substring(0,checkingDate.indexOf("/")));
        checkingDate = checkingDate.substring(checkingDate.indexOf("/")+1);
        int checkingYear = Integer.parseInt(checkingDate);
        if (checkingYear > currentYear) {
          data.set(x+1,data.get(x));
          newPlace = x;
        } else if (checkingYear == currentYear) {
          if (checkingMonth > currentMonth) {
            data.set(x+1,data.get(x));
            newPlace = x;
          } else if (checkingMonth == currentMonth) {
            if (checkingDay > currentDay) {
              data.set(x+1,data.get(x));
              newPlace = x;
            } else if (checkingDay == currentDay) {
              if (current.getStartTime().compareTo(checking.getStartTime()) < 0) {
                data.set(x+1,data.get(x));
                newPlace = x;
              }
            }
          }
        }
        data.set(newPlace, current);
      }
    }
  }

  }
