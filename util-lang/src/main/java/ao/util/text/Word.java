package ao.util.text;

import ao.util.math.rand.Rand;


/**
 * Date: Dec 22, 2008
 * Time: 9:59:28 AM
 */
public class Word
{
    //--------------------------------------------------------------------
    private static final String[] VOWELS_MID = {
            "a", "e", "i", "o", "u", "oo", "ea", "ie"};
    private static final String[] VOWELS_END = {
            "y", "ii"};

    private static final String[] CONSONANTS = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
            "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
            "sh", "th", "ch"};


    //--------------------------------------------------------------------
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
        {
            for (int j = 2; j <= 6; j++)
            {
                System.out.println(random(j));
            }
        }
    }


    //--------------------------------------------------------------------
    private Word() {}


    //--------------------------------------------------------------------
    /**
     * Use the Banana algorithm
     * @param numSyllables how many syllables are desired
     * @return randomly generated word (may not have any common meaning)
     */
    public static String random(int numSyllables)
    {
        if (numSyllables < 1) return "";

        StringBuffer word = new StringBuffer();

        boolean useVowel     = Rand.nextBoolean();
        int     lastSyllable = numSyllables - 1;
        for (int syllable = 0;
                 syllable <= lastSyllable;
                 syllable++)
        {
            if (useVowel)
            {
                if (syllable != lastSyllable)
                {
                    word.append( Rand.fromArray(VOWELS_MID) );
                }
                else
                {
                    word.append( Rand.fromArray(VOWELS_END) );
                }
            }
            else
            {
                word.append( Rand.fromArray(CONSONANTS) );

                if (syllable != 0 && syllable != lastSyllable
                        && Rand.nextBoolean(6))
                {
                    word.append( Rand.fromArray(CONSONANTS) );
                }
            }

            useVowel = !useVowel;
        }

        return word.toString();
    }
}
