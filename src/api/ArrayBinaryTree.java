package api;

import java.util.Random;

public class ArrayBinaryTree<E, T extends Integer> implements BinaryTreeInterface<T> {
	private E[] array;
	private int n = 0;
	private int defaultsize = 100;

	@SuppressWarnings("unchecked")
	public ArrayBinaryTree() {

		array = (E[]) new Object[defaultsize];
	}

	public void setRoot(E element) {
		if (array[0] == null) {
			n++;
		}
		array[0] = element;
	}

	public void setLeft(int p, E element) {
		if (p >= 0 && p < array.length && array[p] != null) {
			int index = p * 2 + 1;
			if (index < array.length) {
				if (array[index] == null) {
					n++;
				}
				array[index] = element;
			} else {
				throw new IllegalStateException("Tree is full");
			}
		} else {
			throw new IllegalArgumentException("p is not valid");
		}
	}

	public void setRight(int p, E element) {
		if (p >= 0 && p < array.length && array[p] != null) {
			int index = p * 2 + 2;
			if (index < array.length) {
				if (array[index] == null) {
					n++;
				}
				array[index] = element;
			} else {
				throw new IllegalStateException("Tree is full");
			}
		} else {
			throw new IllegalArgumentException("p is not valid");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T root() {
		return (T) Integer.valueOf(0);
	}

	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public int numChildren(T p) {
		int numChild = 0;
		int current = (int) p;
		if (current >= 0 && current < array.length && array[current] != null) {
			int leftChild = 2 * current + 1;
			int rightChild = 2 * current + 2;

			if (leftChild < array.length && array[leftChild] != null) {
				numChild++;
			}
			if (rightChild < array.length && array[rightChild] != null) {
				numChild++;
			}
			return numChild;
		} else {
			throw new IllegalArgumentException("p is not valid");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T parent(T p) {
		int current = (int) p;
		int parent = (current - 1) / 2;
		if (current >= 0 && current < array.length && array[current] != null && parent >= 0 && array[parent] != null) {
			return (T) Integer.valueOf(parent);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T left(T p) {
		int current = (int) p;
		int leftChild = 2 * current + 1;
		if (current >= 0 && current < array.length && array[current] != null && leftChild < array.length
				&& array[leftChild] != null) {
			return (T) Integer.valueOf(leftChild);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T right(T p) {
		int current = (int) p;
		int rightChild = 2 * current + 2;
		if (current >= 0 && current < array.length && array[current] != null && rightChild < array.length
				&& array[rightChild] != null) {
			return (T) Integer.valueOf(rightChild);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T sibling(T p) {
		int current = (int) p;
		if (current >= 0 && current < array.length && array[current] != null) {
			// get Left
			if (current % 2 == 0 && current - 1 >= 0 && array[current - 1] != null) {
				return (T) Integer.valueOf(current - 1);
			}
			// get Right
			if (current % 2 == 1 && current + 1 < array.length && array[current + 1] != null) {
				return (T) Integer.valueOf(current + 1);
			}
		}
		return null;
	}

	public E getElement(int index) {
		return array[index];
	}

	private void travel(int p, String space) {
		if (p >= 0 && p < array.length && array[p] != null) {

			space += "\t";

			int rightChild = 2 * p + 2;
			if (rightChild < array.length && array[rightChild] != null) {
				travel(rightChild, space);
			}
			System.out.print(space.substring(1));
			System.out.println(array[p]);

			int leftChild = 2 * p + 1;
			if (leftChild < array.length && array[leftChild] != null) {
				travel(leftChild, space);
			}
		}
	}

	public void printTree() {
		travel(0, "");
	}

	public static void main(String[] args) {
		ArrayBinaryTree<Integer, Integer> tree = new ArrayBinaryTree<Integer, Integer>();

		tree.setRoot(0);

		Random random = new Random();
		for (int i = 0; i < 12; i++) {
			tree.setLeft(i, random.nextInt(100));
			tree.setRight(i, random.nextInt(100));
		}

		System.out.println(tree.left(1));

		tree.printTree();
	}

}