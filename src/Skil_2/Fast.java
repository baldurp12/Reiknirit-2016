package Skil_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Vector;

import java.util.Arrays;
import java.util.List;

/**
 * Created by baldur on 12.9.2016.
 */
public class Fast {


    public Fast(){

    }

    public Point[] fastCollinearal(Point[] points){
        Point[] orginalList = new Point[points.length];


        for(int i = 0; i < points.length; i++){
            Arrays.sort(points, i, points.length, orginalList[i].SLOPE_ORDER);

            for(int j = i+1; j < points.length; j++){
                for(int k = j + 1 ; k < points.length; k++){
                    int counter = 1;
                    if(points[i].slopeTo(points[k]) == points[j].slopeTo(points[k])){

                    }
                }


            }
        }

        return null;
    }


    public static void main(String[] args){

        In in = new In();
        //Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }


        Arrays.sort(points, 0, points.length, points[0].SLOPE_ORDER);
        for (int i = 3; i < points.length; i++){
            System.out.println(points[i].x + ", " + points[i].y);
        }


        /*
        Point temp = new Point(points[0].x, points[0].y);
        Arrays.sort(points, points[0].SLOPE_ORDER);

        for(int i = 0; i < points.length; i++){
            System.out.println();
            System.out.println(points[i].x + ", " + points[i].y);
        }

        for(int i = 0; i < points.length; i++){
            System.out.println("Slope from " + temp + " to " +
                    points[i] + ": " + temp.slopeTo(points[i]) );
        }

        */
    }


}
