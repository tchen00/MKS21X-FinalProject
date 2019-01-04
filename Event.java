public class Event {
  private String name;
  private int year;
  private int month;
  private int day;
  private int startTime;
  private int endTime;
  private String note;

  public Event() {
    //CODE HERE
  }

  public String toString(){
    return null;
  }

  public String getName(){
    return name;
  }

  public String getDate(){
    return "" + month + day + year;
  }

  public String getNotes(){
    return note;
  }

  public void setName(String input){
    name = input;
  }

  public void setDate(int y, int m, int d){
    year = y;
    month = m;
    day = d;
  }

  public void setTime(int start, int end){
    startTime = start;
    endTime = end;
  }
  public void addNote(String n){
    note = n;
  }
}
