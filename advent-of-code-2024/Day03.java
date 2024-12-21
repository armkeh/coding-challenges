import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public final class Day03 {
    // Private constructor to prevent instantiation of this stateless class
    private Day03() {
    }

    static private Pattern mulInstruction = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    public static String part1(String inputPath) {
        String input;
        try {
            input = Utils.parseAsString(inputPath);
        } catch (FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var matcher = mulInstruction.matcher(input);

        var sumOfProducts = 0;
        while (matcher.find()) {
            var termX = Integer.valueOf(matcher.group(1));
            var termY = Integer.valueOf(matcher.group(2));

            sumOfProducts += termX * termY;
        }

        return String.format("The sum of products from valid operations is %d.", sumOfProducts);
    }

    static private Pattern conditionalOrMulInstruction = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)");

    public static String part2(String inputPath) {
        String input;
        try {
            input = Utils.parseAsString(inputPath);
        } catch (FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        var matcher = conditionalOrMulInstruction.matcher(input);

        var sumOfProducts = 0;
        var enabled = true;
        while (matcher.find()) {
            var match = matcher.group(0);
            switch (match) {
            case "do()":
                enabled = true;
                break;
            case "don't()":
                enabled = false;
                break;
            default:
                if (enabled) {
                    var termX = Integer.valueOf(matcher.group(1));
                    var termY = Integer.valueOf(matcher.group(2));

                    sumOfProducts += termX * termY;
                }
            }
        }

        return String.format("The sum of products from valid operations is %d.", sumOfProducts);
    }
}
