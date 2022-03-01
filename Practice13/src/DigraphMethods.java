import ua.princeton.lib.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DigraphMethods {
    public static Digraph g;

    public DigraphMethods(Digraph g) {
        this.g = g;
    }

    static void DFS(int v, boolean visited[], Digraph g) {
        visited[v] = true;
        Iterator<Integer> i = g.adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFS(n, visited, g);
        }
    }

    static Digraph Reversed() {
        Digraph dg = new Digraph(g.V());
        for (int v = 0; v < g.V(); v++) {
            Iterator<Integer> i = g.adj[v].iterator();
            while (i.hasNext()) {
                dg.adj[i.next()].add(v);
                (dg.indegree[v])++;
            }
        }
        return dg;
    }

    static boolean isConnected() {
        boolean visited[] = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
        }
        DFS(0, visited, g);
        for (int i = 0; i < g.V(); i++) {
            if (visited[i] == false) return false;
        }

        Digraph g1 = Reversed();
        for (int i = 0; i < g1.V(); i++) {
            visited[i] = false;
        }
        DFS(0, visited, g1);

        for (int i = 0; i < g1.V(); i++) {
            if (visited[i] == false) return false;
        }
        return true;
    }

    public static boolean isEulerianCycle() {
        if (isConnected() == false) return false;
        for (int i = 0; i < g.V(); i++) {
            if (g.adj[i].size() != g.indegree[i]) return false;
        }
        return true;
    }

    public static void printEulerianCycle() {
        Digraph g1 = g;
        if (!isEulerianCycle()) System.out.println("The Digraph does not have a eulerian cycle");
        else {
            if (g1.adj.length == 0) return;

            int[] edge_count = new int[g1.V()];
            for (int i = 0; i < g1.V(); i++) {
                edge_count[i] = g1.adj[i].size();
            }

            Stack<Integer> curr_path = new Stack<>();

            ArrayList<Integer> cycle = new ArrayList<>();

            curr_path.push(0);
            int curr_v = 0;


            while (!curr_path.empty()) {
                if (edge_count[curr_v] > 0) {
                    curr_path.push(curr_v);

                    int next_v = g1.adj[curr_v].iterator().next();

                    edge_count[curr_v]--;

                   // g1.adj[curr_v].iterator().remove();

                    curr_v = next_v;
                } else {
                    cycle.add(curr_v);
                    curr_v = curr_path.peek();
                    curr_path.pop();
                }
            }

            for (int i = cycle.size() - 1; i >= 0; i--) {
                System.out.printf(String.valueOf(cycle.get(i))+" ");

            }
        }
    }

    static void topologicalSort(int v, boolean visited[], Stack stack) {
        visited[v] = true;
        int i;

        Iterator<Integer> it = g.adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSort(i, visited, stack);
        }

        stack.push(v);
    }

    static void topologicalSort() {
        Stack stack = new Stack();

        boolean visited[] = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
        }
        for (int i = 0; i < g.V(); i++) {
            if (!visited[i]) topologicalSort(i, visited, stack);
        }
        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }

    public static void main(String[] args){
        Digraph dg = new Digraph(4);
        dg.addEdge(0,1);
        dg.addEdge(1,2);
        dg.addEdge(2,0);
        dg.addEdge(3,3);
        g=dg;
        printEulerianCycle();
        System.out.println();

        topologicalSort();
    }
}
