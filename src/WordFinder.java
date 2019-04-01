import java.io.FileNotFoundException;
import java.util.*;

public class WordFinder {

    /**
     * WordFinder class
     * <p>
     * Include main method. Create anagram dictionary object, score table object and
     * rack object to run simulation.
     * Handle if the dictionary given is not found.
     *
     * @param args command line
     */

    public static void main(String[] args) {
        String fileName;
        if (args.length < 1) {
            fileName = "sowpods.txt";   //default use
        } else {
            fileName = args[0];
        }
        try {
            //* create an anagram dictionary, a score table and a rack *//
            //* only one time when these are called because of space consuming*//
            AnagramDictionary dict = new AnagramDictionary(fileName);
            ScoreTable table = new ScoreTable();
            Rack rack = new Rack();
            Scanner in = new Scanner(System.in);
            System.out.println("Type . to quit.");

            while (true) {

                //* read target string *//
                System.out.print("Rack? ");
                String str = in.nextLine();

                //* terminate simulation if a period is implemented from command line *//
                if (".".equals(str)) {
                    break;
                } else {
                    rack.addChar(str);

                    //* simulation start *//
                    simStart(rack.getSubset(), dict, table);
                }
            }
        } catch (FileNotFoundException exc) {
            System.out.println("ERROR: File not found: " + fileName);
        }
    }


    /**
     * Simulation start. Store the score of the anagram in TreeMap<String, Integer>
     * using the anagram as a key, which is sorted in ascending order of alphabetic order.
     * After store it, sort scores in descending order using static class.
     *
     * @param uniList subsets of the target which are created in the rack
     * @param dict    anagram dictionary object
     * @param table   score table object
     *                representative variables: uniList.size() > 0,
     */
    public static void simStart(ArrayList<String> uniList, AnagramDictionary dict, ScoreTable table) {
        Map<String, Integer> anaMap = new TreeMap<String, Integer>();

        //* pick canonical form up from the dictionary *//
        for (String curr : uniList) {
            ArrayList<String> anaList = dict.getAnagramsOf(curr);

            //* check whether there is a canonical form or not *//
            if (anaList.size() != 0) {

                //* get an anagram, calculate a score of it and store them into TreeMap *//
                for (String anagram : anaList) {
                    int sumScore = 0;   //initialization
                    for (int i = 0; i < anagram.length(); i++) {
                        sumScore += table.getScore(anagram.charAt(i));
                    }
                    anaMap.put(anagram, sumScore);
                }
            }
        }

        //* sort scores by descending order *//
        ArrayList<Map.Entry<String, Integer>> listEntries = new ArrayList<Map.Entry<String, Integer>>(anaMap.entrySet());
        Collections.sort(listEntries, new CompOccur());

        //* output simulation result *//
        int listSize = listEntries.size();
        System.out.println("We can make " + listSize + " words from " +
                "\"" + uniList.get(uniList.size() - 1) + "\"");

        if (listSize != 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            for (Map.Entry<String, Integer> curr : listEntries) {
                System.out.println(curr.getValue() + ": " + curr.getKey());
            }
        }
    }

    /**
     * static class to sort values in descending order.
     * inputs are Map entries
     */

    static class CompOccur implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }
}