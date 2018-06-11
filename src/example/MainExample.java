package example;

import java.io.File;

import algorithm.BellmanFord;
import algorithm.BruteForce28;
import algorithm.Dijkstra;
import algorithm.Solver28;

/**
 * <p>課題28を解くメイン関数の一例です。メイン関数自体は4行程度なので、詳しくはコードを実際に読んでみてください。
 */
public class MainExample {

	//入力ファイルのパスを指定
	private static final String INPUT_PATH = "input/sample28.txt";


	//実行するアルゴリズムを選択するために定義。chooseAlgorithmメソッドで使用。
	private static final int BRUTE_FORCE = 0;
	private static final int BELLMAN_FORD = 1;
	private static final int DIJKSTRA = 2;



	public static void main(String[] args) {
		Solver28 sol = chooseAlgorithm(2);

		sol.readInput(new File(INPUT_PATH));
		sol.solve();
		sol.showResult();
	}


	/*
	 * 引数で渡された数に応じたアルゴリズムを実装したクラスを返す。
	 */
	private static Solver28 chooseAlgorithm(int x) {
		switch(x) {
		case BRUTE_FORCE :
			return new BruteForce28();
		case BELLMAN_FORD :
			return new BellmanFord();
		case DIJKSTRA :
			return new Dijkstra();
		default:
			throw new IllegalArgumentException("No Such Solver");
		}
	}

	private MainExample(){};

}
