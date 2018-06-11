package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * <p>{@code Solver28}インターフェースの実装の一つです。
 * 問題を最大重み2部マッチング→最小費用流問題へと帰着させて、
 * 最短路をダイクストラ法で求めるプライマルデュアル法により解を求めます。
 * <p>ベルマンフォード法より高速なダイクストラ法を用いるために、ポテンシャルという概念を導入しています。
 */
public class Dijkstra implements Solver28 {

	private static final int INF = 1<<30;


	static class Edge {
		int to;
		int cap, cost;
		int rev;

		Edge(int to, int cap, int cost, int rev) {
			this.to = to;
			this.cap = cap;
			this.cost = cost;
			this.rev = rev;
		}
	}


	private int maxElem;
	private int n;

	private int V;
	private List<List<Edge>> graph;

	int[] h;


	@Override
	public void readInput(File input) {
		try(Scanner sc = new Scanner(input)) {
			n = sc.nextInt();
			V = 2*n+2;
			graph = new ArrayList<>(V);
			for(int i=0; i<V; i++) {
				graph.add(new ArrayList<>());
			}
			List<Integer> elems = new ArrayList<Integer>(n*n);
			for(int i=0; i<n*n; i++) {
				elems.add(sc.nextInt());
			}
			maxElem = elems.stream().max(Comparator.naturalOrder()).get();
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					int cost = maxElem - elems.get(i*n+j);
					addEdge(i, n+j, cost);
				}
			}
			for(int i=0; i<n; i++) {
				addEdge(V-2, i, 0);
				addEdge(n+i, V-1, 0);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void solve() {
		h = new int[V];
		Edge[] prevE = new Edge[V];
		for(int i=0; i<n; i++) {
			dijkstra(V-2, prevE);
			int v = V-1;
			while(v != V-2) {
				Edge e = prevE[v];
				e.cap = 0;
				Edge revE = graph.get(v).get(e.rev);
				revE.cap = 1;
				v = revE.to;
			}
		}
	}

	@Override
	public void showResult() {
		int cost = 0;
		List<Integer> match = new ArrayList<>(n);
		for(int v=0; v<n; v++) {
			for(Edge e : graph.get(v)) {
				if(e.cap == 0) {
					cost += e.cost;
					match.add(e.to - n + 1);
				}
			}
		}
		System.out.println("最大： " + (n * maxElem - cost));
		System.out.println(match);
	}

	private void dijkstra(int s, Edge[] prevE) {
		PriorityQueue<int[]> que = new PriorityQueue<>(V, (o1, o2) -> o1[0] - o2[0]);
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		que.add(new int[]{0, s});
		while(!que.isEmpty()) {
			int[] top = que.poll();
			int v = top[1];
			if(top[0] > dist[v]) continue;
			for(Edge e : graph.get(v)) {
				if(e.cap > 0 && dist[e.to] > dist[v] + e.cost + h[v] - h[e.to]) {
					dist[e.to] = dist[v] + e.cost + h[v] - h[e.to];
					que.add(new int[]{dist[e.to], e.to});
					prevE[e.to] = e;
				}
			}
		}
		for(int i=0; i<V; i++) {
			h[i] += dist[i];
		}
	}

	private void addEdge(int from, int to, int cost) {
		Edge e = new Edge(to, 1, cost, graph.get(to).size());
		Edge eRev = new Edge(from, 0, -cost, graph.get(from).size());
		graph.get(from).add(e);
		graph.get(to).add(eRev);
	}

}
