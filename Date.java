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
    if (d.equals("Sun")) return 0;
    if (d.equals("Mon")) return 1;
    if (d.equals("Tues")) return 2;
    if (d.equals("Wed")) return 3;
    if (d.equals("Thurs")) return 4;
    if (d.equals("Fri")) return 5;
    if (d.equals("Sat")) return 6;
    return -1;
  }

}
