package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;


/**
 * <p>{@code Solver28}インターフェースの実装の一つで、可能な割り当てを全通り試すアルゴリズムです。
 * 計算量はO(n!)です。
 */
public class BruteForce28 implements Solver28 {

	private int n;
	private int[][] matrix;

	private int maxPoint;
	private int[] solMatching;


	private int[] tmpSol;
	private BitSet unused;


	@Override
	public void readInput(File input) {
		try(Scanner sc = new Scanner(input)) {
			n = sc.nextInt();
			matrix = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void solve() {
		maxPoint = 0;
		solMatching = new int[n];
		tmpSol = new int[n];
		unused = new BitSet(n);
		unused.set(0, n);
		bruteForce(0, 0);
	}

	@Override
	public void showResult() {
		System.out.println("最大： " + maxPoint);
		for(int i=0; i<n; i++) {
			System.out.print(solMatching[i]+1 + " ");
		}
	}

	private void bruteForce(int row, int point) {
		if(row == n && point > maxPoint) {
			System.arraycopy(tmpSol, 0, solMatching, 0, n);
			maxPoint = point;
		}
		if(row == n) return;
		for(int i=unused.nextSetBit(0); i>=0; i=unused.nextSetBit(i+1)) {
			unused.clear(i);
			tmpSol[row] = i;
			bruteForce(row+1, point + matrix[row][i]);
			unused.set(i);
		}
	}


}
