import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Scanner;

public class AdventOfCode2024 {
    public static void main(String[] args) {
        var inputPath = "inputs/day07.txt";

        System.out.println(day07(inputPath));
    }

    public static String day07(String inputPath) {
        var input = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(inputPath));

            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }

            scanner.close();
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        Pattern formulaPattern =  Pattern.compile("^(\\d+):( \\d+)+$");
        for ( var line : input ) {
            var formulaMatcher = formulaPattern.matcher(line);
            if (formulaMatcher.find()) {
                var totalString = formulaMatcher.group(1);

                // TODO: Make this an integer list instead, parsing each term.
                var terms = new ArrayList<String>();
                for (var i = 2; i <= formulaMatcher.groupCount(); i++) {
                    var termString = formulaMatcher.group(i);
                    terms.add(termString);
                }

                System.out.println(String.format("%s: %s, has %d (%d) terms", totalString, String.join(" ", terms), terms.size(), formulaMatcher.groupCount() - 2));
            } else {
                // TODO: Add exception
                return String.format("Could not parse formula in line '%s' using regexp!", line);
            }
        }

        return "";
    }
}
