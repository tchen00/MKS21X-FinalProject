public class Event extends Date{
  private String name;
  private int year;
  private int month;
  private int day;
  private String startTime;
  private String endTime;
  private String note;

  // constructor with everything
  public Event(String nameE, int yearE, int monthE, int dayE, String startTimeE, String endTimeE, String noteE) {
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
    return "Event: " + this.getName() + "\n" + "Date & Time: " + this.getDate() + "\t"+ this.getStartTime() + " - " + this.getEndTime()+ "\n" + "Notes: " + this.getNotes(); // still need to figure out a way to print time
  }

  public String toShortString(){
    return  this.getName() + "|"  + this.getDate();
  }

  public String getName(){
    return name;
  }

  public String getDate(){
    return "" + month + "/" + day + "/" + year;
  }

  public String getNotes(){
    return note;
  }

  public String getStartTime(){
    return startTime;
  }

  public String getEndTime(){
    return endTime;
  }

  public void setName(String input){
    name = input;
  }

  public void setDate(int y, int m, int d){
    if (!validDate(y,m,d)) {
      throw new IllegalArgumentException("Your date inputed is invalid.");
    }
    year = y;
    month = m;
    day = d;
  }

  public void setTime(String start, String end){
    startTime = start;
    endTime = end;
  }
  public void addNote(String n){
    note = n;
  }



}
