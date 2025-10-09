import java.io.*;
import java.util.*;
import java.util.concurrent.*;

 class LineCountTask implements Callable<Integer> {
    private final File file;

    public LineCountTask(File file) {
        this.file=file;
    }

    public Integer call() {
        int lineCount=0;
        try (BufferedReader reader=new BufferedReader(new FileReader(file))) {
            while (reader.readLine()!=null) {
                lineCount++;
            }
            System.out.println(Thread.currentThread().getName()+" processed: "+file.getName()+" ("+lineCount+" lines)");
        } catch (IOException e) {
            System.err.println("Error reading file: "+file.getName()+" : "+e.getMessage());
        }
        return lineCount;
    }
}

public class ExecutorExample {

    public static void main(String[] args) {
      
        String directoryPath="D:/rudra/innogent/task/task2";

        File folder=new File(directoryPath);
        File[] files=folder.listFiles((dir, name) -> name.endsWith(".java"));

        if (files==null||files.length == 0) {
            System.out.println("No text files found in the directory.");
            return;
        }

        ExecutorService executor=Executors.newFixedThreadPool(4);

        List<Future<Integer>> results=new ArrayList<>();

        for (File file : files) {
            LineCountTask task=new LineCountTask(file);
            Future<Integer> future=executor.submit(task);
            results.add(future);
        }

        int totalLines=0;

        for (Future<Integer> future : results) {
            try {
                totalLines += future.get(); 
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error getting result: "+e.getMessage());
            }
        }

        System.out.println("\nTotal number of lines across all files: "+totalLines);

        executor.shutdown(); 
    }
}


