import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.Scanner;

public class AdventOfCode2024 {
    public static void main(String[] args) {
        var inputPath = "inputs/day01.txt";

        System.out.println(day01PartOne(inputPath));
    }

    public static String day01PartOne(String inputPath) {
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

        var leftList = new ArrayList<Integer>();
        var rightList = new ArrayList<Integer>();
        for ( var line : input ) {
            var entries = line.split("   ");

            if (entries.length != 2) {
                return String.format("Encountered input line '%s' which does not have a left and right entry; %d entries found!", line, entries.length);
            }

            Integer left, right;
            try {
                left = Integer.valueOf(entries[0]);
                right = Integer.valueOf(entries[1]);
            } catch(NumberFormatException e) {
                return String.format("Error converting one or both of the entries on line '%s' to an Integer; " + e, line);
            }

            leftList.add(left);
            rightList.add(right);
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int sumOfDifferences = 0;
        for (int i = 0; i < leftList.size(); i++) {
            sumOfDifferences += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return String.format("The sum of differences in the entries is %d.", sumOfDifferences);
    }

    public static String day07PartOne(String inputPath) {
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
