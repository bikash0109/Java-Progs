import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Worker {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How Many threads? ");
        int nThreads = scan.nextInt();  // Create variable 'n'  to handle whatever integer the user specifies.  nextInt() is used for the scanner to expect and Int.

        final Prime[] pThreads = new Prime[nThreads];
        long startTime = System.currentTimeMillis();
        for(int i = 0; i<nThreads; i++){
            pThreads[i] = new Prime();
            pThreads[i].lowerBound = i;
            pThreads[i].start();
        }

        try {
            for (int i = 0; i < nThreads; i++)
                pThreads[i].join();
        } catch (InterruptedException e) {
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Execution time = : "+  elapsedTime);
        System.out.println("----------------------------------------------------");
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("How many Cores this Java Program used: " + cores);
        for (int i = 0; i < nThreads; i++)
            System.out.println("Thread " + i + "  Prime count: " + pThreads[i].count); // Display Thread count
        List<Integer> allPrimes = new ArrayList<>();
        for (Prime p : pThreads) {
            allPrimes.addAll(p.primeList);
        }
        System.out.println("Total prime count: " + allPrimes.size()); // Output total amount of primes from the Array List
//        for (int i = 0; i < 10000; i++) // Display first 100 primes.
//            System.out.println(allPrimes.get(i));
    }
}