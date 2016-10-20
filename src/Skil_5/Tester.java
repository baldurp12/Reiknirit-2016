// Tester for S4 in Reiknirit fall 2016
// 11 October 2016, Magnus M. Halldorsson
package Skil_5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.Huffman;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
		   if (args.length < 1) {
			   StdOut.println("Usage: java Tester <filename>");
		   }
		   Stopwatch t = new Stopwatch();
		   
		   // Apply Burrows-Wheeler
		   BinaryIn in = new BinaryIn(args[0]);
		   String bw = args[0]+".bw";
		   BinaryOut out = new BinaryOut(bw);
		   BurrowsWheeler.transform(in,out);
		   out.close();

		   // Move-to-front
		   BinaryIn in2 = new BinaryIn(bw);
		   String mtf = bw + ".mtf";
		   out = new BinaryOut(mtf);
		   MoveToFront.encode(in2, out);
		   out.close();

		   // Apply Huffman coding; requires stdin/stdout
		   String huf = mtf + ".huf";
		   out = new BinaryOut(huf);
		   System.setIn(new FileInputStream(mtf));
		   System.setOut(new PrintStream(huf));
		   Huffman.compress();
		   
		   System.err.println("Time spent: "+ t.elapsedTime());
	}

}
