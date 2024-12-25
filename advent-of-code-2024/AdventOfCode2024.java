import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class AdventOfCode2024 {
    private static void printInputAdvice() {
        System.out.println("Invoke with day and part as numerical arguments, and optionally a path to an input file. E.g., to run day 5 part 1,:");
        System.out.println("  > java AdventOfCode2024 5 1 day05-sample.txt");
    }

    public static void main(String[] args) {
        var day = 0;
        var part = 0;

        var error = "";
        if (2 <= args.length && args.length <= 3) {
            try {
                day = Integer.parseInt(args[0]);
                part = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                printInputAdvice();
                System.out.println("Error: Day and part arguments must be integers!");
                System.exit(1);
            }
        } else {
            printInputAdvice();
            System.out.println("Error: Not enough or too many arguments provided!");
            System.exit(1);
        }

        // By default, assume input is in the `inputs` directory, named `dayXX.txt`.
        var inputPath = String.format("inputs/day%02d.txt", day);
        if (args.length == 3) {
            inputPath = args[2];
        }

        if (1 <= day && day <= 25 && 1 <= part && part <= 2) {
            var result = String.format("Day %d, part %d not yet implemented.", day, part);
            if (part == 1) {
                switch (day) {
                case 1: result = Day01.part1(inputPath);
                case 2: result = Day02.part1(inputPath);
                case 3: result = Day03.part1(inputPath);
                }
            } else if (part == 2) {
                switch (day) {
                case 1: result = Day01.part2(inputPath);
                case 2: result = Day02.part2(inputPath);
                case 3: result = Day03.part2(inputPath);
                }
            }

            System.out.println(result);
        } else {
            printInputAdvice();
            System.out.println("Error: Day and/or part argument out of bound!");
            System.exit(1);
        }
    }
}
