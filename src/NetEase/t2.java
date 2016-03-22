package NetEase;

import java.util.ArrayList;
import java.util.Scanner;

/* 网易的笔试题，9和g容易混淆，列出字符串中9和g的左右组合*/
/* 在于每个节点复制副本下传，不能传递原本*/
public class t2 {
	public static int count = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String code = in.next().trim();
		// System.out.println(code);
		int n9 = 0;
		int ng = 0;
		ArrayList<Integer> g9 = new ArrayList<Integer>();
		if (code.contains("9") || code.contains("g")) {
			for (int i = 0; i < code.length(); i++) {
				if (code.charAt(i) == '9') {
					n9++;
					g9.add(i);
				} else if (code.charAt(i) == 'g') {
					ng++;
					g9.add(i);
				}
			}
			System.out.print("共" + (g9.size()) + "个 :");
			System.out.println(g9);

			char[] codeArray = code.toCharArray();
			toL(0, codeArray, g9);
			toR(0, codeArray, g9);
		}

	}

	public static void toL(int p, char[] in, ArrayList<Integer> g9) {
		in[g9.get(p)] = '9';
		if (p + 1 < g9.size()) {
			toL(p + 1, in, g9);
			toR(p + 1, in, g9);
		} else {
			count++;
			System.out.print(count + ":");
			System.out.println(in);
		}
	}

	public static void toR(int p, char[] in, ArrayList<Integer> g9) {
		char[] a = null, b = null;
		in[g9.get(p)] = 'g';
		if (p + 1 < g9.size()) {
			toL(p + 1, in, g9);
			toR(p + 1, in, g9);
		} else {
			count++;
			System.out.print(count + ":");
			System.out.println(in);
		}
	}
}
