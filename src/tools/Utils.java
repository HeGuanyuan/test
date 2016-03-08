package tools;

import java.util.Random;

public class Utils {

	public static int[] getRandomIntArray(int count, int max) {
		int[] out = new int[count];
		Random random = new Random();
		for (int i = 0; i < out.length; i++) {
			out[i] = random.nextInt(max);
		}
		return out;
	}

	public static void printArray(int[] in) {
		for (int i = 0; i < in.length; i++) {
			System.out.format("%4d : %d\n", i, in[i]);
		}
	}
}