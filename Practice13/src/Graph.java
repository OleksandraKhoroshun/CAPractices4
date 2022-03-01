import ua.princeton.lib.*;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    public int V;
    public int E;
    public List<Integer>[] arr;

    Graph(int V){
        this.V = V;
        this.E = 0;
        arr = new List[V];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
    }
    //створити порожній граф з V вершин

    Graph(In in){
        this.V = in.readInt();
        arr = new List[V];
        for (int i = 0; i < V; i++) {
            arr[i] = new ArrayList<>();
        }
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }
    // створити граф з вхідного потоку

    void addEdge(int v, int w) {
        E++;
        arr[v].add(w);
        arr[w].add(v);
    }
    // додати ребро v-w

    void removeEdge(int u, int v)
    {
        E--;
        arr[u].remove((Integer)v);
        arr[v].remove((Integer)u);
    }

    Iterable<Integer> adj(int v){
        return arr[v];
    }
    //вершини з’єднані (сусідні) з v

    int V(){
        return V;
    }
    //кількість вершин

    int E(){
        return E;
    }
    //кількість ребер

}
