package main;

import java.io.File;


/*
 * 課題28を解くメイン部分．
 * 実行する前に，二つの変数INPUT_PATHとALGORITHM_TO_RUNの値を設定しておく．
 */
public class MainKadai28 {

	enum Algorithm {
		BRUTE_FORCE,//Brute Forceなアルゴリズム．割り当てを全通り試す．
		BELLMAN_FORD,//最小費用流に帰着．ベルマンフォードで最短路に繰り返し流す．
		DIJKSTRA,//最小費用流に帰着．ポテンシャルを導入し，ダイクストラで最短路に繰り返し流す．
	}


	//入力となるファイルまでのパスを設定する．
	private static final String INPUT_PATH = "input/sample28.txt";

	//実行したいアルゴリズムを設定．上記のenum(Algorithm)のなかから選択する．
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
