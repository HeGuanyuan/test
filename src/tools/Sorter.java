package tools;

public class Sorter {

	public static void insertSortFromMax(int[] in, int count) {
		for (int i = 1; i < count; i++) {
			int tmp = in[i];
			int j = i;
			//这里比较移动的时候很有技巧，移动操作很省力
			while (j > 0 && tmp > in[j - 1]) {
				in[j] = in[j - 1];
				j--;
			}
			in[j] = tmp;
		}
	}
}