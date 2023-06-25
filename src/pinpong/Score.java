package pinpong;

import java.io.*;
import java.util.*;

public class Score {

	public static void guardarScore(File f, int score) {

		try {
			FileWriter fw = new FileWriter(f, true);
			PrintWriter syso = new PrintWriter(fw);
			syso.println(score);
			syso.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int nuevoScore(File f) {
		int maxScore = 0;
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				maxScore = Math.max(maxScore, sc.nextInt());
			}
		} catch (Exception e) {
			System.out.println("Error de lectura");
		}
		return maxScore;
	}

}
