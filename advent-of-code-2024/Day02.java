import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Day02 {
    // Private constructor to prevent instantiation of this stateless class
    private Day02() {}

    public static String part1(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseFileToLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var reports = Utils.parseLinesToIntegerLists(input);

        return String.format("There are %d safe reports.", safeReports(reports, 0));
    }

    public static String part2(String inputPath) {
        ArrayList<String> input;
        try {
            input = Utils.parseFileToLines(inputPath);
        } catch(FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var reports = Utils.parseLinesToIntegerLists(input);

        return String.format("There are %d safe reports.", safeReports(reports, 1));
    }

    private static int safeReports(ArrayList<ArrayList<Integer>> reports, int unsafeLevelToleration) {
        var safeReports = 0;

        for (var report : reports) {
            if (isReportSafe(report, unsafeLevelToleration)) {
                safeReports++;
            }
        }

        return safeReports;
    }

    private static boolean isReportSafe(ArrayList<Integer> report, int unsafeLevelToleration) {
        if (report.size() < 2) {
            throw new IllegalArgumentException("Error; not enough levels in a report!");
        }

        var increasing = report.get(0) < report.get(1);
        var unsafeLevels = 0;
        for (var i = 0; i < report.size() - 1; i++) {
            int diff;
            if (increasing) {
                diff = report.get(i+1) - report.get(i);
            } else {
                diff = report.get(i) - report.get(i+1);
            }

            if (diff < 1 || diff > 3) {
                unsafeLevels++;
                if (unsafeLevels > unsafeLevelToleration) {
                    return false;
                } else {
                    // Try again, with either of levels removed.
                    // Also try removing the first element (which may be misleading us on whether the sequence is increasing/decreasing).
                    var dampenedReport1 = (ArrayList<Integer>)report.clone();
                    var dampenedReport2 = (ArrayList<Integer>)report.clone();
                    var dampenedReport3 = (ArrayList<Integer>)report.clone();

                    dampenedReport1.remove(i);
                    dampenedReport2.remove(i + 1);
                    dampenedReport3.remove(0);

                    return isReportSafe(dampenedReport1, unsafeLevelToleration - 1) ||
                        isReportSafe(dampenedReport2, unsafeLevelToleration - 1) ||
                        isReportSafe(dampenedReport3, unsafeLevelToleration - 1);
                }
            }
        }

        // End of report reached, it is safe.
        return true;
    }
}
