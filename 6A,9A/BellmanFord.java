import java.util.*;

public class BellmanFord {
    static int[] bellmanFord(int V, ArrayList<ArrayList<Integer>> edges, int S){
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        for(int i = 0; i < V-1; i++){
            for(ArrayList<Integer> it: edges){
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);

                if(dist[u] + wt < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }

        for(ArrayList<Integer> it : edges){
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);

            if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]){
                int temp[] = new int[1];
                temp[0] = -1;
                return temp;
            }
        }

        return dist;
    }
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

        adj.add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
        adj.add(new ArrayList<Integer>(Arrays.asList(1, 2, 20)));
        adj.add(new ArrayList<Integer>(Arrays.asList(1, 3, 10)));
        adj.add(new ArrayList<Integer>(Arrays.asList(3, 4, 5)));
        adj.add(new ArrayList<Integer>(Arrays.asList(2, 4, 1)));

        int source = 0;
        int dist[] = bellmanFord(V, adj, source);

        System.out.println(Arrays.toString(dist));
        
    }
}