import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * AnagramDictionary Class
 * <p>
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    Map<String, ArrayList<String>> anagDic;

    /**
     * Create an anagram dictionary from the list of words given in the file
     * indicated by fileName. Store words in HashMap<String, ArrayList> using canonical formed word as key.
     * PRE: The strings in the file are unique.
     *
     * @param fileName the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     *                               Representative invariables: anagDic.size() >= 0
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {

        String pathFileName = "testfiles\\" + fileName;     //assign relative path for IntelliJ
        //String pathFileName = fileName;                   //assign relative path for aludra
        Scanner in = new Scanner(new File(pathFileName));   //open a file

        anagDic = new HashMap<String, ArrayList<String>>();

        //* add all words from text file to HashMap *//
        while (in.hasNext()) {
            ArrayList<String> anagArray = new ArrayList<String>();
            String str = in.nextLine();

            //* convert a word to canonical form *//
            String strCano = sortChar(str, str.length());

            if (anagDic.containsKey(strCano)) {
                anagArray = anagDic.get(strCano);
            }

            anagArray.add(str);
            anagDic.put(strCano, anagArray);
        }
    }

    /**
     * Get all anagrams of the given string. This method is case-sensitive.
     * E.g. "CARE" and "race" would not be recognized as anagrams.
     *
     * @param s string to process
     * @return a list of the anagrams of s
     */
    public ArrayList<String> getAnagramsOf(String s) {

        //* sort characters of string s in canonical form *//
        String tmp = sortChar(s, s.length());

        //* find anagram in the dictionary *//
        if (anagDic.get(tmp) == null) {       // to avoid NullPointerException and side effect
            return new ArrayList<String>();
        }
        return new ArrayList<String>(anagDic.get(tmp));
    }

    /**
     * sort character in ascending order to create canonical form
     *
     * @param str string to process
     * @param len string of length
     * @return char[] canoForm :a canonical word
     */
    public String sortChar(String str, int len) {

        char[] canoArray = new char[len];

        for (int i = 0; i < len; i++) {
            canoArray[i] = str.charAt(i);
        }
        Arrays.sort(canoArray);

        return Arrays.toString(canoArray);
    }
}