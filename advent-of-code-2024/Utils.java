import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Utils {
    public static ArrayList<String> parseAsLines(String inputPath) throws FileNotFoundException {
        var input = new ArrayList<String>();

        Scanner scanner = new Scanner(new File(inputPath));

        while (scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        scanner.close();

        return input;
    }
}
