import java.util.concurrent.*;
import java.util.stream.LongStream;

class ComputationOperation {

    long performTask(long n) {
        long result = 0;
        for (long i = 1; i <= n; i++) {
            result += i;  
        }
        return result;
    }
}
public class ParrallelStream {
    public static void main(String[] args) throws Exception {

        ComputationOperation task = new ComputationOperation();
        long N = 1000000000L;  
        int numThreads = Runtime.getRuntime().availableProcessors();

        System.out.println("\n--- Performance Comparison: Parallel Stream vs ExecutorService ---");
        System.out.println("Available Cores: " + numThreads + "\n");

       
        long parallelStart = System.currentTimeMillis();

        long parallelResult = LongStream.rangeClosed(1, numThreads)
                .parallel()
                .map(i -> task.performTask(N / numThreads))
                .sum();

        long parallelEnd = System.currentTimeMillis();

        System.out.println("Parallel Stream Result: " + parallelResult);
        System.out.println("Time Taken (Parallel Stream): " + (parallelEnd - parallelStart) + " ms\n");

        
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        Future<Long>[] futures = new Future[numThreads];

        long executorStart = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            futures[i] = executor.submit(() -> task.performTask(N / numThreads));
        }

        long totalResult = 0;
        for (Future<Long> f : futures) {
            totalResult += f.get();
        }

        long executorEnd = System.currentTimeMillis();
        executor.shutdown();

        System.out.println("ExecutorService Result: " + totalResult);
        System.out.println("Time Taken (ExecutorService): " + (executorEnd - executorStart) + " ms");

       
        System.out.println("\n--- Summary ---");
        System.out.println("Parallel Stream Time : " + (parallelEnd - parallelStart) + " ms");
        System.out.println("ExecutorService Time : " + (executorEnd - executorStart) + " ms");
    }
}
