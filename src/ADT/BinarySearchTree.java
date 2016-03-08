package ADT;

import java.util.Comparator;

public class BinarySearchTree<T extends Comparable<? super T>> {
	/**
	 * 一般情况下是不可以用static修饰类的。 如果一定要用static修饰类的话，通常static修饰的是匿名内部类。
	 */
	private static class BinaryNode<T> {
		T item;
		BinaryNode<T> left;
		BinaryNode<T> right;

		BinaryNode(T theItem, BinaryNode<T> l, BinaryNode<T> r) {

			this.item = theItem;
			this.left = l;
			this.right = r;
		}

		BinaryNode() {
		}

		BinaryNode(T t) {
			this.item = t;
		}
	}

	public BinarySearchTree(Comparator<? super T> cam) {
		root = null;
		this.cmp = cam;
	}

	private int myCompare(T lhs, T rhs) {
		if (cmp != null) {
			return cmp.compare(lhs, rhs);
		} else
			return ((Comparable) lhs).compareTo(rhs);
	}

	public BinarySearchTree() {
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public boolean contains(T x) {
		return contains(x, root);
	}

	public T findMin() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		return findMin(root).item;
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	public void print() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			print(0, root);
	}

	public T findMax() throws Exception {
		if (isEmpty()) {
			throw new Exception();
		}
		return findMax(root).item;
	}

	/************************************** toLinkedArray *******************************/
	private BinaryNode<T> previous;

	public BinaryNode<T> toLinkedArray() {
		toLinkedArray(root);
		printLinkedArray(root);
		return null;
	}

	private void toLinkedArray(BinaryNode<T> node) {
		if (node != null) {
			toLinkedArray(node.left);
			if (previous != null) {
				previous.right = node;
				node.left = previous;
			}
			previous = node;
			toLinkedArray(node.right);
		}
	}

	public void printLinkedArray(BinaryNode<T> node) {
		if (node != null) {
			// after converting to List,head=a[0]=10,but the head is not the
			// actually head of list.
			// the true head is 4.
			while (node.left != null) {
				node = node.left;// find the true Head.
			}
			while (node != null) {
				System.out.println(node.item + " ");
				node = node.right;
			}
		}
	}

	private BinaryNode<T> root;
	private Comparator<? super T> cmp;

	private boolean contains(T x, BinaryNode<T> t) {
		if (t == null) {
			return false;
		}

		int compareResult = x.compareTo(t.item);

		if (compareResult < 0) {
			return contains(x, t.left);
		} else if (compareResult > 0) {
			return contains(x, t.right);
		} else
			return true;
	}

	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null)
			return null;
		if (t.left == null)
			return t;
		return findMin(t.left);
	}

	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t != null) {
			while (t.right != null)
				t = t.right;
		}
		return t;
	}

	private BinaryNode<T> insert(T x, BinaryNode<T> t) {
		// 带return的嵌套有意思
		if (t == null)
			return new BinaryNode<T>(x, null, null);

		int comapreResult = x.compareTo(t.item);

		if (comapreResult < 0)
			t.left = insert(x, t.left);
		else if (comapreResult > 0)
			t.right = insert(x, t.right);
		else
			;
		return t;
	}

	private BinaryNode<T> remove(T x, BinaryNode<T> t) {

		if (t == null)
			return t;
		int cmp = x.compareTo(t.item);

		if (cmp < 0) {
			t.left = remove(x, t.left);
		} else if (cmp > 0) {
			t.right = remove(x, t.right);
		} else if (t.left != null && t.right != null) {
			t.item = findMin(t.right).item;

			t.right = remove(t.item, t.right);
		} else {
			t = (t.left != null) ? t.left : t.right;
		}
		return t;
	}

	private void print(int depth, BinaryNode<T> n) {
		if (n != null) {
			int de = depth + 1;
			String num = String.format("%4d ", de);
			System.out.format("%s", num + depth(depth) + n.item + "\n");
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
