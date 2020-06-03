package api;

public class LinkedBinaryTree<E, T> implements BinaryTreeInterface<T> {
	protected class Node {
		protected E element; // an element stored at this node
		protected Node parent; // a reference to the parent node (if any)
		protected Node left; // a reference to the left child
		protected Node right; // a reference to the right child

		public Node(E e, Node above, Node leftChild, Node rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}
	}

	private Node root = null;

	public Node addRoot(E element) {
		root = new Node(element, null, null, null);
		return root;
	}

	public Node addLeft(Node p, E element) {
		if (p != null) {
			if (p.left == null) {
				p.left = new Node(element, p, null, null);
			} else {
				set(p.left, element);
			}
			return p.left;
		}
		return null;
	}

	public Node addRight(Node p, E element) {
		if (p != null) {
			if (p.right == null) {
				p.right = new Node(element, p, null, null);
			} else {
				set(p.right, element);
			}
			return p.right;
		}
		return null;
	}

	public void set(Node p, E element) {
		if (p != null) {
			p.element = element;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T root() {
		if (root != null) {
			return (T) root;
		}
		return null;
	}

	private int allChild(Node p) {
		if (p != null) {
			return 1 + allChild(p.left) + allChild(p.right);
		}
		return 0;
	}

	@Override
	public int size() {
		return allChild(root);
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int numChildren(T p) {
		@SuppressWarnings("unchecked")
		Node current = (Node) p;
		int numChild = 0;
		if (current != null) {
			if (current.left != null) {
				numChild++;
			}
			if (current.right != null) {
				numChild++;
			}
		}
		return numChild;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T parent(T p) {
		Node current = (Node) p;

		if (current != null) {
			return (T) current.parent;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T left(T p) {
		if (p != null) {
			Node current = (Node) p;
			return (T) current.left;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T right(T p) {
		if (p != null) {
			Node current = (Node) p;
			return (T) current.right;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T sibling(T p) {
		if (p != null) {
			Node current = (Node) p;

			Node parent = (Node) current.parent;
			if (parent != null) {
				Node leftChild = (Node) parent.left;
				Node rightChild = (Node) parent.right;

				if (current == leftChild) {
					return (T) rightChild;
				} else if (current == rightChild) {
					return (T) leftChild;
				}
			}
		}
		return null;
	}

	private void travel(Node p, String space) {
		if (p != null) {
			space += "\t";
			travel(p.right, space);

			System.out.print(space.substring(1));
			System.out.println(p.element);

			travel(p.left, space);
		}
	}

	public void printTree() {
		travel(root, "");
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		LinkedBinaryTree<Integer, LinkedBinaryTree.Node> tree = new LinkedBinaryTree<Integer, LinkedBinaryTree.Node>();
		tree.addRoot(0);
		tree.addLeft(tree.root(), 1);
		tree.addRight(tree.root(), 2);
		tree.addLeft(tree.left(tree.root()), 3);
		tree.addRight(tree.left(tree.root()), 4);
		tree.addLeft(tree.right(tree.root()), 5);
		tree.addRight(tree.right(tree.root()), 6);
		
		tree.printTree();
	}
}
