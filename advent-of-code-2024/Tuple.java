import java.util.ArrayList;

public class Tuple<X, Y> { 
    public final X x; 
    public final Y y; 
    public Tuple(X x, Y y) { 
        this.x = x; 
        this.y = y; 
    }

    public static Tuple<ArrayList<Integer>, ArrayList<Integer>> parseLinesToTupleOfIntegerLists(ArrayList<String> strings) throws IllegalArgumentException, NumberFormatException {
        var xList = new ArrayList<Integer>();
        var yList = new ArrayList<Integer>();

        for (var line : strings) {
            var entries = line.split("   ");

            if (entries.length != 2) {
                throw new IllegalArgumentException(String.format("Encountered input line '%s' which does not have a left and right entry; %d entries found!", line, entries.length));
            }

            Integer left, right;
            try {
                left = Integer.valueOf(entries[0]);
                right = Integer.valueOf(entries[1]);
            } catch(NumberFormatException e) {
                throw new NumberFormatException(String.format("Error converting one or both of the entries on line '%s' to an Integer; " + e, line));
            }

            xList.add(left);
            yList.add(right);
        }

        return new Tuple<ArrayList<Integer>, ArrayList<Integer>>(xList, yList);
    }
}
