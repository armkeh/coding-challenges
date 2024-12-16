import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class AdventOfCode2024 {
    public static void main(String[] args) {
        var inputPath = "inputs/day01.txt";

        System.out.println(day01PartTwo(inputPath));
    }

    public static String day01PartOne(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseAsLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var lists = Tuple.parseLinesToIntegerLists(input);
        var leftList = lists.x;
        var rightList = lists.y;

        Collections.sort(leftList);
        Collections.sort(rightList);

        int sumOfDifferences = 0;
        for (int i = 0; i < leftList.size(); i++) {
            sumOfDifferences += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return String.format("The sum of differences in the entries is %d.", sumOfDifferences);
    }

    public static String day01PartTwo(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseAsLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var lists = Tuple.parseLinesToIntegerLists(input);
        var leftList = lists.x;
        var rightList = lists.y;

        var rightOccurrenceMap = Utils.occurrenceMap(rightList);

        int sumOfSimilarityScores = 0;
        for ( var e : leftList ) {
            var occurrences = rightOccurrenceMap.getOrDefault(e, 0);
            sumOfSimilarityScores += e * occurrences;
        }

        return String.format("The sum of the similarity scores is %d.", sumOfSimilarityScores);
    }

    public static String day07PartOne(String inputPath) {
        var input = new ArrayList<String>();
        try {
            input = Utils.parseAsLines(inputPath);
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
