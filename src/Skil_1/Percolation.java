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
    protected static int openSites;


    public static void main(String[] args) {// unit testing (required)
        PercolationStats tester = new PercolationStats(5, 5);
        StdOut.print("IT RAN !!");

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
        grid = new int[N][N];
        gridSize = N;
        openSites = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = 0;
            }
        }
    }


    public void open(int row, int col) { // open the site (row, col) if it is not open already
        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            openSites++;
            /**
             * Here we need to check if sites adjacent are also open, and union them.
             * Convert 2D to 1D and
             */
        }

    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        return grid[row][col] == 1;
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

    /**
     * Returns true for now
     *
     * @return
     */
    public boolean percolates() { // does the system percolate?



        return true; // TODO - Perform UnionFind magic
    }

    private boolean insideBounds(int row, int col){
        return (row >= 0 && col >=0) && (row < gridSize && col < gridSize);
    };

}


