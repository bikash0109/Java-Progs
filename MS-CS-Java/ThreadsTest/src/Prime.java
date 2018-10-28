import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prime extends Thread {
    public int count = 0;
    public int lowerBound;
    public List<Integer> primeList = new ArrayList<>();

    public void run() {
        for (int n = lowerBound; n <= lowerBound + 1000; n++) {
            BigInteger bi = BigInteger.valueOf(n);
            if (bi.isProbablePrime(100)){
                count++;
                System.out.println("n : " + n);
                System.out.println("lowerBound : " + lowerBound );
                primeList.add(n);
            }
        }
    }
}