import java.io.*;

public class CSVTammy {
  public static void main(String[] args) throws FileNotFoundException, IOException{
    String csvFile = "sampleCalendar.csv";
    BufferedReader br = new BufferedReader(new FileReader("sampleCalendar.csv"));
    String line = "";
    String cvsSplitBy = ",";
/*
    try {

        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {

            // use comma as separator
            String[] country = line.split(cvsSplitBy);

            System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/
    FileWriter fw = new FileWriter("sampleCalendar.csv", true);
    PrintWriter out = new PrintWriter(fw);
    out.append("This");
    out.append(",");
    out.append("is");
    out.append(",");
    out.append("amazing");
    out.append("It's");
    // out.newLine();
    out.append("\n"); // adds to NEXT ROW *************
    out.append("really");
    out.append(",");
    out.append("amazing");
    out.flush();

       //Close the Print Writer
    out.close();

       //Close the File Writer
    fw.close();
    br.close();
    }


  }
