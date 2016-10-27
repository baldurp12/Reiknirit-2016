package Skil_5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;


public class BurrowsWheeler {

	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	public static void transform(BinaryIn in, BinaryOut out) {
        CircularSuffixArray suffixArray = new CircularSuffixArray(in.readString());

        int first = 0;
        for(int i = 0; i < suffixArray.length(); i++){
            if(suffixArray.index(i) == 0){
                first = i;
                break;
            }
        }

        out.write(first);
        for (int i = 0; i < suffixArray.length(); i++){
            char c = suffixArray.circularSuffixArray[i].getLastChar();
            out.write(c);
        }


	}


	// apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	public static void inverseTransform(BinaryIn in, BinaryOut out) {
        int asciiSize = 256; //Largest possible numbers of individual characters we can encounter in this assignment
        int index = in.readInt();

        //Key-index counting (pg. 705 in text book)
        String text = in.readString();
        int N = text.length();
        int[] count = new int[asciiSize+1];

        int[] next = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            count[text.charAt(i) + 1]++;
        }

        for (int r = 0; r < asciiSize; r++){
            count[r + 1] += count[r];
        }

        for (int i = 0; i < text.length(); i++){
            next[count[text.charAt(i)]++] = i;
        }

        // Decoding:
        int i = next[index];
        for (int j = 0; j < N; j++) {
            out.write(text.charAt(i));
            i = next[i];
        }
    }

	public static void main(String[] args) {
		if (args.length < 3) {
			StdOut.println("Usage: java BurrowsWheeler (-|+) <infile> <outfile>");
			return;
				}
		BinaryIn in = new BinaryIn(args[1]);
		BinaryOut out = new BinaryOut(args[2]);
		char ch = args[0].charAt(0);
		if (ch == '-')
			transform(in, out);
		else if (ch == '+')
			inverseTransform(in,out);
		else if (ch == 'b') { // Do both encode then decode
			transform(in, out);
			out.close();
			BinaryIn in2 = new BinaryIn(args[2]);
			out = new BinaryOut(args[2]+".out");
			inverseTransform(in2,out);
		}
		out.close();
	}


}
