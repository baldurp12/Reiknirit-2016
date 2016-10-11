package Skil_3;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.RectHV;


public class KdTree {
    private final short VERTICAL = 1;
    private final short HORIZONTAL = 2;
    private int sizeCounter;
    private Node root;

    /*
        Taken from the checklist.pdf provided
     */
    private class Node {
        private short orientation;
        private Point2D point; // the point
        private RectHV rectangle; // the axis-aligned rectangle corresponding to this node
        private Node rt; // the right/top subtree
        private Node lb; // the left/bottom subtree

        public Node(Point2D point, short orientation) {
            this.point = point;
            this.lb = null;
            this.rt = null;
            this.rectangle = null;
            this.orientation = orientation;
        }




    }

    // construct an empty set of points
    public KdTree() {
        root = null;
        sizeCounter = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return sizeCounter;
    }

    /*
    The algorithms for search and insert are similar to those for BSTs,
    VERTICAL:
    (if the point to be inserted has a smaller x-coordinate than the point at the root, go left; otherwise go right);
    HORIZONTAL:
    (if the point to be inserted has a smaller y-coordinate than the point in the node, go left; otherwise go right);
     */
    private boolean pointLessThanNode(Node compNode, Point2D compPoint){
        if(compNode.orientation == HORIZONTAL){
            return compPoint.y() < compNode.point.y();
        }
        else{
            return compPoint.x() < compNode.point.x();
        }
    }

    // add the point point to the set (if it is not already in the set)
    public void insert(Point2D point) {
        if(this.contains(point)){
            //Do nothing
        }
        else if(root == null) {
            root = new Node(point, VERTICAL);
            root.rectangle = new RectHV(0,0,1,1);
            sizeCounter++;
        }
        else{
            root = insertNewNode(root, point, this.root.orientation, root.rectangle);
        }
    }


    private Node insertNewNode(Node curRoot, Point2D point, short rootOrientation, RectHV rect){

        // Base case
        if(curRoot == null){
            if(rootOrientation == VERTICAL) {
                Node newNode =  new Node(point, HORIZONTAL);
                sizeCounter++;
                return newNode;
            }
            else{
                Node newNode = new Node(point, VERTICAL);
                sizeCounter++;
                return newNode;
            }
        }

        if(pointLessThanNode(curRoot, point)){
            curRoot.lb = insertNewNode(curRoot.lb, point, curRoot.orientation, curRoot.rectangle);
            if(curRoot.lb.rectangle == null){
                if(rootOrientation == HORIZONTAL){
                    curRoot.lb.rectangle = new RectHV(rect.xmin(), rect.ymin(),
                                                        rect.xmax(), point.y());
                }
                else{
                    curRoot.lb.rectangle = new RectHV(rect.xmin(), rect.ymin(),
                                                        point.x(), rect.ymax());
                }
            }
        }
        else if (!pointLessThanNode(curRoot, point)){
            curRoot.rt = insertNewNode(curRoot.rt, point, curRoot.orientation, curRoot.rectangle);
            if(curRoot.rt.rectangle == null){
                if(rootOrientation == HORIZONTAL){
                    curRoot.rt.rectangle = new RectHV(rect.xmin(), point.y(),
                                                        rect.xmax(), rect.ymax());
                }
                else{
                    curRoot.rt.rectangle = new RectHV(point.x(), rect.ymin(),
                                                        rect.xmax(), rect.ymax());
                }
            }
        }

        return curRoot;
    }

    // does the set contain the point point?
    public boolean contains(Point2D p) {
        if(this.root == null){
            return false;
        }
        if(this.root.point.equals(p)){
            return true;
        }
        return containsHelper(this.root,p);
    }

    private boolean containsHelper(Node root, Point2D point) {
        if(root == null){
            return false;
        }
        if(root.point.compareTo(point) == 0){
            return true;
        }
        if(pointLessThanNode(root, point)){
            return containsHelper(root.lb, point);
        }

        else{
            return containsHelper(root.rt, point);
        }
    }

    // draw all of the points to standard draw
    public void draw() {
        drawAll(root);
    }

    private void drawAll(Node curRoot) {
        if (curRoot == null){
            return;
        }
        drawAll(curRoot.lb);


        System.out.println("Trying to draw" +  curRoot.point.x() + ", " + curRoot.point.y());

        if (curRoot.orientation == VERTICAL) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(curRoot.point.x(), curRoot.rectangle.ymin(), curRoot.point.x(),
                            curRoot.rectangle.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(curRoot.rectangle.xmin(), curRoot.point.y(), curRoot.rectangle.xmax(),
                            curRoot.point.y());
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(curRoot.point.x(),curRoot.point.y());
        drawAll(curRoot.rt);
    }


    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        Stack<Point2D> retStack = new Stack<Point2D>();

        return null;
    }

    private Point2D stackBuilder(Node curRoot, Stack<Point2D> stack){
       return null;
    }

    // a nearest neighbor in the set to point; null if set is empty
    public Point2D nearest(Point2D p) {

        return p;
    }

    /*
        Prints all the nodes in tree, to test if all points where being inserted into the tree correctly
     */
    public void printAll(){

        print(root);
    }

    private void print(Node curRoot){
        if (curRoot == null){
            return;
        }
        print(curRoot.lb);

        System.out.println(curRoot.point.toString() + "'s rectangle is: " + curRoot.rectangle.toString());

        print(curRoot.rt);
    }


    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int N = in.readInt(), C = in.readInt(), T = 20;
        KdTree tree = new KdTree();
        Point2D [] points = new Point2D[C];
        out.printf("Inserting %d points into tree\n", N);
        for (int i = 0; i < N; i++) {
            tree.insert(new Point2D(in.readDouble(), in.readDouble()));
        }
        out.printf("tree.size(): %d\n", tree.size());
        out.printf("Testing contains method, querying %d points\n", C);
        for (int i = 0; i < C; i++) {
            points[i] = new Point2D(in.readDouble(), in.readDouble());
            out.printf("%s: %s\n", points[i], tree.contains(points[i]));
        }
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < C; j++) {
                tree.contains(points[j]);
            }
        }

        /*KdTree set = new KdTree();
        Point2D point0 = new Point2D(0.5, 0.5);
        Point2D point1 = new Point2D(0.6, 0.4);
        Point2D point2 = new Point2D(0.4, 0.2);
        Point2D point3 = new Point2D(0.3, 0.9);
        Point2D point4 = new Point2D(0.2, 0.3);
        Point2D point5 = new Point2D(0.1, 0.5);
        Point2D point6 = new Point2D(0.5, 0.7);
        Point2D point7 = new Point2D(0.6, 0.1);
        Point2D point8 = new Point2D(0.7, 0.5);



        set.insert(point0);
        set.insert(point1);
        set.insert(point2);
        set.insert(point3);
        set.insert(point4);
        set.insert(point5);
        set.insert(point6);
        set.insert(point7);
        set.insert(point8);


        //set.printAll();
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(255,0,0);
        set.draw();

        //System.out.println(set.root.point.toString());
        //System.out.println(set.root.lb.point.toString());
        //System.out.println(set.root.rt.point.toString());
        if(set.root.lb == null){
            System.out.println("left is empty");
        }
        if(set.root.rt == null){
            System.out.println("right is empty");
        }
        */

        /*
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHVreference[] rectangles = new RectHVreference[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHVreference(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        KdTree set = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D point : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D point : ptset) {
                ptarr[j] = point;
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
        */

    }

}
