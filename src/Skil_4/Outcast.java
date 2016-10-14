package Skil_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Baldur on 11.10.2016.
 */

public class Outcast {

    private WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast ( WordNet wordnet ){

        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns , return an outcast
    public String outcast ( String [] nouns ){

        String outcast = null;
        int dist = 0;
        for (int i = 0; i < nouns.length; i++){

            int result = 0;

            for (int j = 0; j < nouns.length; j++){
                result = result + wordNet.distance(nouns[i],nouns[j]);
            }
            if (result > dist){
                dist = result;
                outcast = nouns[i];
            }
        }

        return outcast;


    }

    public static void main ( String [] argss ) {

        String [] args = { "/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/synsets/synsets.txt",
                           "/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/hypernyms/hypernyms.txt",
                           "/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/outcasts/outcast5.txt", //table
                           "/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/outcasts/outcast8.txt", //bed
                           "/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/outcasts/outcast11.txt" //potato
                          };

        WordNet wordnet = new WordNet ( args [0] , args [1]) ;
        Outcast outcast = new Outcast ( wordnet );

        for (int t = 2; t < args.length ; t++) {
            In in = new In(args[t]);
            String [] nouns = in.readAllStrings();
            StdOut.println (args[t] + ": " + outcast.outcast(nouns));
            }
        }
}