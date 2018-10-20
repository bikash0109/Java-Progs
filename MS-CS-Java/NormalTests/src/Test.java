import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("How", "To", "Do", "In", "Java");

        String joinedString = String.join(", ", strList + "lol");

        System.out.println(joinedString);
    }
}
