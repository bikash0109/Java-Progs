/*
 * Program Name: T_6.java
 *
 * Version :  1
 *
 * @author: Bikash Roy - br8376
 * @author: Tanay Bhardwaj
 *
 * */
import java.util.*;

public class T_6 extends Thread    {
    String o = " ";
    int id;

    public T_6(int id) {
        this.id = id;
    }
    public void run () {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized ( o ) {
            System.err.println(id + " --->" );
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(id + " <---" );

        }
    }

    public static void main (String args []) {
        new T_6(1).start();
        new T_6(2).start();
        new T_6(3).start();
    }
}

// In this question, it does not matter where we put the sleep, the output will always be in the same order:
// --->
// <---
// This is a simple multi thread question where we can find out which thread is printing what.
// It is synchronised over String o and strings are immutable.
// Just the thread id will change because it depends on the scheduler which thread it want to pick up.
// The thread will be picked up by the scheduler, it will go in the run method, do its job and exit. This will go on until all the threads has been executed and then the program
// will exit.