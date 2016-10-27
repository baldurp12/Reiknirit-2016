// Move-to-front
// 11 October 2016, Magnus M. Halldorsson
package Skil_5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

	private static final int asciiSize = 256;

	// apply move-to-front encoding
	public static void encode(BinaryIn in, BinaryOut out) {
		char[] asciiArray = buildArray();

		while(!in.isEmpty()){
			char currChar = in.readChar();
			char index = getIndex(currChar, asciiArray);
			asciiArray = mtf(asciiArray, currChar, index);
			out.write(index);
		}

	}

	// apply move-to-front decoding
	public static void decode(BinaryIn in, BinaryOut out) {

		char[] asciiArray = buildArray();
		while(!in.isEmpty()){
			char currChar = in.readChar();
			out.write(asciiArray[currChar]);
			asciiArray = mtf(asciiArray, asciiArray[currChar], currChar);
		}
	}

	/**
	 *
	 * @param currChar
	 * @param asciiArray
	 * @return
	 */
	private static char getIndex(char currChar, char[] asciiArray) {
		for(char i = 0; i < asciiSize; i++){
			if (asciiArray[i] == currChar){
				return i;
			}
		}
		return 0;
	}

	private static char[] mtf(char[] inArr, char charToMove, char indexOfChar){

		for(char i = indexOfChar; i > 0; i--){
			inArr[i] = inArr[i-1];
		}
		inArr[0] = charToMove;
		return inArr;
	}

	private static char[] buildArray(){
		char[] returnArray = new char[asciiSize];
		for (int i = 0; i < asciiSize; i++) {
			returnArray[i] = (char)(i & 0xff);
		}
		return returnArray;
	}


	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	// if args[0] is 'b', perform both
	public static void main(String[] args) {

		if (args.length < 1) {
			StdOut.println("Usage: java MoveToFront (-|+|b) <infile> <outfile>");
			return;
		}
		BinaryIn in = new BinaryIn(args[1]);
		BinaryOut out = new BinaryOut(args[2]);
		char ch = args[0].charAt(0);
		if (ch == '-')
			encode(in, out);
		else if (ch == '+')
			decode(in,out);
		else if (ch == 'b') { // Do both encode then decode
			encode(in,out);
			out.close();
			BinaryIn in2 = new BinaryIn(args[2]);
			out = new BinaryOut(args[2]+".out");
			decode(in2,out);
		}
		out.close();
	}

}
