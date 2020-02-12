import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;

class GFG {
    public static void countFrequencies(ArrayList<String> list)
    {

        // hash set is created and elements of
        // arraylist are insertd into it
        Set<String> st = new HashSet<>(list);
        for (String s : st)
            System.out.println(s + ": " + Collections.frequency(list, s));
    }

    public static void main(String[] args)
    {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Geeks");
        list.add("for");
        list.add("Geeks");
        countFrequencies(list);
    }
}