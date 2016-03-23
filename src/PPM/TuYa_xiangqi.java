package PPM;

import java.util.HashMap;

/* */

/* 性能不佳，棋盘比较大时无法快速完成*/
public class TuYa_xiangqi {

	static HashMap<String, Integer> total = new HashMap<String, Integer>();
	static HashMap<String, Integer> last = new HashMap<String, Integer>();
	static HashMap<String, Integer> temp = new HashMap<String, Integer>();
	static int w = 20, h = 20;
	static int fromX = 12, fromY = 18;
	static int toX = 1, toY = 5;
	static int p = 1;
	static boolean got = false;

	public static void main(String[] args) {
		int count = getStep(3, 3, 4, 5);
		System.out.println(count);
	}

	/* 棋盘大小下限制在20*20 */
	public static int getStep(int fx, int fY, int tX, int tY) {
		fromX = fx;
		fromY = fY;
		toX = tX;
		toY = tY;
		total.put(fromX + "," + fromY, 0);
		last.put(fromX + "," + fromY, 0);

		while (!got) {
			for (String s : last.keySet()) {
				int x = Integer.valueOf(s.split(",")[0]);
				int y = Integer.valueOf(s.split(",")[1]);
				go(x + 1, y + 2);
				go(x + 2, y + 1);
				go(x + 1, y - 1);
				go(x + 1, y - 2);

				go(x - 1, y - 2);
				go(x - 2, y - 1);
				go(x - 2, y + 1);
				go(x - 1, y + 2);
				if (got)
					break;
			}
			p++;
			last = temp;
			temp = new HashMap<String, Integer>();
		}
		return p - 1;
	}

	public static void go(int x, int y) {
		if (x <= w && y <= h) {

			if (!total.containsValue(x + "," + y)) {
				if (!last.containsKey(x + "," + y)) {
					total.put(x + "," + y, p);
					temp.put(x + "," + y, p);
					if (x == toX && y == toY)
						got = true;
				}
			}
		}
	}
}
