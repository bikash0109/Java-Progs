/*
 * Program Name: TestMath.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 *
 *
 * This program, performs some Math operations, without using Java Math class. - sqrt(), max(), abs().
 *
 * */

import java.util.Random;

public class TestMath {
    public static double epsilon = 0.000000001; // error precision.

    public static double abs(Double testNumber, boolean isTestCall) {
        double outPutValue = testNumber < 0 ? -testNumber : testNumber;
        if(!isTestCall)
            System.out.println("The absolute value of " + testNumber + " is. " + outPutValue);
        return outPutValue;
    }

    public static void max(int firstNumber, int secondNumber) {
        System.out.println("\nMaximum of " + firstNumber + " and " + secondNumber + " is. : " + (firstNumber > secondNumber ?
                firstNumber : secondNumber));
    }

    //Babylonian method for square root
    public static double sqrt(double testNumber) {
        if (testNumber >= 0) {
            System.out.println("\nsqrt(" + testNumber + ") :");
            if (testNumber == 1) {
                    System.out.println("sqrt(" + testNumber + ") ~= " + testNumber + "\n ( " + testNumber + "^2 = )" + Math.pow(testNumber, 2));
                return testNumber;
            }
            double n = testNumber; // Taking initial number as the approximation number
            double guess = testNumber / 2;

            while (n - guess > epsilon) {
                n = (n + guess) / 2; // Average of the number and the guess.
                    System.out.println("x_n = " + n);
                guess = testNumber / n; // reduce the guess by n times
            }
                System.out.println("sqrt(" + testNumber + ") ~= " + n + "\n ( " + n + "^2 ) = " + Math.pow(n, 2));
            return n;
        } else {
                System.out.println("\nsqrt(" + testNumber + ") ~= " + Double.NaN);
            return Double.NaN;
        }
    }

    static void testSqrt() {
        if (0 != sqrt(0))
            System.out.println("Test 1: sqrt failed");
        else
            System.out.println("Test 1: 0 != sqrt(0) - Passed.");
        if (0 != sqrt(-0))
            System.out.println("Test 2: sqrt failed");
        else
            System.out.println("Test 2: 0 != sqrt(-0) - Passed.");
        if (Double.NaN == sqrt(-1))
            System.out.println("Test 3: sqrt failed");
        else
            System.out.println("Test 3: Double.NaN == sqrt(-1) - Passed.");
        if (Double.NaN == sqrt(Double.NaN))
            System.out.println("Test 4: sqrt failed");
        else
            System.out.println("Test 4: Double.NaN == sqrt(Double.NaN) - Passed.");
        double result;
        double theDoubles[] = {1, 2, 3, 4, 5};
        for (int index = 0; index < theDoubles.length; index++) {
            result = sqrt(theDoubles[index]);
            if (abs(result * result - theDoubles[index], true) > epsilon)
                System.out.println("Test 5: sqrt failed: " + (result * result - theDoubles[index]));
            else
                System.out.println("Test 5: sqrt (" + theDoubles[index] + ") - Passed.");
        }
    }

    static void testMax() {
        // - + 0
        // + - 1    -> Associativity
        // + + 2
        // - - 3
        // = = 4
        // 0 - 5
        // 0 + 6
        for(int n = 0 ; n < 7 ; n++)
         {
            switch (n) {
                case 0:
                    System.out.println("\nTest for -Number and +Number : ");
                    max(-(n + new Random().nextInt(4)), n + new Random().nextInt(4));
                    break;

                case 1:
                    System.out.println("\nTest for +Number and -Number : ");
                    max(n + new Random().nextInt(4), -(n + new Random().nextInt(4)));
                    break;

                case 2:
                    System.out.println("\nTest for +Number and +Number : ");
                    max(n + new Random().nextInt(4), n + new Random().nextInt(4));
                    break;

                case 3:
                    System.out.println("\nTest for -Number and -Number : ");
                    max(-(n + new Random().nextInt(4)), -(n + new Random().nextInt(4)));
                    break;

                case 4:
                    System.out.println("\nTest for same Number : ");
                    max(n + 40, n + 40);
                    break;

                case 5:
                    System.out.println("\nTest for 0 and -Number : ");
                    max(0, -(n + new Random().nextInt(4)));
                    break;

                case 6:
                    System.out.println("\nTest for 0 and +Number : ");
                    max(0, n + new Random().nextInt(4));
                    break;
            }
        }
    }

    public static void testAbs()
    {
        for(int n = 1; n < 3 ; n ++) {
            switch (n) {
                case 1:
                    System.out.println("\nTest of absolute value of a negative double");
                    abs(-4.54, false);
                    break;
                case 2:
                    System.out.println("\nTest of absolute value of a positive double");
                    abs(6.75, false);
                    break;
                default:
                    System.out.println(Double.NaN);
            }
        }
    }

    public static void main(String[] args) {
        //testSqrt();
        //testMax();
        //testAbs();
        System.out.println(new Random().nextInt(2));
    }
}
