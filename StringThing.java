/*
* Program Name: StringThing.java
*
* Version :  1
*
* @author: Bikash Roy
* @author: Tanay Bhardwaj
*/

public class StringThing {
    public static void method(String id, String literal, String aNewString) {
        /* id will be concatenated with ".  " and the comparison result of the literal and aNewString */
        System.out.println(id + ".      " + (literal == aNewString));
    }

    public static void main(String args[]) {
        String aString = "123"; // 1 String is generated.
        String bString = "123"; // 1 String is generated.
        String cString = "1" + "23"; // 3 Strings are generated.
        int number = 3;

        /* "a." will be concatenated with "123" and then checked with aString.
        3 Strings are generated - "a.","123" , "a.123".
        Garbage collector will free the memory allocated for "a.123","a.", "123" */
        System.out.println("a.  " + "123" == aString);

        /* "b." will be concatenated with the boolean result of the expression ("12" + number == aString),
         where "12" will be concatenated to the number value, which is 3, and will be temporarily assigned new location
         and will be compared with aString, returning false.
         5 String - "b.", "12", number(converted to string), "123", "b. false"
         Garbage collector will free up the memory assigned to "b.", "12", "b.123"*/
        System.out.println("b.  " + ("12" + number == aString));


        /*"c. " concatenated with aString concatenated with evaluation of 1*23 (implicit conversion) = 23 - 12323
        3 Strings are generated here - "c.", "23"(1*23, converted to string), "c.12323".
        Garbage collector will free the memory allocated for the string "c.", 1, 23, "23", "c. 12323" "*/
        System.out.println("c.  " + aString + 1 * 23);

        /*"d. " concatenated with 123(implicit conversion to String) then number(implicit conversion to String) then aString
        4 Strings are generated here - "d.", 123 (converted to string), number (converted to String), "d.1233123"
        Garbage collector will free the memory allocated for "d.", 123, "123", "d.1233123"*/
        System.out.println("d.  " + 123 + number + aString);

        /* 123 added to 'number' then concatenated with aString. The whole string then concatenated with "e. "
        3 Strings are generated here - "e.", 126 (converted to string) , "e.126123".
        Garbage collector will free the memory allocated for "e.", "e.126123"*/
        System.out.println("e.  " + (123 + number) + aString);


        /* 121 (123-2) concatenated with "" then with number then with aString and finally with "f. "
        5 Strings are generated here- "f.", 121, "", number, f.1213123.
        Garbage collector will free the memory allocated for the string "f.",123, 2, "121","", "3", "f.1213123 "*/
        System.out.println("f.  " + (123 - 2 + "" + number + aString));

        /* "g. " concatenated with the multiplication of 123 and number and then concatenated with aString
        3 Strings are generated here - "g.", 369 (converted to string), "g.369123"
        Garbage collector will free the memory allocated for "g. ", 123, 369, "g.369123" */
        System.out.println("g.  " + 123 * number + aString);

        /*"h. "concatenated with the division of 123 and number then concatenated with aString
        3 Strings are generated here - "h.", 41 (converted to string), "h.41123"
        Garbage collector will free the memory allocated for - "h.", 123, 41, h.41123*/
        System.out.println("h.  " + 123 / number + aString);

        /*number subtracted from 123 then concatenated with "i. " and finally with aString
        3 Strings are generated here "i.", 120 (converted to string), "i.120123"
        Garbage collector will free the memory allocated for "i.", 123 , 120, "i.120123"*/
        System.out.println("i.  " + (123 - number) + aString);

        /* j. is concatenated with the boolean result of the comparison between "123" and aString, since aString is not
        a new String and "123" is already in the heap, both literals will point to same memory location, hence true is
        the result - "j. true"
        3 Strings are generated here - "j.", "123", "j.true"
        Garbage collector will free up - "j.", "123", "j.true"*/
        System.out.println("j.  " + ("123" == aString));

        /* g. is concatenated with the boolean result of the comparison between concatenation of "a" and "a" and aString,
        since "aa" is not a new String and ("a" + "a" = "aa") is already in the heap, both literals will point to same
        memory location, hence true is the result - "g. true"
        6 Strings are generated here - "g.", "a", "a" , "aa" , "aa", "g. true"
        Garbage collector will free up - "g.", "a", "a", "aa", "aa" "g.true"*/
        System.out.println("g.  " + ("a" + "a" == "aa"));

        /* h. is concatenated with the boolean result of the comparison between "123" and cString, since cString is not
        a new String and "123" is already in the heap, both literals will point to same memory location, hence true is
        the result - "h. true"
        3 Strings are generated here - "h.", "123", "h.true"
        Garbage collector will free up - "h.", "123", "h.true"*/
        System.out.println("h.  " + ("123" == cString));

        /* "1." will be concatenated to "x" to be compared to "x", hence result is false
        4 Strings will be generated - "1.", "x", "1.x", "x"
        Garbage collector will free up - "1.", "x" , "1.x", "x"*/
        System.out.println("1." + "x" == "x");

        /* At this point garbage collector will free the memory allocated for aString, bString, cString */

        /* String generated - 5 - "1", "xyz", "x", "yz", "1. true"
        Garbage collector will free up - "1", "x", "yz", "xyz", "1. true" */
        method("1", "xyz", "x" + "yz");

        /* String generated - 6 - "2", "xyz", "x", "yz", "xyz", "2. false"
        Garbage collector will free up - "2", "x", "yz", "xyz", "xyz", "2. false" */
        method("2", "xyz", new String("x") + "yz");

        /* String generated - 6 - "3", "xyz", "x", "y", "z", "3. true"
        Garbage collector will free up - "3", "x", "y", "z", "xyz", "3. true" */
        method("3", "xyz", "x" + "y" + "z");

        /* String generated - 8 - "4", "1", "2", "3", "123", 2 (Converted to string), 3 (converted to string), "4. true"
        Garbage collector will free up - "4", "1", "2", "3", "123", 2, 1, 3, "4. true" */
        method("4", "1" + "2" + "3", "1" + 2 * 1 + 3);

        /* String generated - 8 - "5", "1", "2", "3", "123", 2 (Converted to string), 3 (converted to string), "5. true"
        Garbage collector will free up - "5", "1", "2", "3", "123", 2, 3, "5. true" */
        method("5", "1" + "2" + "3", "1" + 2 + 3);

        /* String generated - 8 - "6", "1", "2", "3", "123", 2 (Converted to string), 3 (converted to string), "5. true"
        Garbage collector will free up - "6", "1", "2", "3", "123", 3, 1, 2, 3, "6. true" */
        method("6", "1" + "2" + "3", "1" + (3 - 1) + 3);
    }
}
