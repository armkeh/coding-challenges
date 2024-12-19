import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Day02 {
    // Private constructor to prevent instantiation of this stateless class
    private Day02() {}

    public static String part1(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseAsLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var reports = Utils.parseLinesToIntegerLists(input);

        var safeReports = 0;
        for (var report : reports) {
            if (report.size() < 2) {
                return "Error; not enough levels in a report!";
            }

            var incrementing = report.get(0) < report.get(1);

            var safetyIncrement = 1;
            for (var i = 0; i < report.size() - 1; i++) {
                int diff;
                if (incrementing) {
                    diff = report.get(i+1) - report.get(i);
                } else {
                    diff = report.get(i) - report.get(i+1);
                }

                if (diff < 1 || diff > 3) {
                    // Violation found, stop checking this report and don't increment.
                    safetyIncrement = 0;
                    break;
                }
            }

            safeReports += safetyIncrement;
        }

        return String.format("There are %d safe reports.", safeReports);
    }

    public static String part2(String inputPath) {
        return "";
    }
}
