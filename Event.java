public class Event {
  private String name;
  private int year;
  private int month;
  private int day;
  private int startTime;
  private int endTime;
  private String note;

  // constructor with everything
  public Event(String nameE, int yearE, int monthE, int dayE, int startTimeE, int endTimeE, String noteE) {
    name = nameE;
    year = yearE;
    month = monthE;
    day = dayE;
    startTime = startTimeE;
    endTime = endTimeE;
    note = noteE;
  }

  // empty constructor
  public Event(){

  }
  public String toString(){
    return "This event is: " + this.getName() + this.getDate() + this.getNotes(); // still need to figure out a way to print time
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
