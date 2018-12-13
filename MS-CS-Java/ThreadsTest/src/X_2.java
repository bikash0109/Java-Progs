
import java.util.*;

public class X_2 implements Comparable {

    static final Comparator aComparator = new Comparator() {
        public int compare(Object o1, Object o2) {
            X_2 n1 = (X_2)o1;       // cast execption
            X_2 n2 = (X_2)o2;       // cast execption
            System.out.println("n1 : " + n1 + " n2 : " + n2);
            return n2.aString.charAt(0) - n1.aString.charAt(0);
        }
    };

    protected String  	  aString;
    protected int          id;

    public X_2(String aString) {
        this.aString = aString;
    }

    public String toString() {
        return aString + "/" + id;
    }
    public int compareTo(Object o) {
        X_2 n = (X_2)o;
        System.out.println(n.aString + " - " + aString );
        return aString.length() - n.aString.length();
    }
    public static void main(String args[]) {
        X_2 n[] = {
                new X_2("a"),
                new X_2("b"),
                new X_2("aa"),
                new X_2("bb"),
                new X_2("aaa"),
                new X_2("ccc")
        };
        TreeSet l = new TreeSet(aComparator);

        for ( int i = 0; i < n.length; i ++ ) {
            System.out.println("Inserting: " + n[i]);
            l.add(n[i]);
            System.out.println(l);
        }
        System.out.println("------------------");
        System.out.println(l);
    }
}
