import java.io.*;

public class CSVGrace {
  public static void main(String[] args) throws FileNotFoundException, IOException{
    BufferedReader br = new BufferedReader(new FileReader("test.csv"));
    String line = "";
    while ((line = br.readLine()) != null) {
      String[] country = line.split(",");
      System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
    }
    FileWriter fw = new FileWriter("test.csv", true);
    PrintWriter out = new PrintWriter(fw);
    out.append("This");// first row first column
    out.append(",");
    out.append("is");// first row second column
    out.append(",");
    out.append("amazing");// first row third column
    out.append("It's"); // second row first column.
    out.append(",");
    out.append("really");// second row second column
    out.append(",");
    out.append("amazing");// second row third column

       //Flush the output to the file
    out.flush();

       //Close the Print Writer
    out.close();

       //Close the File Writer
    fw.close();
    br.close();

  }
}
