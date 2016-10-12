package Skil_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Baldur on 11.10.2016.
 */
public class Outcast {

    // constructor takes a WordNet object
    public Outcast ( WordNet wordnet ){

    }

    // given an array of WordNet nouns , return an outcast
    public String outcast ( String [] nouns ){
        return null;
    }

    public static void main ( String [] args ) {
        WordNet wordnet = new WordNet ( args [0] , args [1]) ;
        Outcast outcast = new Outcast ( wordnet );
        for (int t = 2; t < args . length ; t ++) {
            String [] nouns = In.readStrings ( args [t]);
            StdOut.println (args[t] + ": " + outcast.outcast(nouns));
            }
        }
}
