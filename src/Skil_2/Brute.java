package Skil_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

/**
 * Created by baldur on 12.9.2016.
 */

public class Brute {

    public static boolean checkifEqual(Point p, Point q, Point r, Point s) {

        if(p.slopeTo(q) == p.slopeTo(r)){
            if (p.slopeTo(q) == p.slopeTo(s)) {
                return true;
            }
        }
        return false;
    }

    public static void collinear (Point p[]) {
        Arrays.sort(p);
        int len = p.length;

        // 4 x for loop because O(n^4)
        for (int i = 0; i < len; i++) {

            for (int j = i + 1; j < len; j++) {

                for (int k = j + 1; k < len; k++) {

                    for (int l = k + 1; l < len ; l++) {

                        if (checkifEqual(p[i], p[j], p[k], p[l])) {

                            StdOut.println(p[i].toString() + " -> " + p[j].toString() + " -> " + p[k].toString() + " -> " + p[l].toString());
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Stopwatch timer = new Stopwatch();

        In in = new In("/Users/thorgerdureiriksdottir/Desktop/REIR/Reiknirit-2016/lib/testinput/input6.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);

        }
        collinear(points);

        double t = timer.elapsedTime();
        StdOut.print("time:" + t);


    }
}

