/**
 * **算法说明： 1.遍历链表获得尾节点和偶数数目 2.再次遍历链表，把偶数依次移动到尾节点后面
 * （2）（如果偶数和技术数目相差较大的话可以偶数多就把奇数移动到前面，奇数多就把偶数移动到后面）（快要提交的时候才想起来） 3.返回链表第一个节点
 */
public class Solution2 {

	public static void main(String[] args) {

		// 产生测试用例
		Node last = null;
		Node root = null;
		int[] in = new int[] { 0, 0, 19, 2, 4, 0, 0, 7, 10, 18, 20, 26, 30, 1, 3, 5, 7, 9, 11, 31, 15, 17 };
		for (int i = 0; i < in.length; i++) {
			Node n = new Node();
			n.value = in[i];
			if (last == null) {
				last = n;
				root = n;
			} else {
				last.next = n;
			}
			last = n;
		}

		root = swap(root);

		// 打印结果
		Node head = root;
		while (head != null) {
			System.out.println(head.value);
			head = head.next;
		}
	}

	static class Node {
		public Node next;
		public int value;
	}

	public static Node swap(Node list) {
		if (list == null)
			return null;
		// 指针
		Node p = list;
		// 返回链表头
		Node head = null;

		// 链表中偶数个数
		int dCcount = 0;

		// 奇数
		int sCount = 0;

		// 链表尾端
		Node end = null;

		// 遍历找到尾端和偶数个数
		while (p != null) {
			end = p;
			if (p.value % 2 == 0)
				++dCcount;
			else
				++sCount;
			p = p.next;
		}
		p = list;

		// 遍历过程中的上一个奇数节点
		Node last = null;
		while (p != null && dCcount != 0) {
			if (p.value % 2 == 0) {
				Node tmp = p;

				// 指针后移
				p = p.next;

				// 将偶数节点移动到尾端
				end.next = tmp;
				end = tmp;
				end.next = null;

				// 如果之前有奇数，将下一个节点和之前的奇数节点接起来
				if (last != null)
					last.next = p;

				// 偶数个数减一
				--dCcount;
			} else {
				// 更新最后一个奇数
				last = p;

				// 指针后移
				p = p.next;

				if (head == null)
					// 将第一个奇数节点作为链表头
					head = last;
			}
		}

		// 返回链表头
		return head;

	}
}
