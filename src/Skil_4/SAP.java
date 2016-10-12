package Skil_4;

import edu.princeton.cs.algs4.*;


public class SAP {

    private static Digraph digraph;

    // constructor takes a digraph ( not necessarily a DAG )
    public SAP(Digraph G){
        this.digraph = new Digraph(G);
        StdOut.println("haehae");
        DirectedCycle cycle = new DirectedCycle(digraph);
        if(cycle.hasCycle()){
            throw new IllegalArgumentException("Graph is not acyclic");
        }
        else{
            StdOut.println("is acyclic");
        }

    }


    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws IndexOutOfBoundsException{
        return -1;
    }

    // a shortest common common ancestor of v and w; -1 if no such path
    public int ancestor(int v, int w) throws IndexOutOfBoundsException{
        return -1;
    }

    // length of shortest ancestral path of vertex subsets A and B; -1 if no such path
    public int length(Iterable<Integer> A, Iterable<Integer> B) throws IndexOutOfBoundsException{
        return -1;
    }

    // a shortest common ancestor of vertex subsets A and B; -1 if no such path
    public int ancestor(Iterable<Integer> A, Iterable<Integer> B) throws IndexOutOfBoundsException{
        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args){
        In in = new In("/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/digraphs/digraph1.txt");
        Digraph digraph = new Digraph(in);
        SAP testSap = new SAP(digraph);

        Bag<Integer> hypernyms = new Bag<Integer>();


    }

}