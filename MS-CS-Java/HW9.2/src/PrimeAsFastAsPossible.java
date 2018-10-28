/*
 * Program Name: ConsumerProducer.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * A program that implements a multi-threaded solution to find prime numbers for a given n.
 *
 * logic : Odd thread executes odd multiples of numbers and even thread strikes the even multiples
 *
 * */


class PrimeAsFastAsPossible {
    boolean[] prime;
    int n;

    PrimeAsFastAsPossible(int n) {
        this.n = n;
        this.prime = new boolean[n + 1];
        for (int i = 0; i < n ; i++) {
            prime[i] = true;
        }
    }

    class MyThread extends Thread {
        int id;

        MyThread(int id) {
            this.id = id;
        }

        public void run() {
            for (int p = 2; p * p <= n; p++) {
                if ((p % 2 == 0 && this.id % 2 == 0) || (p % 2 != 0 && this.id % 2 != 0)) {
                    // If prime[p] is not changed, then it is a prime
                    if (prime[p] == true) {
                        // Update all multiples of p
                        for (int i = p * 2; i <= n; i += p) {
                            prime[i] = false;
                        }
                    }
                }
            }
        }
    }

    void go() {
        long start = System.currentTimeMillis();
        int arraySize = Runtime.getRuntime().availableProcessors();
        MyThread[] thread = new MyThread[arraySize];
        for (int i = 0; i < arraySize; i++) {
            thread[i] = new MyThread(i);
            thread[i].start();
        }
        for (int i = 0; i < arraySize; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException ex) {

            }
        }
        long stop = System.currentTimeMillis();
        System.out.println("Time of execution: " + (stop - start));
    }

    public static void main(String args[]) throws ArgumentMissingException, NotANumberException, NegativeNumberException{
        try {
            int n = Integer.parseInt(args[0]);
            if(n < 0){
                throw new NegativeNumberException("");
            }
            PrimeAsFastAsPossible g = new PrimeAsFastAsPossible(n);
            g.go();
        }catch (ArrayIndexOutOfBoundsException ex) {
            throw new ArgumentMissingException("Argument Missing");
        }catch (NumberFormatException ex){
            throw new NotANumberException("Not a number");
        }catch (NegativeNumberException ex){
            throw new NegativeNumberException("Negative Number cannot be processed.");
        }
    }
} 