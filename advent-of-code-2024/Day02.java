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

        return String.format("There are %d safe reports.", safeReports(reports, 0));
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
                    System.out.println(String.format("Unsafe level found after index %d in report %s!", i, report.toString()));

                    // Try again, with either of levels removed.
                    var dampenedReport1 = (ArrayList)report.clone();
                    var dampenedReport2 = (ArrayList)report.clone();

                    dampenedReport1.remove(i);
                    dampenedReport2.remove(i+1);

                    System.out.println(String.format("Trying again with modified reports %s and %s.\n", dampenedReport1.toString(), dampenedReport2.toString()));

                    var safe = isReportSafe(dampenedReport1, unsafeLevelToleration - 1) || isReportSafe(dampenedReport2, unsafeLevelToleration - 1);

                    if (safe) {
                        System.out.println("Report was found to be safe after level removed!\n");
                    }

                    return safe;
                }
            }
        }

        // End of report reached, it is safe.
        return true;
    }
}
