import java.io.*;
import java.util.Random;

public class CreateFile {

    public static String generateRandomWords(int numberOfWords)
    {
        StringBuilder randomStrings = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings.append(new String(word) + " ") ;
        }
        return randomStrings.toString();
    }

    public static void main(String[] args) throws IOException
    {

        int n = 50;
        while (n != 0)
        {
            String workingDir = System.getProperty("user.dir");
            String fileSeparator = System.getProperty("file.separator");
            String absoluteFilePath = workingDir + fileSeparator +"file_" + n + ".txt";
            File file = new File(absoluteFilePath);

            Writer writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(generateRandomWords(500 + n*3 ));
            writer.close();
            n--;
        }
    }
}
