/*
 * Program Name: PiEvenOddImprovement.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements the STDIN file inputstream to read from command line arguments and count the number of odd
 * and even digits in pi, upto billion digits.
 *
 * The file can be a compressed file, Gzip stream is used to decompress that file
 * */


import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.zip.GZIPInputStream;

public class PiEvenOddImprovement {
    public static void main(String[] args) throws Exception {
        int num, evenCount = 0, oddCount = 0;
        try {
            if (args.length > 0) {
                if (args[0].contains(".txt")) {
                    BufferedReader br;
                    if (args[0].contains(".gz")) {
                        GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(args[0]));
                        br = new BufferedReader(new InputStreamReader(gzip));
                    } else {
                        File file = new File(args[0]);
                        InputStream in = new FileInputStream(file);
                        br = new BufferedReader(new InputStreamReader(in));
                    }
                    num = br.read();
                    if (num == -1) {
                        throw new EmptyFileException("EmptyFileException");
                    }
                    while (num != -1) {
                        char ch = (char) num;
                        if (ch != '.' && Character.isDigit(ch)) {
                            if ((num & 1) == 0)
                                evenCount++;
                            else
                                oddCount++;
                        } else {
                            if(ch != '.')
                                throw new NoNumbersException("NoNumbersException");
                        }
                        num = br.read();
                    }
                } else {
                    System.out.println("File extension is missing or incorrect.");
                }
            } else {
                throw new ArgumentMissingException("ArgumentMissingException");
            }
        }
        catch (NoNumbersException noNum){
            System.err.println(noNum.getMessage() + ": null is empty.");
        }
        catch (EmptyFileException efile){
            System.err.println(efile.getMessage() + ": null is empty.");
        }
        catch (ArgumentMissingException am){
            System.err.println("Arguments missing!");
        }
        finally {
            BigDecimal odd = new BigDecimal(oddCount);
            BigDecimal even = new BigDecimal(evenCount);
            System.out.println("even : = " + evenCount);
            System.out.println("odd : = " + oddCount);
            System.out.println("odd/even : = " +
                    ((oddCount == 0 && evenCount == 0) ? Double.NaN : odd.divide(even, 10, RoundingMode.HALF_UP).toString()));
        }
    }
}
