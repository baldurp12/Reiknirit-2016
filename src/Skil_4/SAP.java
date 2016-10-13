package Skil_4;

import edu.princeton.cs.algs4.*;
import org.omg.CORBA.OBJ_ADAPTER;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SAP {

    private Digraph digraph;

    // constructor takes a digraph ( not necessarily a DAG )
    public SAP(Digraph G){
        this.digraph = new Digraph(G);
        StdOut.println(digraph);
        DirectedCycle cycle = new DirectedCycle(digraph);
        if(cycle.hasCycle()){
            throw new IllegalArgumentException("Graph is not acyclic");
        }
        else{
            StdOut.println("is acyclic");
        }

        for( int i = 0; i < digraph.V(); i++){
            int size = 0;
            for (int j: digraph.adj(i)){
                size++;
            }
            if(size>1){
                throw new IllegalArgumentException("Graph is not rooted");
            }
        }
    }

    public int getlength(Object v, Object w){
        BreadthFirstDirectedPaths graphV;
        BreadthFirstDirectedPaths graphW;

        if (v instanceof Integer){
             graphV = new BreadthFirstDirectedPaths(digraph,(Integer)v);
             graphW = new BreadthFirstDirectedPaths(digraph,(Integer)w);
        }
        else{

             graphV = new BreadthFirstDirectedPaths(digraph, (Iterable<Integer>)v);
             graphW = new BreadthFirstDirectedPaths(digraph, (Iterable<Integer>)w);
        }

        int min = Integer.MAX_VALUE;
        boolean isPath = false;

        for(int i = 0; i < digraph.V(); i++) {

            if (graphV.hasPathTo(i) && graphW.hasPathTo(i)) {
                isPath = true;
                int dist = graphV.distTo(i) + graphW.distTo(i);
                if (dist < min){
                    min = dist;
                }
            }
        }

        if (isPath){
            return min;
        }
        return -1;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws IndexOutOfBoundsException{
        if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()){
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return getlength(v,w);
    }

    public int getAncestor(Object v, Object w){
        BreadthFirstDirectedPaths graphV;
        BreadthFirstDirectedPaths graphW;

        if (v instanceof Integer){
            graphV = new BreadthFirstDirectedPaths(digraph,(Integer)v);
            graphW = new BreadthFirstDirectedPaths(digraph,(Integer)w);
        }
        else{

            graphV = new BreadthFirstDirectedPaths(digraph, (Iterable<Integer>)v);
            graphW = new BreadthFirstDirectedPaths(digraph, (Iterable<Integer>)w);
        }
        int ancestor = 0;
        int min = Integer.MAX_VALUE;
        boolean isPath = false;

        for(int i = 0; i < digraph.V(); i++){

            if (graphV.hasPathTo(i) && graphW.hasPathTo(i)){
                isPath = true;
                int dist = graphV.distTo(i) + graphW.distTo(i);

                if (dist < min){
                    ancestor = i;
                    min = dist;
                }
            }
        }
        if(isPath){
            return ancestor;
        }

        return -1;
    }

    // a shortest common common ancestor of v and w; -1 if no such path
    public int ancestor(int v, int w) throws IndexOutOfBoundsException{
        if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()){
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return getAncestor(v, w);
    }

    // length of shortest ancestral path of vertex subsets A and B; -1 if no such path
    public int length(Iterable<Integer> A, Iterable<Integer> B) throws IndexOutOfBoundsException{
        for (int i : A){
            if (i < 0 || i >= digraph.V()){
                throw new IndexOutOfBoundsException();
            }
        }
        for (int i : B){
            if (i < 0 || i >= digraph.V()){
                throw new IndexOutOfBoundsException();
            }
        }
       return getlength(A,B);
    }

    // a shortest common ancestor of vertex subsets A and B; -1 if no such path
    public int ancestor(Iterable<Integer> A, Iterable<Integer> B) throws IndexOutOfBoundsException{
        for (int i : A){
            if (i < 0 || i >= digraph.V()){
                throw new IndexOutOfBoundsException();
            }
        }
        for (int i : B){
            if (i < 0 || i >= digraph.V()){
                throw new IndexOutOfBoundsException();
            }
        }
        return getAncestor(A, B);
    }

    // do unit testing of this class
    public static void main(String[] args){
        In in = new In("/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/digraphs/digraph1.txt");
        Digraph digraph = new Digraph(in);
        try {
            SAP s = new SAP(digraph);
            System.out.println("ancestor = " + s.ancestor(6,2)); //0
            System.out.println("ancestor = " + s.ancestor(8,11)); //5
            System.out.println("length = " + s.length(6,2)); //4
            System.out.println("length = " + s.length(8,11)); //3
            Integer v[] = {3,8,6,1};
            Integer w[] = {10,2};
            List<Integer> vlist = Arrays.asList(v);
            List<Integer> wlist = Arrays.asList(w);

            System.out.println("length = " + s.length(vlist,wlist)); //2
            System.out.println("ancestor = " + s.ancestor(vlist,wlist)); //0

        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

    }

}