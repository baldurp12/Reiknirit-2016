
/****************************************************************************
 *  Compilation:  javac PointSET.java
 *  Execution:    
 *  Dependencies:
 *  Author:
 *  Date:
 *
 *  Data structure for maintaining a set of 2-D points, 
 *    including rectangle and nearest-neighbor queries
 *
 *************************************************************************/

package Skil_3;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
    public SET<Point2D> point2DSet;

    // construct an empty set of points
    public PointSET() {

        point2DSet = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {

        return point2DSet.isEmpty();
    }

    // number of points in the set
    public int size() {

        return point2DSet.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {

        point2DSet.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {

        return point2DSet.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.setPenRadius(0.01);

        for (Iterator<Point2D> i = point2DSet.iterator(); i.hasNext(); ) {
            Point2D curPoint = i.next();
            StdDraw.point(curPoint.x(), curPoint.y());
            StdDraw.show();
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        SET<Point2D> returnSET = new SET<Point2D>();

        for (Iterator<Point2D> i = point2DSet.iterator(); i.hasNext(); ) {
            Point2D curPoint = i.next();
            if (rect.contains(curPoint)) {
                returnSET.add(curPoint);
            }

        }

        return returnSET;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {

        if(point2DSet.isEmpty()){
            return null;
        }

        Point2D closestPoint = null;

        double lowestDistance = Double.MAX_VALUE;
        double tempDistance = Double.MAX_VALUE;

        for (Iterator<Point2D> i = point2DSet.iterator(); i.hasNext(); ) {
            Point2D curPoint = i.next();
            tempDistance = curPoint.distanceSquaredTo(p);
            if(tempDistance < lowestDistance){
                lowestDistance = tempDistance;
                closestPoint = curPoint;
            }
        }
        return closestPoint;
    }


    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        PointSET set = new PointSET();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }

        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();
    }
}

