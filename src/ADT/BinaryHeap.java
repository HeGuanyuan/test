package ADT;

/**
 * @Class 最小堆
 * @Author He Guanyuan 2015-9-19 下午4:23:00
 */
public class BinaryHeap<T extends Comparable<? super T>> {

	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (T[]) new Comparable[capacity + 1];
	}

	@SuppressWarnings("unchecked")
	public BinaryHeap(T[] items) {
		currentSize = items.length;
		array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
		int i = 1;
		for (T x : items)
			array[i++] = x;
		buildHeap();
	}

	public void insert(T x) {
		if (currentSize == array.length - 1) {
			enlargeArray(array.length * 2 - 1);
		}

		int hole = ++currentSize;
		for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	public T findMin() {
		if (!isEmpty())
			return array[1];
		return null;
	}

	public T deleteMin() {
		if (!isEmpty()) {
			T minItem = findMin();
			/** 好巧妙 array[1] = array[currentSize--]; */
			array[1] = array[currentSize--];
			percolateDown(1);
			return minItem;
		}
		return null;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public void makeEmpty() {
		currentSize = 0;
	}

	public void print() {
		if (!isEmpty())
			print(0, 1);
	}

	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize;
	private T[] array;

	private void percolateDown(int hole) {
		int child;
		T tmp = array[hole];
		// 保证child不越界，一直往下
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			// 如果右子比较小，从右子子树开始
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
				child++;
			if (array[child].compareTo(tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);

	}

	private void enlargeArray(int newSize) {
		T[] old = array;
		array = (T[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++) {
			array[i] = old[i];
		}
	}

	private void print(int depth, int index) {
		if (index <= currentSize) {
			int de = depth + 1;
			String num = String.format("%4d ", de);
			System.out.format("%s", num + depth(de) + array[index] + "\n");
			print(de, index * 2);
			print(de, index * 2 + 1);
		}
	}

	private String depth(int d) {
		String s = "";
		for (int i = 0; i < d-1; i++) {
			s += "| ";
		}
		return s;
	}
}
