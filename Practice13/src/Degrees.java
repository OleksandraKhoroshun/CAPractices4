import ua.princeton.lib.*;

import java.util.ArrayList;

public class Degrees {

    public static Digraph G;

    Degrees(Digraph G){
        this.G = G;
    }
    int indegree(int v){
        return G.indegree(v);
    }
    int outdegree(int v){
        return G.outdegree(v);
    }
    Iterable<Integer> sources(){
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<G.V();i++){
                if(indegree(i)==0){
                    res.add(i);
                }
        }
        return res;
    } //-джерела
    Iterable<Integer> sinks() {
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0;i<G.V();i++){
            if(outdegree(i)==0){
                res.add(i);
            }
        }
        return res;
    }//- стоки
    boolean isMap(){
        for(int i=0;i<G.V();i++) {
            if (outdegree(i) != 1) {
                return false;
            }
        }
        return true;
    } //- чи є G відображенням

}
