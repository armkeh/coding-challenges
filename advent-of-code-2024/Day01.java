import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public final class Day01 implements Day {

    public String part1(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseFileToLines(inputPath);
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

    public String part2(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseFileToLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var lists = Tuple.parseLinesToIntegerLists(input);
        var leftList = lists.x;
        var rightList = lists.y;

        var rightOccurrenceMap = Utils.occurrenceMap(rightList);

        int sumOfSimilarityScores = 0;
        for (var e : leftList) {
            var occurrences = rightOccurrenceMap.getOrDefault(e, 0);
            sumOfSimilarityScores += e * occurrences;
        }

        return String.format("The sum of the similarity scores is %d.", sumOfSimilarityScores);
    }
}
