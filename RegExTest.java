/*
 * Program Name: RegExTest.java
 *
 * Version :  1
 *
 * @author: Bikash Roy
 * @author: Tanay Bhardwaj
 *
 *
 * This program, checks for various properties of string using pattern class, as entered in the .txt file.
 *
 * Properties of a string are:
 *  - ´a’ is part of the word.
    - Palindrome anchored at the beginning and end of line.
    - Include a palindrome which is 2 characters long.
    - Include a palindrome which is 3 characters long.
    - The word has at least one ’a’ in it.
    - The word consist only of ’a’s or ’b’s.
    - ´a’s or ’b’s can not be part of the word.
    - The word is == ’.’.
    - The word includes a ’.’.
 * */


import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {
    // Make directory for saving the RegExTest file.
    public static String filepath = FileSystemView.getFileSystemView().getHomeDirectory().toString() + "/bikashroy_RegExTestFile";

    // Set the path for creating the file - also the file name.
    final static String FILENAME = filepath + "/RegExTest.txt";

    // This method creates the RegEx Test Directory and File with some words in new line.
    public void createAndWriteDataToTextFile() throws IOException {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        if (!(new File(filepath).exists()))
            new File(filepath).mkdirs();
        if (!(new File(FILENAME).exists())) {
            String content = ".\n" + "a\n" + "aba\n" + "abb\n" + "cd\n" + "axxx\n" + "abca\n" + "Hello World.";
            fileWriter = new FileWriter(FILENAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            System.out.println("File is created, and contents are inserted in the File.");
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (fileWriter != null)
                fileWriter.close();
        }
    }

    /*This method reads the file, and stores each line into a String Vector, and for each element in Vector, check
     methods are called. */
    public void readTheFileAndCheckForProperties() {
        File file = new File(FILENAME);
        Vector<String> regTextFileData = new Vector<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                regTextFileData.add(sc.nextLine());
            }
            for (String line : regTextFileData) {
                regExTest(line);
                ownTest(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void regExTest(String line) {
        System.out.println("\nRegEx Test for : " + line);

        // Checks for letter "a" in the string.
        if (line.matches(".*a.*"))
            System.out.println("'a' is part of the word : " + line + "\nThe word : " + line + " has at least one ’a’ in it");

        // Check for anchor and Palindrome
        if (line.matches("(.).*\\1"))
            System.out.println("Palindrome anchored at the beginning and end of line");
        else if (line.length() == 1 && !line.equals("."))
            System.out.println("Palindrome anchored at the beginning and end of line");

        if (isPalindromeUsingPattern(line)) {
            System.out.println(line + " : is a Palindrome.");
        } else
            System.out.println(line + " : is not a Palindrome.");

        // Check for Substrings which are Palindrome
        Pattern p = Pattern.compile("(\\w)\\1+");
        Matcher m = p.matcher(line);
        if (m.find()) {
            System.out.println(line + " : Include a palindrome which is " + m.group().length() + " characters long. The Palindrome part is. (" + m.group() + ")");
        }

        // Check for a's and b's
        String patternasandbs = "(a+|b+)*";
        if (line.matches(patternasandbs))
            System.out.println(line + " : only consists of a's or b's");
        else
            System.out.println("´a’s or ’b’s can not be part of the word :" + line);


        //Check for (.)
        Pattern dot = Pattern.compile("^\\.$");
        Matcher matchOnlyDot = dot.matcher(line);
        if (matchOnlyDot.find())
            System.out.println("The word is == ’.’");

        Pattern dotPattern = Pattern.compile("^[^.]*\\.[^.]*$");
        Matcher matchDotPattern = dotPattern.matcher(line);
        if (matchDotPattern.find())
            System.out.println("The word : " + line + " includes a ’.’\n");
        else
            System.out.println("\n");
    }

    public void ownTest(String line) {
        System.out.println("Own Test for : " + line);

        //Check if "a" is part of the word
        if (line.contains("a"))
            System.out.println("'a' is part of the word : " + line + "\nThe word : " + line + " has at least one ’a’ in it");

        //Check for anchor and Palindrome
        if (line.charAt(0) == line.charAt(line.length() - 1))
            System.out.println("Palindrome anchored at the beginning and end of line");
        else { // Check for substring which are Palindrome
            Vector<String> finalPalin = new Vector<>();
            StringBuffer newPalin = new StringBuffer();
            for (int i = 1; i < line.length() - 1; i++) {
                if (line.charAt(i) == line.charAt(i + 1)) {
                    if (newPalin.length() > 1 && line.charAt(i) != line.charAt(i - 1)) {
                        finalPalin.add(newPalin.toString());
                        newPalin = new StringBuffer();
                    }
                    newPalin.append(line.charAt(i)).append(line.charAt(i + 1));
                }
            }
            finalPalin.add(newPalin.toString());
            for (String elements : finalPalin) {
                System.out.println("Include a palindrome which is " + elements.length() + " character long. The palindrome part is. (" + elements + ")");
            }
        }
        if (line.equals(new StringBuffer(line).reverse().toString()))
            System.out.println(line + " : is a Palindrome.");
        else
            System.out.println(line + " : is not a Palindrome.");

        // Check for a's and b's
        boolean onlyAB = false;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'a')
                onlyAB = true;
            else if (line.charAt(i) == 'b')
                onlyAB = true;
            else {
                onlyAB = false;
                break;
            }

        }
        if (onlyAB)
            System.out.println("The word consist only of ’a’s or ’b’s.");
        else
            System.out.println("´a’s or ’b’s can not be part of the word.");

        //Check for (.)

        if (line.equals("."))
            System.out.println("The word is == \'" + line + "\'");

        long count = line.chars().filter(dot -> dot == '.').count(); // lambda to check for a dot in the string.
        if (count == 1)
            System.out.println("The word : " + line + " includes a ’.’\n");


    }

    // Checks if a string is palindrome, recursively.
    public static boolean isPalindromeUsingPattern(String line) {

        final String palindromeRegex = "(?x) | (?:(.) lookahead)+".replace("lookahead", "(?<=(?=^.*? (\\1?)$).*)");

        //String palin = "(?x) | (?:(.) (?<=(?=^.*? (\\1 \\2?)$).*))+";

        //Pattern p = Pattern.compile(palin);
        //Matcher m = p.matcher(line);

//        while(m.find()) {
//            if(m.group().length() != 0)
//                System.out.println(m.group().trim());
//
//            System.out.println("Start Index: " + m.start() + "\nEnd Index" + m.end());
//        }


        // (?x | ?:(.) (?<= (?=^.*? (\1 \2)$).*))+

        //System.out.println(line.matches(palin));
        return line.matches(palindromeRegex);
    }

    public static void main(String[] args) throws IOException {
        //RegExTest regExTest = new RegExTest();
        //regExTest.createAndWriteDataToTextFile();
        //regExTest.readTheFileAndCheckForProperties();
        isPalindromeUsingPattern("abbabba");
    }
}