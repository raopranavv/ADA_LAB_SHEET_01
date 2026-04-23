#Dijkstra (Greedy)
import java.util.*;

class Pair {
    int node, weight;

    Pair(int n, int w) {
        node = n;
        weight = w;
    }
}

public class DijkstraAlgo {

    public static Map<String, Integer> dijkstra(Map<String, List<Pair>> graph, String start) {
        Map<String, Integer> dist = new HashMap<>();

        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dist.put(start, 0);

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Pair(start.hashCode(), 0));

        Map<Integer, String> reverseMap = new HashMap<>();
        for (String key : graph.keySet()) {
            reverseMap.put(key.hashCode(), key);
        }

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            String node = reverseMap.get(current.node);
            int d = current.weight;

            for (Pair neighbor : graph.get(node)) {
                String nextNode = reverseMap.get(neighbor.node);
                int newDist = d + neighbor.weight;

                if (newDist < dist.get(nextNode)) {
                    dist.put(nextNode, newDist);
                    pq.add(new Pair(neighbor.node, newDist));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Map<String, List<Pair>> graph = new HashMap<>();

        graph.put("A", Arrays.asList(new Pair("B".hashCode(), 1), new Pair("C".hashCode(), 4)));
        graph.put("B", Arrays.asList(new Pair("C".hashCode(), 2), new Pair("D".hashCode(), 5)));
        graph.put("C", Arrays.asList(new Pair("D".hashCode(), 1)));
        graph.put("D", new ArrayList<>());

        Map<String, Integer> result = dijkstra(graph, "A");

        for (String node : result.keySet()) {
            System.out.println(node + " : " + result.get(node));
        }
    }
}


#Bellman-Ford (Dynamic Programming)
import java.util.*;

class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class BellmanFord {

    public static int[] bellmanFord(List<Edge> edges, int V, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[src] = 0;

        // Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        // Check negative cycle
        for (Edge e : edges) {
            if (dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]) {
                System.out.println("Negative cycle detected");
                return null;
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 5));
        edges.add(new Edge(1, 2, -3));
        edges.add(new Edge(2, 3, 4));

        int[] result = bellmanFord(edges, 4, 0);

        if (result != null) {
            for (int i = 0; i < result.length; i++) {
                System.out.println("Node " + i + " : " + result[i]);
            }
        }
    }
}
