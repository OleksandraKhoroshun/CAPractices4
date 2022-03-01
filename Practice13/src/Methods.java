import java.util.ArrayList;
import java.util.Iterator;

public class Methods {
    public static Graph g;

    public Methods(Graph g){
        this.g = g;
    }
    public static void DFS(int v,boolean visited[]){
        visited[v] = true;

        Iterator<Integer> i = g.arr[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFS(n, visited);
        }
    }

    public static boolean isConnected() {
        boolean visited[] = new boolean[g.V];

        for (int i = 0; i < g.V; i++) {
            visited[i] = false;
        }

        int i=0;
        for (i = 0; i < g.V; i++) {
            if (g.arr[i].size() != 0) {
                break;
            }
        }

        if (i == g.V) return true;

        DFS(i, visited);

        for (i = 0; i < g.V; i++) {
            if (visited[i] == false && g.arr[i].size() > 0) {
                return false;
            }
        }
        return true;
    }

    public static int isEulerian() {
        if (isConnected() == false)
            return 0;

        int odd = 0;
        for (int i = 0; i < g.V; i++) {
            if (g.arr[i].size() % 2 != 0) {
                odd++;
            }
        }
        if (odd > 2) return 0;

        if(odd==2) return 1;
        else return 2;
    }

    public static boolean isValidNextEdge(int u, int v,Graph g1) {
        if (g1.arr[u].size() == 1) {
            return true;
        }

        boolean[] visited = new boolean[g1.V];
        int count1 = dfsCount(u, visited,g1);

        g1.removeEdge(u, v);
        visited = new boolean[g1.V];
        int count2 = dfsCount(u, visited,g1);

        g1.addEdge(u, v);
        return (count1 > count2) ? false : true;
    }

    public static int dfsCount(int v, boolean[] isVisited,Graph g1)
    {
        isVisited[v] = true;
        int count = 1;

        for (int adj : g1.arr[v]) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited,g1);
            }
        }
        return count;
    }

    public static String EulerianCycle(int u,Graph g1){
        for (int i = 0; i < g1.arr[u].size(); i++) {
            int v = g1.arr[u].get(i);

            if (isValidNextEdge(u, v,g1)) {
                res+=(" "+u + "-" + v + " ");
                //System.out.println(" "+u + "-" + v + " ");

                g1.removeEdge(u, v);
                EulerianCycle(v,g1);
            }
        }
        return res;
    }
    static String res;
    public static void printEulerianCycle() {
        res="";
        if (isEulerian() == 0) System.out.println("Graph is not Eulerian.");
        else if (isEulerian() == 1) System.out.println("Graph has a Eulerian path, but not a cycle");
        else {
            Graph g1 = g;
            System.out.println(EulerianCycle(0,g1));
        }
    }

    static boolean hasHamiltCycle;

    static void HamiltonianCycle() {

        hasHamiltCycle = false;

        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);

        boolean[] visited = new boolean[g.V()];

        for (int i = 0; i < visited.length; i++)
            visited[i] = false;

        visited[0] = true;

        FindHamCycle( 1, path,
                visited);

        if(hasHamiltCycle) System.out.println(HamCycle);
        else  {
            System.out.println("No Hamiltonian Cycle");
            return;
        }
    }

    static String HamCycle="";

    static void FindHamCycle(int pos, ArrayList<Integer> path, boolean[] visited) {
        if (pos == g.V()) {

            if (g.arr[path.get(path.size() - 1)].contains(path.get(0))) {

                path.add(0);
                for (int i = 0; i < path.size(); i++) {

                   // System.out.print(path.get(i) + " ");
                    HamCycle+=path.get(i) + " - ";
                }

                HamCycle+="\n";
               // System.out.println();
                path.remove(path.size() - 1);

                hasHamiltCycle = true;
            }
            return;
        }

        for (int v = 0; v < g.V(); v++) {

            if (isSafe(v, path, pos) && !visited[v]) {

                path.add(v);
                visited[v] = true;

                FindHamCycle( pos + 1, path, visited);

                visited[v] = false;
                path.remove(path.size() - 1);
            }
        }
    }

   static boolean isSafe(int v, ArrayList<Integer> path, int pos) {

        if (!g.arr[path.get(pos - 1)].contains(v))
            return false;

        for (int i = 0; i < pos; i++) {
            if (path.get(i) == v) return false;
        }

        return true;
    }

    public static void main(String[] args){
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g=g1;
        printEulerianCycle();

        Graph g2 = new Graph(6);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.addEdge(0, 2);
        g2.addEdge(3, 4);
        g2.addEdge(4, 5);
        g2.addEdge(5, 0);
        g2.addEdge(1, 5);
        g2.addEdge(1, 4);
        g=g2;
        printEulerianCycle();
g=g2;
        HamiltonianCycle();





    }
}
