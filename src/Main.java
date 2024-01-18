import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "src/data.txt";
        int loc = 141;
        String[] data = readData(fileName);
        writeData(data, loc);
    }

    private static void writeData(String[] data, int loc) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/output.csv"));
        double offset;
        String duplex;
        String name;
        double freq;

        for (String line : data) {
            String[] columns = line.split(" ");
            freq = Double.parseDouble(columns[1]);
            if (columns[2].equals("(simplex)")) {
                offset = 0;
                duplex = "";
            } else {
                offset = Math.round(Math.abs(freq - Double.parseDouble(columns[2])) * 10.0) / 10.0;
                if (freq > Double.parseDouble(columns[2])) {
                    duplex = "-";
                } else {
                    duplex = "+";
                }
            }
            if (columns.length == 4 || true) {
                name = columns[0];
            } else {
                name = "";
            }

            bw.write(loc + "," + name + "," + freq + "," + duplex + "," + offset + "\n");
            loc++;
        }
        bw.close();
    }

    private static String[] readData(String fileName) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        ArrayList<String> data = new ArrayList<>();
        String line;

        try {
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return data.toArray(new String[0]);
    }
}
