import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;

public final class Day05 implements Day {

    static private Pattern orderingRule = Pattern.compile("(\\d+)\\|(\\d+)");
    static private Pattern pageList = Pattern.compile("\\d+(,\\d+)+");

    public String part1(String inputPath) {
        String input;
        try {
            input = Utils.parseFileToString(inputPath);
        } catch (FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var orderingRuleMatcher = orderingRule.matcher(input);

        var orderingRules = new ArrayList<Tuple<String,String>>();
        while (orderingRuleMatcher.find()) {
            var beforePage = orderingRuleMatcher.group(1);
            var afterPage  = orderingRuleMatcher.group(2);

            orderingRules.add(new Tuple<String,String>(beforePage, afterPage));
        }

        var pageListMatcher = pageList.matcher(input);

        var middlePageNumbersSum = 0;
        while (pageListMatcher.find()) {
            var pageList = pageListMatcher.group();
            var pages = Arrays.asList(pageList.split(","));

            var obeysRules = true;
            for (var rule : orderingRules) {
                var beforePage  = rule.x;
                var beforeIndex = pages.indexOf(beforePage);
                var afterPage   = rule.y;
                var afterIndex  = pages.indexOf(afterPage);

                if (beforeIndex != -1 && afterIndex != -1 && beforeIndex > afterIndex) {
                    obeysRules = false;
                    break;
                }
            }

            if (obeysRules) {
                var middlePage = Integer.valueOf(pages.get(pages.size() / 2));

                middlePageNumbersSum += middlePage;
            }
        }

        return String.format("The sum of middle page numbers from correctly-ordered updates is %d.", middlePageNumbersSum);
    }

    public String part2(String inputPath) {
        String input;
        try {
            input = Utils.parseFileToString(inputPath);
        } catch (FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var orderingRuleMatcher = orderingRule.matcher(input);

        var orderingRules = new ArrayList<Tuple<String,String>>();
        while (orderingRuleMatcher.find()) {
            var beforePage = orderingRuleMatcher.group(1);
            var afterPage  = orderingRuleMatcher.group(2);

            orderingRules.add(new Tuple<String,String>(beforePage, afterPage));
        }

        var pageListMatcher = pageList.matcher(input);

        var middlePageNumbersSum = 0;
        while (pageListMatcher.find()) {
            var pageList = pageListMatcher.group();
            var pages = Arrays.asList(pageList.split(","));

            var obeysRules = true;
            var scanNeeded = true;
            while (swapPerformed) {
                // By default, don't rescan.
                scanNeeded = false;

                for (var rule : orderingRules) {
                    var beforePage  = rule.x;
                    var beforeIndex = pages.indexOf(beforePage);
                    var afterPage   = rule.y;
                    var afterIndex  = pages.indexOf(afterPage);

                    if (beforeIndex != -1 && afterIndex != -1 && beforeIndex > afterIndex) {
                        obeysRules = false;

                        // Correct the broken rule by swapping the pages.
                        pages.set(beforeIndex, afterPage);
                        pages.set(afterIndex, beforePage);

                        // Re-scan after the swap(s).
                        scanNeeded = true;
                    }
                }
            }

            if (!obeysRules) {
                var middlePage = Integer.valueOf(pages.get(pages.size() / 2));

                middlePageNumbersSum += middlePage;
            }
        }

        return String.format("The sum of middle page numbers from corrected updates is %d.", middlePageNumbersSum);
    }
}
