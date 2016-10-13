package Skil_4;

/**
 * Created by Baldur on 11.10.2016.
 */
import edu.princeton.cs.algs4.*;

public class WordNet {

    private  SAP sap;
    private  Digraph digraph;
    private  ST<String, Bag<Integer>> nouns;
    private  ST<Integer, String> synsets;

    // constructor takes the name of the two input files
    public WordNet ( String synsets , String hypernyms ) throws IllegalArgumentException{
        int counter = 0;
        //Read synsets from file.
        In in = new In(synsets);

        //initialize
        this.nouns = new ST<String, Bag<Integer>>();
        this.synsets = new ST<Integer, String>();


        /**
         * Build the two STs, synsets and nouns
         */
        while(in.hasNextLine()){
            String[] splitline = in.readLine().split(",");
            int synsetsID = Integer.parseInt(splitline[0]);
            String[] nounArray = splitline[1].split(" ");

            this.synsets.put(synsetsID, splitline[1]);

            for(String s: nounArray){
                if(this.nouns.contains(s)){
                    this.nouns.get(s).add(synsetsID);
                }
                else{
                    Bag<Integer> newBag = new Bag<Integer>();
                    newBag.add(synsetsID);
                    this.nouns.put(s, newBag);
                }
            }


        }

        // Close the In stream used to read the synsets
        in.close();

        // Open a new In stream with the hypernym file
        in = new In(hypernyms);



        /**
         * Builds the hypernym digraph with the same number of Vertices as there are keys in the {@param}synsets ST
         */

        digraph = new Digraph(this.synsets.size());

        while(in.hasNextLine()){
            String[] nodes = in.readLine().split(",");
            for(String s: nodes){
                if(!(s.equals(nodes[0]))){
                    digraph.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(s));
                }
            }
        }
        // Close the In stream used to read hypernyms
        in.close();

    }

    // returns all WordNet nouns
    public Iterable <String > nouns (){

        return this.nouns.keys();
    }

    // is the word a WordNet noun ?
    public boolean isNoun ( String word ){

        return this.nouns.contains(word);
    }

     // distance between nounA and nounB ( defined below )
     public int distance ( String nounA , String nounB ){



     }

     // a synset ( second field of synsets .txt ) that is a shortest common ancestor
     // of nounA and nounB
     public String sap ( String nounA , String nounB ){
         return null;
     }

     // do unit testing of this class
     public static void main ( String [] args ){
         String synsetsFile = "C:\\Users\\Baldur\\Dropbox\\Bs.C-Hugb\\5.onn\\reir\\Reiknirit-2016\\lib\\Skil_4\\wordnet\\synsets\\synsets100-subgraph.txt";
         String hypernymsFile = "C:\\Users\\Baldur\\Dropbox\\Bs.C-Hugb\\5.onn\\reir\\Reiknirit-2016\\lib\\Skil_4\\wordnet\\hypernyms\\hypernyms100-subgraph.txt";

         WordNet newNet = new WordNet(synsetsFile, hypernymsFile);

         StdOut.println(newNet.digraph);


     }
}
