import java.util.ArrayList;
import java.util.List;

public class BSTreeToLinkedList {

	/*
	 * 把二元查找树转变成排序的双向链表 题目： 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
	 * 要求不能创建任何新的结点，只调整指针的指向。 10 / \ 6 14 / \ / \ 4 8 12 16 转换成双向链表
	 * 4=6=8=10=12=14=1
	 */
	public static void main(String[] args) {
		BSTreeToLinkedList bn = new BSTreeToLinkedList();
		int[] a = { 10, 6, 14, 4, 8, 12, 16 };// 这些数据是按二叉查找树的层次遍历存放
		Node head = bn.creatTree(a);
		bn.toTwoWayLinkedList(head);
		bn.printTwoLinkedList(head);

	}

	/*
	 * 思路：中序遍历二叉树，将上次访问的节点记为previous，当前访问的节点记为current；
	 * 
	 * 对于遍历过程中的每个当前节点，让该节点的左指针指向previous(current->left = previous)，让previous的右指针
	 * 
	 * 指向当前节点(previous->right = current)，然后将previous更新为current。当中序遍历结束时，二叉搜索树也
	 * 
	 * 被转化为双链表了。
	 * 
	 * 具体思路可参见：http://hi.baidu.com/gropefor/blog/item/d2144f8ce0325105b31bba11.html
	 * 
	 * 问题：这个previous只能作为类成员才能得到正确的结果，作为局部变量的话，我得不到正解。 我尝试过这样写：
	 * toTwoWayLinkedList(Node node,Node previous),在main函数里面调用时候，用
	 * toTwoWayLinkedList(head,null)，得不到正确答案
	 */
	private Node previous;

	public void toTwoWayLinkedList(Node node) {
		if (node != null) {
			toTwoWayLinkedList(node.getLeft());
			if (previous != null) {
				previous.setRight(node);
				node.setLeft(previous);
			}
			previous = node;
			toTwoWayLinkedList(node.getRight());
		}
	}

	public void printTwoLinkedList(Node node) {
		if (node != null) {
			// after converting to List,head=a[0]=10,but the head is not the
			// actually head of list.
			// the true head is 4.
			while (node.getLeft() != null) {
				node = node.getLeft();// find the true Head.
			}
			while (node != null) {
				System.out.print(node.getData() + " ");
				node = node.getRight();
			}
		}
	}

	public Node creatTree(int[] data) {
		List<Node> nodeList = new ArrayList<Node>();
		for (int each : data) {
			Node node = new Node(each);
			nodeList.add(node);
		}
		int lastRootIndex = data.length / 2 - 1;
		for (int i = lastRootIndex; i >= 0; i--) {
			int leftIndex = i * 2 + 1;
			Node root = nodeList.get(i);
			Node left = nodeList.get(leftIndex);
			root.setLeft(left);
			if (leftIndex + 1 < data.length) {
				Node right = nodeList.get(leftIndex + 1);
				root.setRight(right);
			}
		}
		Node head = nodeList.get(0);
		return head;

	}

}

class Node {
	private int data;
	private Node left;
	private Node right;
	public Node(int i) {
		data = i;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
