// Circular Suffix Array
// 11 October 2016
// Magnus M. Halldorsson
package Skil_5;

import edu.princeton.cs.algs4.In;

import static edu.princeton.cs.algs4.QuickX.sort;

public class CircularSuffixArray {

	CircularSuffix[] circularSuffixArray;
    String text;

	public CircularSuffixArray(String s){
		if (s == null){
			throw new NullPointerException("Input string cannot be null");
		}
		else{
            this.text = s;
			circularSuffixArray = new CircularSuffix[s.length()];
            for(int i = 0; i < s.length(); i++){
                circularSuffixArray[i] = new CircularSuffix(s, i);
            }
		}
		//edu.princeton.cs.algs4.QuickX.sort;
		sort(circularSuffixArray);
	}

    /**
     * Returns the length of the input string.
     * @return the length of the input string
     */
	public int length() // length of s
	{
		return text.length();
	}
	
	/**
     * Returns the index into the original string of the <em>i</em>th smallest circular suffix.
     * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest circular suffix.
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <= i < n}
     */
	public int index(int i) // returns index of ith sorted suffix
	{
	    if(i < 0 || i >= this.length()){
			throw new IndexOutOfBoundsException();
	    }
        return circularSuffixArray[i].suffixIndex;
	}

	public class CircularSuffix implements Comparable<CircularSuffix> {
        String referenceInput;
        int suffixIndex;

		public CircularSuffix(String s, int startIndex) {
			this.referenceInput = s;
            this.suffixIndex = startIndex;
		}

        /*
            Same principles as used in algs4.jar SuffixArray.java
            Small modifications to accommodate to circular suffixes
         */
		public int compareTo(CircularSuffix that) {
            if(this == that) return 0;

            for(int i = 0; i < referenceInput.length(); i++){
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return 0;
		}

        public char charAt(int index){
            // Use the modular of the indecies and string length to complete the circle
            int actualIndex = (index + suffixIndex)%referenceInput.length();
            return (referenceInput.charAt(actualIndex));
        }

		/**
		 * first and last used for testing
		 */

        public char getLastChar(){

			return this.charAt(text.length()-1);
		}

		public char getFirstChar(){
			return this.charAt(text.length());
		}

	}


	public static void main(String[] args) // unit testing
	{
	    
	   In in = new In(args[0]);
	   String s = in.readAll();  // Read whole file
	   String pair = s + s;
	   CircularSuffixArray suffix = new CircularSuffixArray(s);

/*
	   StdOut.println("  i ind select");
	   StdOut.println("-------------------");
	   
	   for (int i = 0; i < s.length(); i++) {
	       int index = suffix.index(i);
	       String ith = "\"" + pair.substring(index, index+Math.min(index + 50, s.length())) + "\"";
	       StdOut.printf("%3d %3d %s %s %s\n", i, index, ith, suffix.circularSuffixArray[i].getLastChar(), suffix.circularSuffixArray[i].getFirstChar());
	   }
	   */
	}	

}
