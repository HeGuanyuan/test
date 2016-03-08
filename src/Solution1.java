import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * *算法说明 : 把单词和次数以键值对的形式插入搜索二叉树中，遇到重复的单词则增加键值对中该单词的次数，
 * 同时记录最多的次数和出现次数最多的单词，在插入遇到重复单词时比较记录的最多次数，如果有超过，则更新， 所有单词插入完之后输出出现次数最多的单词和次数。
 */
public class Solution1 {

	public static void main(String[] args) throws Exception {

		// 读取一篇文章中的单词作为测试用例
		BufferedReader reader = new BufferedReader(new FileReader("P:\\for_test_s1.txt"));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		Pattern expression = Pattern.compile("[a-zA-Z]+");// 定义正则表达式匹配单词
		String string = buffer.toString();

		Matcher matcher = expression.matcher(string);
		int c = 0;
		while (matcher.find()) {
			c++;
		}
		// 测试用例data
		String[] data = new String[c];
		matcher = expression.matcher(string);
		c = 0;
		while (matcher.find()) {
			data[c++] = matcher.group();
		}

		find(data);

	}

	public static void find(String[] data) {

		if (data == null)
			return;
		BSTree tree = new BSTree();
		for (int i = 0; i < data.length; i++) {
			// 将每个单词插入二叉树中，在二叉树的插入中处理单词重复时的计数
			tree.insert(new Pair(data[i]));
		}

		// 输出出现次数最多的单词
		 System.out.println(tree.out + " : " + tree.count);
		tree.print();
	}

	// 键值对 类
	static class Pair {
		String key;
		int value;
		private int de = 'A' - 'a';

		// key的比较方法，不区分大小写
		public int compareTo(Pair p) {
			char[] aa = this.key.toCharArray();
			char[] bb = p.key.toCharArray();
			int l = aa.length > bb.length ? bb.length : aa.length;
			for (int i = 0; i < l; i++) {
				char al = (char) (aa[i] >= 'A' ? (aa[i] - de) : aa[i]);
				char bl = (char) (bb[i] >= 'A' ? (bb[i] - de) : bb[i]);
				if (al > bl) {
					return 1;
				} else if (al < bl) {
					return -1;
				}
			}
			return aa.length - bb.length;
		}

		public Pair(String k) {
			this.key = k;
			this.value = 1;
		}
	}

	// 搜索二叉树
	static class BSTree {

		// 最多的出现次数
		int count = 0;
		// 出现次数最多的单词
		String out = new String("");

		// 二叉树节点类
		private class BinaryNode {
			Pair pair;
			BinaryNode left;
			BinaryNode right;

			BinaryNode(Pair theItem, BinaryNode l, BinaryNode r) {

				this.pair = theItem;
				this.left = l;
				this.right = r;
			}
		}

		// 根节点
		private BinaryNode root;

		public BSTree() {
			root = null;
		}

		// 二叉树的 递归插入 操作
		private BinaryNode insert(Pair x, BinaryNode p) {

			// 要插入的父节点为空
			if (p == null)
				return new BinaryNode(x, null, null);

			int comapreResult = x.compareTo(p.pair);

			if (comapreResult < 0)
				// 小于父节点插入左子树
				p.left = insert(x, p.left);
			else if (comapreResult > 0)
				// 大于父节点插入右子树
				p.right = insert(x, p.right);
			else {
				// 相等则增加该单词的计数
				++p.pair.value;

				if (p.pair.value > count) {
					// 更新出现次数最多的单词
					count = p.pair.value;
					out = p.pair.key;
				}
			}
			// 返回父节点
			return p;
		}

		public void insert(Pair x) {
			root = insert(x, root);
		}

		public void print() {
			if (this.root == null)
				System.out.println("Empty tree");
			else
				print(0, root);
		}

		private void print(int depth, BinaryNode n) {
			if (n != null) {
				int de = depth + 1;
				String num = String.format("%4d ", de);
				System.out.format("%s", num + depth(depth) + n.pair.key + " : " + n.pair.value + "\n");
				print(de, n.left);
				print(de, n.right);
			}
		}

		private String depth(int d) {
			String s = "";
			for (int i = 0; i < d; i++) {
				s += "| ";
			}
			return s;
		}

	}
}
