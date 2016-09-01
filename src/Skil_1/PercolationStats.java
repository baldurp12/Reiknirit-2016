package Skil_1;

import edu.princeton.cs.algs4.*;

public class PercolationStats {
	
	private static double[] openedSites;
	
	public PercolationStats(int N, int T){ // perform T independent experiments on an N-by-N grid

		if(N <= 0 || T <= 0){
				throw new IllegalArgumentException();
		}
		
		openedSites = new double[T];


		for(int i = 0; i < T; i++){
			Percolation block = new Percolation(N);
			while(!block.percolates()){
				block.open(StdRandom.uniform(N), StdRandom.uniform(N));
			}
			double nSquare = N*N;
            //StdOut.print((block.numberOfOpenSites() / (nSquare)) + "\n");
			openedSites[i] = (block.numberOfOpenSites() / (nSquare));
		}

	}
	 
	public double mean(){ // sample mean of percolation threshold
		return StdStats.mean(openedSites);
	} 
	 
	 
	public double stddev(){ // sample standard deviation of percolation threshold
		return StdStats.stddevp(openedSites);
	} 
	 
	 
	public double confidenceLow(){ // low end point of 95%confidence interval
		/*
		 * (1.96)*mean() minus 
		 * java.lang.Math.sqrt(openedSites.length)
		 */

		return this.mean() -
                (1.96 * this.stddev()) /
                        Math.sqrt(openedSites.length);
	} 
	 
	 
	public double confidenceHigh() {
        return this.mean() +
                (1.96 * this.stddev()) /
                        Math.sqrt(openedSites.length);

    }
}
