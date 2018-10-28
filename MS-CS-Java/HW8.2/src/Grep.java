/*
 * Program Name: Grep.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements the subset of Grep of UNIX.
 *
 * • -c
 * • -l
 * • -w
 * • -q
 *
 * By default, the word entered is matched with the file and the lines are displayed.
 * */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    // method to match the pattern
    public static void findGrep(String word, Vector<String> fileName, Vector<String> instructions) throws Exception {
        if (instructions.contains("-q")) {
            return;
        }
        BufferedReader br = null;
        boolean has_c = instructions.contains("-c");
        boolean has_l = instructions.contains("-l");
        boolean has_w = instructions.contains("-w");
        Map<String, String> outputSet = new HashMap<>();
        if (has_w) {
            word = "\\b" + word + "\\b";
        }
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher("");
        if(fileName.size() > 0){
            for (String file : fileName) {
                int count = 0;
                String line;
                String matchedLine = "";
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (IOException e) {
                    System.err.println("Cannot read '" + file + "': " + e.getMessage());
                }
                while ((line = br.readLine()) != null) {
                    matcher.reset(line);
                    if (matcher.find()) {
                        count++;
                        matchedLine += line + "\n";
                    }
                }
                outputSet.put(file, matchedLine + "@" + count);
            }
            br.close();
        }else {
            if(fileName.size() == 0 && instructions.size() == 1 && instructions.firstElement().equals("-")){
                int count = 0;
                String matchedLine = "";
                Scanner sc = new Scanner(System.in);
                String standardInput = sc.nextLine();
                matcher.reset(standardInput);
                if (matcher.find()) {
                    count++;
                    matchedLine += standardInput + "\n";
                }
                outputSet.put(standardInput, matchedLine + "@" + count);
            }
        }


        printOutput(outputSet, instructions, fileName, has_l, has_c, has_w);
    }

    // method to check for the order of execution and printing the desired output
    public static void printOutput(Map<String, String> outputSet, Vector<String> instructions,
                                   Vector<String> fileName, boolean has_l, boolean has_c, boolean has_w) {
        String instruction = instructions.size() > 0 ? instructions.firstElement() : "";
        switch (instruction) {
            case "-":
                outputSet.forEach((key, value) -> {
                    String valuePart = value.substring(0, value.indexOf("@"))
                            .replace("\n", "\n" + key + " : ");
                    String valuePartSub = valuePart.length() == 0 ? " " : valuePart.substring(0, valuePart.lastIndexOf(key));
                    System.out.println(key
                            + " : " +
                            valuePartSub);
                });
                break;
            case "-c":
                if (!outputSet.isEmpty()) {
                    if (has_l) {
                        if (fileName.size() > 1) {
                            outputSet
                                    .forEach((key, value) ->
                                            System.out.println(key + " : " +
                                                    (value.contains("0") ? 0 : 1) + "\n" + key));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(1 + "\n" + key));
                        }
                    } else {
                        if (fileName.size() > 1) {
                            outputSet.forEach((key, value) ->
                                    System.out.println(key + " : " + value.substring(value.lastIndexOf("@") + 1)));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(value.substring(value.lastIndexOf("@") + 1)));
                        }
                    }
                } else
                    System.out.println();
                break;
            case "-w":
                if (!outputSet.isEmpty()) {
                    if (has_c && !has_l) {
                        if (fileName.size() > 1) {
                            outputSet.forEach((key, value) ->
                                    System.out.println(key + " : " + value.substring(value.lastIndexOf("@") + 1)));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(value.substring(value.lastIndexOf("@") + 1)));
                        }
                    } else if (!has_c && has_l) {
                        outputSet.forEach((key, value) -> System.out.println(key));
                    } else if (has_c && has_l) {
                        if (fileName.size() > 1) {
                            outputSet
                                    .forEach((key, value) ->
                                            System.out.println(key + " : " +
                                                    (value.contains("0") ? 0 : 1) + "\n" + key));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(1 + "\n" + key));
                        }
                    } else {
                        if (fileName.size() > 1) {
                            outputSet.forEach((key, value) ->
                                    System.out.println(key + " : " + value.substring(0, value.indexOf("@"))));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(value.substring(0, value.indexOf("@"))));
                        }
                    }
                } else
                    System.out.println();
                break;
            case "-l":
                if (!outputSet.isEmpty()) {
                    if (has_c && has_w)
                        if (fileName.size() > 1) {
                            outputSet
                                    .forEach((key, value) ->
                                            System.out.println(key + " : " +
                                                    (value.contains("0") ? 0 : 1) + "\n" + key));
                        } else {
                            outputSet.forEach((key, value) ->
                                    System.out.println(1 + "\n" + key));
                        }
                    else if (!has_c && has_w) {
                        outputSet.forEach((key, value) ->
                                System.out.println((value.contains("0") ? "" : key)));
                    } else
                        outputSet
                                .forEach((key, value) ->
                                        System.out.println((value.contains("0") ? "" : key)));
                } else
                    System.out.println();
                break;
            default:
                outputSet.forEach((key, value) ->
                        System.out.println(value.substring(0, value.indexOf("@"))));
                break;

        }
    }


    public static void main(String[] argv) throws Exception {
        Vector<String> instructions = new Vector<>();
        Vector<String> fileName = new Vector<>();
        String[] word = {null};
        Arrays.stream(argv).forEach(arg -> {
            if (arg.startsWith("-")) {
                instructions.add(arg);
            } else if (arg.contains(".txt")) {
                fileName.add(arg);
            } else {
                word[0] = arg;
            }
        });
        findGrep(word[0], fileName, instructions);
    }
}