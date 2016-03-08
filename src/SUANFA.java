public class SUANFA {

	static int count = -1;

	public static void main(String[] args) {
		find(0, 0, 1, 4, 0, 10);
		System.out.println(count);
	}

	private static void find(int x, int y, int tx, int ty, int n, int size) {

		if (x > size || x < 0 || y > size || y < 0)
			return;
		else if (x == tx && y == ty) {
			count = n;
			return;
		} else if (count != -1 && n > count || n > 1000) {
			// 递归太深 stackoverflaw 的防止措施
			return;
		} else {
			find(x + 1, y + 2, tx, ty, n + 1, size);
			find(x + 2, y + 1, tx, ty, n + 1, size);
			find(x + 1, y - 2, tx, ty, n + 1, size);
			find(x + 2, y - 1, tx, ty, n + 1, size);
			find(x - 2, y - 1, tx, ty, n + 1, size);
			find(x - 2, y + 1, tx, ty, n + 1, size);
			find(x - 1, y + 2, tx, ty, n + 1, size);
			find(x - 1, y - 2, tx, ty, n + 1, size);
		}
	}
}
