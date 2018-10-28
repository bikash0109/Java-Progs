import java.math.BigInteger;
import java.util.ArrayList;

class MyThread extends Thread {
    static int id;
    static long num;
    static ArrayList<Boolean> prime;

    MyThread(int id, long num, ArrayList<Boolean> prime) {
        this.num = num;
        this.id = id;
        this.prime = prime;
    }

    public void run() {
        for (int p = 2; p * p <= num; p++) {
            if ((p % 2 == 0 && this.id % 2 == 0) || (p % 2 != 0 && this.id % 2 != 0)) {
                // If prime[p] is not changed, then it is a prime
                if (prime.get(p) == true) {
                    // Update all multiples of p
                    for (int i = p * 2; i <= num; i += p) {
                        prime.remove(i);
                        prime.add(i, false);
                    }
                }
            }
        }
    }
}


class SieveOfEratosthenes {
    ArrayList<Boolean> prime;
    long n;

    SieveOfEratosthenes(long n) {
        this.n = n;
    }

    void sieveOfEratosthenes() {
        int arraySize = Runtime.getRuntime().availableProcessors();
        this.prime = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            System.out.println(i);
            prime.add(i, true);
        }
        MyThread[] thread = new MyThread[arraySize];
        for (int i = 0; i < arraySize; i++) {
            thread[i] = new MyThread(i, n, prime);
            thread[i].start();
        }
        for (int i = 0; i < arraySize; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException ex) {

            }
        }
    }

    void printPrime() {
        // Print all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime.get(i) == true)
                System.out.print(i + " ");
        }
    }

    // Driver Program to test above function 
    public static void main(String args[]) {
        long n = 3168885720L;
//        System.out.print("Following are the prime numbers ");
//        System.out.println("smaller than or equal to " + n);
        SieveOfEratosthenes g = new SieveOfEratosthenes(n);
        g.sieveOfEratosthenes();
        //g.printPrime();
    }
} 