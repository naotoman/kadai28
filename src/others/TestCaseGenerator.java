package others;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestCaseGenerator {

	private static final int N = 10;
	private static final int MAX_W = 10;//inclusive

	private static final String FILE_NAME = "input/sample28.txt";

	public static void main(String[] args) {
		File file = new File(FILE_NAME);
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			pw.println(N);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					int w = (int)(Math.random() * (MAX_W+1));
					if(j > 0) pw.print(" ");
					pw.print(w);
				}
				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
