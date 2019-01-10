public class Date {

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

  public boolean isLeapYear(int year) {
    return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
  }

  public int daysInMonth(int m, int y) {
    boolean leapYear = isLeapYear(y);
    if (leapYear && m == 2) return 29;
    else if (m == 2) return 28;
    if ((m > 7 && m % 2 == 0) || (m < 8 && m % 2 == 1)) return 31;
    return 30;
  }

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

  }
