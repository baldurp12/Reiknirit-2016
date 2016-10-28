// Tester for S4 in Reiknirit fall 2016
// 11 October 2016, Magnus M. Halldorsson
package Skil_5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.Huffman;
import edu.princeton.cs.algs4.Stopwatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {

		Stopwatch t = new Stopwatch();
		// Apply Huffman decoding; requires stdin/stdout
		String huf = args[0];
		try{
			System.setIn(new FileInputStream(huf));
			System.setOut(new PrintStream(huf + ".exHuf"));
		}
		catch (RuntimeException e){
			e.getMessage();
		}

		Huffman.expand();

		BinaryIn in = new BinaryIn((huf + ".exHuf"));
		BinaryOut out = new BinaryOut((huf + ".reMTF"));
		MoveToFront.decode(in,out);

		out.close();

		in = new BinaryIn((huf + ".reMTF"));
		String bw = args[0] + ".final.txt";
		out = new BinaryOut(bw);
		BurrowsWheeler.inverseTransform(in, out);
		out.close();


		System.err.println("Time spent decompressing " + args[0] +" : " + t.elapsedTime());
		/*
		if (args.length < 1) {
		    StdOut.println("Usage: java Tester <filename>");
		}

		Stopwatch t = new Stopwatch();

		// Apply Burrows-Wheeler
		BinaryIn in = new BinaryIn(args[0]);
		String bw = args[0] + ".bw";
		BinaryOut out = new BinaryOut(bw);
		BurrowsWheeler.transform(in, out);
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
		try{
			System.setIn(new FileInputStream(mtf));
			System.setOut(new PrintStream(huf));
		}
		catch (RuntimeException e){
			e.getMessage();
		}

		Huffman.compress();

		System.err.println("Time spent compressing " + args[0] +" : " + t.elapsedTime());
		*/

	}

}
