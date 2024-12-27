import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Day04 implements Day {

    public String part1(String inputPath) {
        char[][] input;
        try {
            input = Utils.parseFileToCharMatrix(inputPath);
        } catch (FileNotFoundException e) {
            return String.format("Input file %s not found; " + e, inputPath);
        }

        // Note: Parse function ensures that all the input arrays have the same length.

        int xmasCount = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                // Only begin checks if we are on an 'X'.
                if (input[i][j] != 'X') {
                    continue;
                }

                // Check we're enough within the boundaries
                var topSafe = i >= 3;
                var rightSafe = j < input[i].length - 3;
                var bottomSafe = i < input.length - 3;
                var leftSafe = j >= 3;

                // Horizontal forward
                if (rightSafe && input[i][j+1] == 'M' && input[i][j+2] == 'A' && input[i][j+3] == 'S') {
                    xmasCount++;
                }

                // Horizontal backward
                if (leftSafe && input[i][j-1] == 'M' && input[i][j-2] == 'A' && input[i][j-3] == 'S') {
                    xmasCount++;
                }

                // Vertical downward
                if (bottomSafe && input[i+1][j] == 'M' && input[i+2][j] == 'A' && input[i+3][j] == 'S') {
                    xmasCount++;
                }

                // Vertical upward
                if (topSafe && input[i-1][j] == 'M' && input[i-2][j] == 'A' && input[i-3][j] == 'S') {
                    xmasCount++;
                }

                // Down-Right diagonal
                if (bottomSafe && rightSafe && input[i+1][j+1] == 'M' && input[i+2][j+2] == 'A' && input[i+3][j+3] == 'S') {
                    xmasCount++;
                }

                // Up-Right diagonal
                if (topSafe && rightSafe && input[i-1][j+1] == 'M' && input[i-2][j+2] == 'A' && input[i-3][j+3] == 'S') {
                    xmasCount++;
                }

                // Down-Left diagonal
                if (bottomSafe && leftSafe && input[i+1][j-1] == 'M' && input[i+2][j-2] == 'A' && input[i+3][j-3] == 'S') {
                    xmasCount++;
                }

                // Up-Left diagonal
                if (topSafe && leftSafe && input[i-1][j-1] == 'M' && input[i-2][j-2] == 'A' && input[i-3][j-3] == 'S') {
                    xmasCount++;
                }
            }
        }

        return String.format("Found %d instances of XMAS in the wordsearch.", xmasCount);
    }

    public String part2(String inputPath) {
        return "Day or part not yet implemented!";
    }
}
