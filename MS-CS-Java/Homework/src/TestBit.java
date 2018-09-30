import java.util.Scanner;
import java.util.regex.MatchResult;

public class TestBit {
    public static void printIt(String input) {
        Scanner sc = new Scanner(input);

        System.out.println("sc.findInLine: " +
                sc.findInLine("(\\d+) fish (\\d+) fish (\\w+) fish (\\w+)"));
        MatchResult result = sc.match();

        for (int i = 1; i <= result.groupCount(); i++) {
            System.out.println(i + ": " + result.group(i));
        }
    }

    public static void main(String[] args) {
        String input = "1 fish 2 fish red fish blue fish";
        printIt(input);
    }
}
