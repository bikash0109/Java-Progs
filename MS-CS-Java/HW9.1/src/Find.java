/*
 * Program Name: Find.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * A progarm to  mimic find function of UNIX.
 *
 * */

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Find {
    public String nameToSearch;

    private void RecursivePrint(File[] listFiles, int level, Vector<String> instructions) {
        boolean hasPrintDate = instructions.contains("--printDate");
        boolean hasPrintLength = instructions.contains("--printLength");
        boolean hasPrintFile = instructions.contains("--printFile");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        for (File f : listFiles) {
            if(instructions.size() > 0){
                System.out.format("%30s %20s %10s",
                        (hasPrintFile ? f.getName() : ""),
                        (hasPrintDate ? "\t" + sdf.format(f.lastModified()) : ""),
                        (hasPrintLength ? f.length() : "") + "\n");
            }else {
                try{
                    System.out.format(f.getCanonicalPath() + "\n");
                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }

            if (f.listFiles() != null) {
                RecursivePrint(f.listFiles(), level + 1, instructions);
            }
        }
    }

    public void getAllFiles(Vector<String> instructions) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        if(!path.contains(nameToSearch)){
            path += (nameToSearch.length() > 0 ? "/" + nameToSearch : "");
        }

        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {
            RecursivePrint(directory.listFiles(), 0, instructions);
        }
    }

    public static void main(String[] args) {
        Vector<String> instructions = new Vector<>();
        String nameToSearch = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("--")) {
                instructions.add(args[i]);
            } else {
                nameToSearch = args[i];
            }
        }

        Find find = new Find();
        find.nameToSearch = nameToSearch;
        find.getAllFiles(instructions);
    }
}
