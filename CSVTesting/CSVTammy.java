import java.io.*;

public class CSVTammy {
  public static void main(String[] args) throws FileNotFoundException, IOException{
    String csvFile = "sampleCalendar.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

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

    }


  }
