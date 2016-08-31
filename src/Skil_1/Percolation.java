package Skil_1;

import edu.princeton.cs.algs4.*;


/**
 * @author Baldur Mar
 * @author Thorgerdur Edda
 * @instructor Magnus Mar Halldorsson
 * Hand-in assignment 1 for T-301-REIR Autumn semester 2016
 * @category School assignment for T-301-REIR, Reykjavik University
 */


public class Percolation {

    private static int[][] grid; // grid[rows][columns]
    private static int gridSize;
    private static int openSites;
    private QuickUnionUF QF;
    private WeightedQuickUnionUF WQF;
    private int TOP;
    private int BOTTOM;


    public static void main(String[] args) {// unit testing (required)

        Percolation tester = new Percolation(3);
        tester.open(0,0);
        tester.open(0,1);
        tester.open(1,1);
        tester.open(2,1);
        System.out.print(tester.QF.connected(1,9));
        System.out.print(tester.QF.connected(9,1));
        System.out.print(tester.QF.connected(4,9));


        System.out.print("END");
    }

    // These functions were provided by the instructor, implementation is a part of the assignment

    /**
     * Class constructor
     * Sets grid size to N
     * and sets all sites to blocked
     *
     * @param N
     */

    public Percolation(int N) {// create N-by-N grid, with all sites initially blocked, blocked == 0
        if(N <= 0) {
            throw new IllegalArgumentException("N < 0");
        }

        grid = new int[N][N];
        gridSize = N;
        openSites = 0;

        TOP = N*N;
        BOTTOM = N*N+1;


        QF = new QuickUnionUF(N*N + 2 ); // QuickUnion of size N adding 2 spots for top and bottom
        WQF = new WeightedQuickUnionUF(N*N + 2);

        //Union top TOP = N*N
        for(int i = 0; i < N; i++){
            QF.union(convertDimensions(0,i), TOP);
        }

        //Union bottom BOTTOM = N*N +1
        for(int j = 0; j < N; j++){
            QF.union(convertDimensions(N-1,j),  BOTTOM);
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 0;
            }
        }
    }


    public void open(int row, int col) { // open the site (row, col) if it is not open already
        if(!insideBounds(row, col)) throw new IllegalArgumentException("Site is out of bounds");

        if (isOpen(row,col)) {
            grid[row][col] = 1;
            openSites++;

            if(isOpen(row-1, col)){
                QF.union(convertDimensions(row,col), convertDimensions(row-1,col));
            }
            if(isOpen(row+1, col)){
                QF.union(convertDimensions(row,col), convertDimensions(row+1,col));
            }
            if(isOpen(row, col-1)){
                QF.union(convertDimensions(row,col), convertDimensions(row, col-1));
            }
            if(isOpen(row, col+1)){
                QF.union(convertDimensions(row,col), convertDimensions(row, col+1));
            }
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if(!insideBounds(row, col)) throw new IllegalArgumentException("Site is out of bounds");
        else return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) { // is the site (row, col) full?
        return true; // TODO - Perform UnionFind magic
    }

    /**
     * Returns the number of sites open in the grid
     *
     * @return int openSites
     */

    public int numberOfOpenSites() {
        return openSites;
    }


    public boolean percolates() { // does the system percolate?
        return QF.connected(TOP,BOTTOM) || QF.connected(BOTTOM, TOP);
    }

    private boolean insideBounds(int row, int col){
        return (row >= 0 && col >=0) && (row < gridSize && col < gridSize);
    };

    private int convertDimensions(int row, int col){
        //System.out.print("Converting [" + row + "]"+ "[" + col +"]" + " to " + (row * gridSize + col) +"\n");
        return row * gridSize + col;
    }
}


