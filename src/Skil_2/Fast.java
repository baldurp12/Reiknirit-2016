package Skil_2;

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Fast {

    private static void fastCollinearal(Point[] points){

        int len = points.length;


        for(int i = 0; i < len; i++){
            Point p = points[i];
            Point[] orginalList = points.clone();


            Arrays.sort(orginalList, i, len, p.SLOPE_ORDER);
            int counter = 0;
            double findSlope = p.slopeTo(orginalList[i]);

            for(int j = i + 1; j < len; j++) {

                double currentPoint = p.slopeTo(orginalList[j]);
                if (currentPoint == findSlope) {
                    counter++;
                }
                else{
                    if (counter >= 2) {
                        StdOut.print(p);

                        for (int k = (j - 1) - counter; k < j; k++) {
                            StdOut.print(" -> " + orginalList[k]);
                        }
                        StdOut.println();
                    }
                counter = 0;
                findSlope = currentPoint;

                }

            }


        }}

    public static void main(String[] args) {

        In in = new In();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        fastCollinearal(points);

    }
}