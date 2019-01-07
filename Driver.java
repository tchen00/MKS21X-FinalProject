import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

  public static void main(String[] args) throws IOException {
    // Instantiate BufferedReader object
       BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));

       System.out.print("Input your evnt name :");
       String myName = myReader.readLine();
       System.out.println("Event name is : "+ myName);

   }
}
