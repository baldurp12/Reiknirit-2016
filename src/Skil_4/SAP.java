package Skil_4;

import edu.princeton.cs.algs4.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SAP {

    private Digraph digraph;

    // constructor takes a digraph ( not necessarily a DAG )
    public SAP(Digraph G){
        this.digraph = new Digraph(G);
        DirectedCycle cycle = new DirectedCycle(digraph);
        if(cycle.hasCycle()){
            throw new IllegalArgumentException("Graph is not acyclic");
        }
        int counted = 0;
        for( int i = 0; i < digraph.V(); i++){
            if(digraph.outdegree(i) ==0){
                counted++;
            }
            if(counted > 1){
                throw new IllegalArgumentException("Graph is not rooted");
            }
        }
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) throws IndexOutOfBoundsException{
        if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()){
            throw new IndexOutOfBoundsException();
        }

        BreadthFirstDirectedPaths graphV  = new BreadthFirstDirectedPaths(digraph,v);
        BreadthFirstDirectedPaths graphW = new BreadthFirstDirectedPaths(digraph,w);

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

    // a shortest common common ancestor of v and w; -1 if no such path
    public int ancestor(int v, int w) throws IndexOutOfBoundsException{
        if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()){
            throw new IndexOutOfBoundsException();
        }
        BreadthFirstDirectedPaths graphV = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths graphW = new BreadthFirstDirectedPaths(digraph, w);

        int ancestor = 0;
        int min = Integer.MAX_VALUE;
        boolean isPath = false;

        for(int i = 0; i < digraph.V(); i++){
            if (graphV.hasPathTo(i) && graphW.hasPathTo(i)){
                isPath = true;
                int dist = graphV.distTo(i) + graphW.distTo(i);

                if (dist == min){
                    if (ancestor < i){
                        ancestor = i;
                    }
                }
                else if (dist < min){
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
        BreadthFirstDirectedPaths graphV  = new BreadthFirstDirectedPaths(digraph,A);
        BreadthFirstDirectedPaths graphW = new BreadthFirstDirectedPaths(digraph,B);

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

    // a shortest common ancestor of vertex subsets A and B; -1 if no such path
    public int ancestor(Iterable<Integer> A, Iterable<Integer> B) throws IndexOutOfBoundsException{
        BreadthFirstDirectedPaths graphV = new BreadthFirstDirectedPaths(digraph, A);
        BreadthFirstDirectedPaths graphW = new BreadthFirstDirectedPaths(digraph, B);

        int min = Integer.MAX_VALUE;
        boolean isPath = false;
        int ancestor = 0;

        for(int i = 0; i < digraph.V(); i++) {

            if (graphV.hasPathTo(i) && graphW.hasPathTo(i)) {
                isPath = true;
                int dist = graphV.distTo(i) + graphW.distTo(i);

                if (dist == min){
                    if (ancestor < i){
                        ancestor = i;
                    }
                }
                else if (dist < min){
                    min = dist;
                    ancestor = i;
                }
            }
        }

        if (isPath){
            return ancestor;
        }

        return -1;
    }

    // do unit testing of this class
    public static void main(String[] args){
        In in = new In("/Users/thorgerdureiriksdottir/Desktop/Reikni/Reiknirit-2016/lib/Skil_4/wordnet/digraphs/digraph2.txt");
        Digraph digraph = new Digraph(in);
        try {
            SAP s = new SAP(digraph);
            for (int i = 0; i < digraph.V(); i++){
                System.out.println(i + "outdegree " + s.digraph.outdegree(i));
            }
/*
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
            */

            System.out.println("ancestor = " + s.ancestor(1,4)); //4
            System.out.println("length = " + s.length(1,4)); //3



        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

    }

}