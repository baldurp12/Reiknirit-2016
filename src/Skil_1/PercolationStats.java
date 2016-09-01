package Skil_1;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;

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
			Std
			openedSites[i] = (block.numberOfOpenSites() / (N*N));
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
		return 0.0;
	} 
	 
	 
	public double confidenceHigh() { // high end point of 95% confidence interval
		return 0.0; //TODO 	 
	}
	
}
