import java.util.*;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

/**
 * Rack class
 * <p>
 * A Rack of Scrabble tiles. Analyze frequency characters from input string using TreeMap<Character, Integer>
 * to get subset of a given string.
 */

public class Rack {

    private Map<Character, Integer> uniChar;

    /**
     * initialize rack object
     * Representative invariables: uniChar.size() >= 0
     */
    public Rack() {

        uniChar = new TreeMap<Character, Integer>();

    }

    /**
     * count frequency of characters in a string
     *
     * @param word input string
     */
    public void addChar(String word) {

        uniChar.clear(); // initialization for second implementation

        //* count characters *//
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (isLowerCase(letter) || isUpperCase(letter)) {    //eliminate any symbols
                if (uniChar.containsKey(letter)) {
                    int temp = uniChar.get(letter);
                    uniChar.put(letter, ++temp);
                } else {
                    uniChar.put(letter, 1);     //this character appears for the first time
                }
            }
        }
    }

    /**
     * get subset of a string
     *
     * @return all subsets of the indicated multiset
     */
    public ArrayList<String> getSubset() {

        //* initialization *//
        String unique = "";
        int i = 0;
        int[] multArray = new int[uniChar.size()];

        //* create a unique word and frequency distribution *//
        for (Map.Entry<Character, Integer> curr : uniChar.entrySet()) {
            unique += curr.getKey();
            multArray[i++] = curr.getValue();
        }

        /*debug
        for (int j = 0; j < unique.length(); j++) {
            System.out.println("DEBUG: " + unique.charAt(j) + " appears " + multArray[j] + " times in the rack");
        }
        System.out.println();
        */

        return allSubsets(unique, multArray, 0);
    }

    /**
     * Finds all subsets of the multiset starting at position k in unique and mult.
     * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
     * unique.charAt(i).
     * PRE: mult.length must be at least as big as unique.length()
     * 0 <= k <= unique.length()
     *
     * @param unique a string of unique letters
     * @param mult   the multiplicity of each letter from unique.
     * @param k      the smallest index of unique and mult to consider.
     * @return all subsets of the indicated multiset
     * @author Claire Bono
     */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();

        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }

        // get all subsets of the multiset without the first unique char
        ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

        // prepend all possible numbers of the first char (i.e., the one at position k)
        // to the front of each string in restCombos.  Suppose that char is 'a'...

        String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
        for (int n = 0; n <= mult[k]; n++) {
            for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
                // we found in the recursive call
                // create and add a new string with n 'a's in front of that subset
                allCombos.add(firstPart + restCombos.get(i));
            }
            firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
        }

        return allCombos;
    }


}
