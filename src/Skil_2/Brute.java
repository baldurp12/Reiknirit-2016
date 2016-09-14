package Skil_2;

import edu.princeton.cs.algs4.In;
import java.util.Arrays;

/**
 * Created by baldur on 12.9.2016.
 */

public class Brute {

    public static boolean checkifEqual(Point p, Point q, Point r, Point s) {

        return p.slopeTo(q) == p.slopeTo(r) || p.slopeTo(r) == p.slopeTo(s);
    }

    public static void collinear (Point p[]) {
        int c = p.length;
        Arrays.sort(p);
        
        for (int i = 0; i < c; i++) {
            for (int j = i + 1; j < c; j++) {
                for (int k = j; k < c; k++) {
                    for (int l = k; l < c; l++) {
                        if (checkifEqual(p[i], p[j], p[k], p[l])) {

                            System.out.println(p[i].toString() + " -> " +
                                p[j].toString() + " -> " +
                                p[k].toString() + " -> " +
                                p[l].toString());
                        }
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        /*
         * Do not modify
         */
        In in = new In();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);

        }
        collinear(points);
    }
}

