import ua.princeton.lib.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public class EulerianGraph {

    private static class Point{
        int x;
        int y;

        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

    }

    public static int V;
    public static int E;
    public static HashMap<Point,List<Point>> arr;

    EulerianGraph(int V){
        this.V = V;
        this.E = 0;
        arr = new HashMap<>();
    }
    //створити порожній граф з V вершин

    EulerianGraph(In in){
        this.V = in.readInt();
        arr = new HashMap<>();
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            String v =in.readLine();
            int vx = Integer.parseInt(v.split(" ")[0]);
            int vy = Integer.parseInt(v.split(" ")[1]);
            String w =in.readLine();
            int wx = Integer.parseInt(w.split(" ")[0]);
            int wy = Integer.parseInt(w.split(" ")[1]);
            Point vP = new Point(vx,vy);
            Point wP = new Point(wx,wy);
            addEdge(vP, wP);
        }
    }
    // створити граф з вхідного потоку

    void addEdge(Point v, Point w) {
        E++;
        if(arr.containsKey(v)) {
            arr.get(v).add(w);
        }else {
            arr.put(v,new ArrayList<>());
            arr.get(v).add(w);
        }
        if(arr.containsKey(w)) {
            arr.get(w).add(v);
        }else {
            arr.put(w,new ArrayList<>());
            arr.get(w).add(v);
        }

    }
    // додати ребро v-w

    void removeEdge(int u, int v)
    {
        E--;
        arr.get(u).remove((Integer)v);
        arr.get(v).remove((Integer)u);
    }

    Iterable<Point> adj(Point v){
      return arr.get(v);
    }
    //вершини з’єднані (сусідні) з v

    static int V(){
        return V;
    }
    //кількість вершин

    static int E(){
        return E;
    }
    //кількість ребер

    public static double max(){
        double res = Double.NEGATIVE_INFINITY;
       for(Point p:arr.keySet()){
           for(Point p1 : arr.get(p)){
               if(p1.x > res ) res = p1.x;
               if(p1.y > res) res=p1.y;
           }
       }
       return abs(res);
    }
    public static void show(){
        StdDraw.setXscale(-max(), max());
        StdDraw.setYscale(-max(), max());
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.show(0);

        int count=0;
        Point [][]visited = new Point[E()][2];
        for(Point p:arr.keySet()){
            for(Point p1 : arr.get(p)) {
                boolean draw=true;
                for(int i=0;i<count;i++){
                    if((visited[i][0] == p && visited[i][1] == p1) || ( visited[i][0]==p1 && visited[i][1]==p)){
                        draw=false;
                    }
                }
                if (draw) {
                    StdDraw.line(p.x, p.y, p1.x, p1.y);
                    System.out.println(p.x + " : " + p.y + " - " + p1.x + " : " + p1.y);
                    visited[count][0] = p;
                    visited[count][1] = p1;
                    count++;
                }
            }
            }
        StdDraw.show(0);

    }

   /* public static boolean isConnected() {
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        int i=0;
        Point p1=null;
        for (Point p : arr.keySet()) {
            p1=p;
            i++;
            if (arr.get(p).size() != 0) {
                //p1 =p;
                break;
            }

        }

        if (i == V) return true;

        DFS(p1,i, visited);

        i=0;
        for (Point p : arr.keySet()) {
            if (visited[i] == false && arr.get(p).size()  > 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static void DFS(Point vp,int v,boolean visited[]){
        visited[v] = true;

        Iterator<Point> i = arr.get(v).listIterator();
        while (i.hasNext())
        {
            Point n = i.next();
           // v++;
            int i1=0;
            for (Point p : arr.keySet()){
                if(p.x ==n.x && p.y == n.y) {break;}
                    i1++;
            }
            v=i1;
            //System.out.println(v);
            if (!visited[v])
                DFS(n,v, visited);
        }
    }

    public static int isEulerian() {
        if (isConnected() == false)
            return 0;

        int odd = 0;
        for (Point p : arr.keySet()){
            if (arr.get(p).size() % 2 != 0) {
                odd++;
            }
        }
        if (odd > 2) return 0;

        if(odd==2) return 1;
        else return 2;
    }
*/
    public static void main(String[] args){
        EulerianGraph g1 = new EulerianGraph(4);
        g1.addEdge(new Point(0,0), new Point(0,1));
        g1.addEdge(new Point(0,0), new Point(1,0));
        g1.addEdge(new Point(1,1), new Point(0,1));
        g1.addEdge(new Point(1,1), new Point(1,0));
        show();
       // System.out.println(isEulerian());
    }
}
