
import java.util.Scanner;	// what is this good for?
import java.io.File;		// what is this good for?

public class Scanner7 {
    public static void asIs() {
        Scanner sc  = new Scanner(System.in);
        System.out.print(": ");
        while ( sc.hasNext() )
            System.out.print("-" + sc.next() + "+");
        sc.close();
        System.out.println();
    }
    public static void whiteSpace(String description, String theDelimiter) {
        Scanner sc  = null;
        try {
            sc  = new Scanner(new File( "words.txt") );
        } catch ( Exception e )	{}
        sc.reset();
        sc.useDelimiter(theDelimiter);	// A whitespace character: [ \t\n\x0B\f\r]
        System.out.println(description);
        System.out.println("\tdelemiter: " + theDelimiter);
        while ( sc.hasNext() )
            System.out.println("\t-" + sc.next() + "+");
        sc.close();
        System.out.println();
    }
    public static void main( String[] args ) {
        whiteSpace("java white space", "\\p{javaWhitespace}+");
        whiteSpace("white space* and comma and white spice*", "\\s*,\\s*");
        whiteSpace("white space+ and comma and white spice*", "\\s+,\\s*");
        whiteSpace("comma or semicolom", "\\s*(,|;)\\s*");
    }
}

