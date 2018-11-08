/*
 * Program Name: T_7.java
 *
 * Version :  1
 *
 * @author: Bikash Roy - br8376
 * @author: Tanay Bhardwaj
 *
 * */

import java.util.*;

public class T_7 extends Thread {
    static Object o = new Object();
    static int counter = 0;
    int id;

    public T_7(int id) {
        this.id = id;
    }

    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (++counter == 1)
            o = new Object();
        else
            o = new Object();

        synchronized (o) {

            System.err.println(id + " --->");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(id + " <---");
        }
    }

    public static void main(String args[]) {
        new T_7(1).start();
        new T_7(2).start();
        new T_7(3).start();
    }
}

//2 --->
//1 --->
//3 --->
//1 <---
//3 <---
//2 <---

// The first output can be as shown above, we are creating 3 new objects, So now all the three thread will have the key to enter the synchronised method.
// After print the first statement, all the thread goes to sleep. After sleep, the thread prints the second print statement and exits the method and program ends
// The manner in which the thread will execute is decided by the scheduler. After the thread sleep, any of these thread can be picked up by the scheduler and it will
// print.


//3 --->
//1 --->
//3 <---
//1 <---
//2 --->
//2 <---

// In this scenario, we got two sleep, one just after the run method starts and one in between the two print statement. No, suppose one of the threads stops after the
// if statement. Now we got tho thread to go inside the synchronised method. So suppose thread 3 goes in first and then thread 1. Thread 3 will print the first statement
// and go to sleep. Then thread 1 will go and print the first statement and sleep. Now scheduler can pick any of these threads to print and suppose it will pick thread
// 1. It will print the statement and exit, then thread 3 will print and exit. Now thread 2 will come in and print the first statement. Now since we don't have any
// thread left, it will print the 1st statement, sleep and then the second statement. Now, the program will exit.


//2 --->
//2 <---
//3 --->
//3 <---
//1 --->
//1 <---

// In this case, we have a sleep just after the new object has been created in the else statement and another after the first print statement.
// Now suppose one of the thread stops at the first else statement, and another stops at the else statement. Now we have thread 2 suppose.
// It enters the synchronised method, prints the statement and goes out. Now, 3 thread enters, prints and goes out, and at last the last thread enters
// prints and goes out and the program ends