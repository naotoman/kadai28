package main;

import algorithm.BellmanFord;
import algorithm.BruteForce28;
import algorithm.Dijkstra;
import main.MainKadai28.Algorithm;

class AlgorithmFactory {

	Solver28 choose(Algorithm x) {
		switch(x) {
			case BRUTE_FORCE :
				return new BruteForce28();
			case BELLMAN_FORD :
				return new BellmanFord();
			case DIJKSTRA :
				return new Dijkstra();
			default:
				throw new IllegalArgumentException("Bug");

		}
	}

}
