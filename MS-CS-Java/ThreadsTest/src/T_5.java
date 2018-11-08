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

public class T_5 extends Thread    {
    static Object o = new Object();
    static int    counter = 0;
    int id;

    public T_5(int id)	{
        this.id = id;
        System.err.println(o.hashCode());
    }
    public void run () {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ( ++counter == 1 ) {
            o = new Object();
            System.err.println(o.hashCode());
        }

        synchronized ( o ) {
            System.err.println(id + " --->" );
            System.err.println(id + " <---" );
        }
    }

    public static void main (String args []) {
        new T_5(1).start();
        new T_5(2).start();
        new T_5(3).start();
    }
}


//  1205044462 - Object hashcode
//  1205044462 - Object hashcode
//  1205044462 - Object hashcode
//  52184959 - Object hashcode
//  2091401114 - Object hashcode
//  3 --->
//  1 --->
//  3 <---
//  1 <---
//  2 --->
//  2 <---
// One Thread - suppose 3 goes in, followed by 1, makes a new object on the way, then enters the sync block, 2 comes in,
// but it is locked on the new object which is created by 1. hence the above output

//3 --->
//1 --->
//3 <---
//1 <---
//2 --->
//2 <---

// When we have two sleep, one before the if statement and one in between the two print statement.
// The 3 thread starts. It goes till the if statement, then thread one starts, So now thread 3 and thread 1 will have it's own o. Both of the thread can enter the synchronised method.
// Both of them enters, print the first statement and goes to sleep. After sleep, any of these two thread can be selected by scheduler. Then the second statement will be printed and both
// the thread will exit.
// Now, thread 2 will go in as it will have the key to enter the synchronised o. It will print the first sleep statement and now since there is no other thread present, it will sleep then
// wake up and execute the second statement and exit. The program will end now.


//1 --->
//1 <---
//3 --->
//3 <---
//2 --->
//2 <---

// Only one sleep is present in between the print statement
// The 1 thread comes, it is synchronised with o. It will go in and print first statement, sleep and then print the second statement. And exit. The 3rd will go, print and exit and at
// last 2nd will go, print and exit. Only one thread can go inside the synchronised statement in this scenario.