import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Grep {

    private static void printUsageAndExit(String... str) {
        System.out.println("Usage: " + Grep.class.getSimpleName()
                + " [OPTION]... PATTERN FILE...");
        System.out.println("Search for PATTERN in each FILE. "
                + "If FILE is a directory then whole file tree of the directory"
                + " will be processed.");
        System.out.println("Example: grep -m 100 'hello world' menu.h main.c");
        System.out.println("Options:");
        System.out.println("    -m NUM: stop analysis after NUM matches");
        Arrays.asList(str).forEach(System.err::println);
        System.exit(1);
    }

    private static Stream<Path> getPathStream(Path path) {
        try {
            return Files.walk(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static Stream<String> path2Lines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        Vector<String> instructions = new Vector<>();
        Vector<String> fileName = new Vector<>();
        String[] word = {null};
        Arrays.stream(args).forEach(arg -> {
            if (arg.startsWith("-")) {
                instructions.add(arg);
            } else if (arg.contains(".txt")) {
                fileName.add(arg);
            } else {
                word[0] = arg;
            }
        });
        String[] filenames = fileName.toArray(new String[fileName.size()]);
        boolean has_c = instructions.contains("-c");
        boolean has_l = instructions.contains("-l");
        boolean has_w = instructions.contains("-w");
        long maxCount = Long.MAX_VALUE;
        if (args.length < 2) {
            printUsageAndExit();
        }
        int i = 0;
        //parse PATTERN
        Pattern pattern = Pattern.compile(has_w ? "\\b" + word[0] + "\\b": word[0]);
        if (i == args.length) {
            printUsageAndExit("There are no files for input");
        }

        try {
            List<Path> files = Arrays.stream(filenames)
                    .map(Paths::get)
                    // flatMap will ensure each I/O-based stream will be closed
                    .flatMap(Grep::getPathStream)
                    .filter(Files::isRegularFile)
                    .collect(toList());

            var count = files.parallelStream()
                    // flatMap will ensure each I/O-based stream will be closed
                    .flatMap(Grep::path2Lines)
                    .filter(pattern.asPredicate())
                    .limit(maxCount).count();
//            if(has_c)
//                System.out.println(result.count());
//            if(has_l)
//                System.out.println();
        } catch (UncheckedIOException ioe) {
            printUsageAndExit(ioe.toString());
        }
    }
}