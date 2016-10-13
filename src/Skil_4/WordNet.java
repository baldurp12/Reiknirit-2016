package Skil_4;

/**
 * Created by Baldur on 11.10.2016.
 */
import edu.princeton.cs.algs4.*;

public class WordNet {

    private  SAP s;
    private  Digraph digraph;
    private  ST<String, Bag<Integer>> nouns;
    private  ST<Integer, String> synsets;

    // constructor takes the name of the two input files
    public WordNet ( String synsets , String hypernyms ) throws IllegalArgumentException{
        int counter = 0;
        //Read synets from file.
        In in = new In(synsets);

        //initialize
        this.nouns = new ST<String, Bag<Integer>>();
        this.synsets = new ST<Integer, String>();

        while(in.hasNextLine()){
            String[] splitline = in.readLine().split(",");
            for (String s : splitline[1].split(" ")){
                Bag<Integer>
            }

        }


        this.digraph = new Digraph(counter);


        s = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable <String > nouns (){
        return null;
    }

    // is the word a WordNet noun ?
    public boolean isNoun ( String word ){
        return false;
    }

     // distance between nounA and nounB ( defined below )
     public int distance ( String nounA , String nounB ){

         return -1;
     }

     // a synset ( second field of synsets .txt ) that is a shortest common ancestor
     // of nounA and nounB
     public String sap ( String nounA , String nounB ){
         return null;
     }

     // do unit testing of this class
     public static void main ( String [] args ){


     }
}
