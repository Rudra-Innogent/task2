import java.io.*;
import java.util.*;

class FileHandler {
    public static void saveToFile(String filename, List<String> lines) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for(String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch(IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) lines.add(line);
        } catch(FileNotFoundException e) {
            System.out.println("File not found: " + filename + ". Initializing empty collection.");
        } catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }
}
