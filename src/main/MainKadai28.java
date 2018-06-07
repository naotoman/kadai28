package main;

import java.io.File;

public class MainKadai28 {

	enum Algorithm {
		BRUTE_FORCE,
		BELLMAN_FORD,
		DIJKSTRA,
	}



	private static final String INPUT_PATH = "input/sample28.txt";

	private static final Algorithm ALGORITHM_TO_RUN = Algorithm.DIJKSTRA;



	public static void main(String[] args) {
		AlgorithmFactory factory = new AlgorithmFactory();
		Solver28 sol = factory.choose(ALGORITHM_TO_RUN);

		File input = new File(INPUT_PATH);

		sol.readInput(input);
		sol.solve();
		sol.showResult();
	}
}
