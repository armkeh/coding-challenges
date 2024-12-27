import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class Utils {
    // Private constructor to prevent instantiation of this stateless class
    private Utils() {
    }

    public static String parseFileToString(String inputPath) throws FileNotFoundException {
        var input = "";

        Scanner scanner = new Scanner(new File(inputPath));

        while (scanner.hasNextLine()) {
            input += scanner.nextLine();
        }

        scanner.close();

        return input;
    }

    public static ArrayList<String> parseFileToLines(String inputPath) throws FileNotFoundException {
        var input = new ArrayList<String>();

        Scanner scanner = new Scanner(new File(inputPath));

        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        scanner.close();

        return input;
    }

    public static char[][] parseFileToCharMatrix(String inputPath) throws FileNotFoundException {
        var input = new ArrayList<char[]>();

        Scanner scanner = new Scanner(new File(inputPath));

        // Parse first line to get expected line lengths.
        int lineLength = 0;
        if (scanner.hasNextLine()) {
            var firstLine = scanner.nextLine();
            lineLength = firstLine.length();
            input.add(firstLine.toCharArray());
        }

        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            
            if (line.length() != lineLength) {
                throw new IllegalArgumentException(String.format("Error; line '%s' does not have the expected number of characters (%d)!", line, lineLength));
            }

            input.add(line.toCharArray());
        }

        scanner.close();

        var result = new char[lineLength][input.size()];
        input.toArray(result);
        return result;
    }

    public static <T> HashMap<T,Integer> occurrenceMap(ArrayList<T> list) {
        var occMap = new HashMap<T,Integer>();

        for (var e : list) {
            occMap.put(e, occMap.getOrDefault(e, 0) + 1);
        }

        return occMap;
    }

    public static ArrayList<ArrayList<Integer>> parseLinesToIntegerLists(ArrayList<String> lines) throws IllegalArgumentException, NumberFormatException {
        var listOfLists = new ArrayList<ArrayList<Integer>>();
        
        for (var line : lines) {
            var entries = line.split(" ");

            var list = new ArrayList<Integer>();
            for (var entry : entries) {
                try {
                    list.add(Integer.valueOf(entry));
                } catch(NumberFormatException e) {
                    throw new NumberFormatException(String.format("Error converting entry '%s' on line '%s' to an Integer; " + e, entry, line));
                }
            }
            
            listOfLists.add(list);
        }

        return listOfLists;
    }
}
