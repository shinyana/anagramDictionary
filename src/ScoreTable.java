/**
 * ScoreTable class
 * <p>
 * Immutable class for storing Scrabble scores.
 */
public class ScoreTable {
    // Scrabble scores
    private int[] score = {1, 3, 3, 2, 1, 4, 2,     // a  b  c  d  e  f  g
                           4, 1, 8, 5, 1, 3, 1,     // h  i  j  k  l  m  n
                           1, 3, 10, 1, 1, 1, 1,    // o  p  q  r  s  t  u
                           4, 4, 8, 4, 10};         // v  w  x  y  z

    private final char UPPER_BASE = 'A';
    private final char LOWER_BASE = 'a';

    /**
     * initialize ScoreTable object
     */
    public ScoreTable() {

    }

    /**
     * find a score corresponding to a character
     *
     * @param alpha character in anagram
     * @return score of each character
     * Representative invariables: score.size() = 26
     */

    public int getScore(char alpha) {

        if (Character.isUpperCase(alpha)) {

            return score[alpha - UPPER_BASE];

        } else {

            return score[alpha - LOWER_BASE];
        }
    }
}