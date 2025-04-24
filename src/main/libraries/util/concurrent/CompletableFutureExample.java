package main.libraries.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

  // Simulate a time-consuming task
  private static String fetchData(String source) {
    System.out.println("Fetching data from " + source + " on thread: " + Thread.currentThread().getName());
    try {
      TimeUnit.SECONDS.sleep(2); // Simulate network delay
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
    return "Data from " + source;
  }

  // Simulate another time-consuming processing step
  private static String processData(String data) {
    System.out.println("Processing data '" + data + "' on thread: " + Thread.currentThread().getName());
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
    return data.toUpperCase();
  }

  public static void main(String[] args) throws Exception {
    ExecutorService customExecutor = Executors.newFixedThreadPool(4);
    System.out.println("Main thread: " + Thread.currentThread().getName());

    CompletableFuture<String> futureResult = CompletableFuture
        .supplyAsync(() -> fetchData("Database"), customExecutor) // 1. Fetch data async
        .thenApplyAsync(data -> processData(data), customExecutor) // 2. Process it async when fetched
        .thenApply(processedData -> "Final Result: " + processedData) // 3. Simple sync transformation
        .exceptionally(ex -> { // 4. Handle potential errors in the chain
          System.err.println("An error occurred: " + ex.getMessage());
          return "Error Result";
        });

    // We can do other things here while the async tasks run...
    System.out.println("Tasks submitted, main thread continuing...");
    TimeUnit.MILLISECONDS.sleep(500); // Simulate other work

    // Now, let's get the final result (blocking here just for the example)
    // In a real application (like a web server), you might return the
    // CompletableFuture itself or use thenAccept/thenRun at the end.
    System.out.println("Waiting for result...");
    String result = futureResult.join(); // Use join() to wait (blocks)

    System.out.println("Final outcome: " + result);

    customExecutor.shutdown(); // Important to shut down the executor
  }
}