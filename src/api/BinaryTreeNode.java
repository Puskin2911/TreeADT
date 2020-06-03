package api;

public class BinaryTreeNode<E> {
	private E element;
	private BinaryTreeNode<E> parent;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}

	public BinaryTreeNode(E element) {
		this.element = element;
		this.parent = null;
		this.left = null;
		this.right = null;
	}
	
	private BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
		this.element = element;
		this.parent = parent;
	}
	
	public BinaryTreeNode<E> root(){
		return this;
	}
	
	public void set(BinaryTreeNode<E> p, E element) {
		if (p != null) {
			p.element = element;
		}
	}
	
	public BinaryTreeNode<E> addLeft(BinaryTreeNode<E> p, E element) {
		if(p != null) {
			if(p.left != null) {
				set(p.left, element);
			}
			else {
				p.left = new BinaryTreeNode<E>(element, p);
			}
			return p.left;
		}
		return null;
	}
	
	public BinaryTreeNode<E> addRight(BinaryTreeNode<E> p, E element) {
		if(p != null) {
			if(p.right != null) {
				set(p.right, element);
			}
			else {
				p.right = new BinaryTreeNode<E>(element, p);
			}
			return p.right;
		}
		return null;
	}
	
	private int allChild(BinaryTreeNode<E> p) {
		if (p != null) {
			return 1 + allChild(p.left) + allChild(p.right);
		}
		return 0;
	}
	
	public int size() {
		return allChild(this);
	}
	
	public boolean isEmpty() {
		return this == null;
	}
	
	public int numChildren(BinaryTreeNode<E> p) {
		int numChild = 0;
		if(p != null) {
			if(p.left != null) numChild++;
			if(p.right != null) numChild++;
		}
		
		return numChild;
	}
	
	public BinaryTreeNode<E> parent(BinaryTreeNode<E> p){
		if(p != null) {
			return p.parent;
		}
		return null;
	}
	
	public BinaryTreeNode<E> left(BinaryTreeNode<E> p){
		if(p != null) {
			return p.left;
		}
		return null;
	}
	
	public BinaryTreeNode<E> right(BinaryTreeNode<E> p){
		if(p != null) {
			return p.right;
		}
		return null;
	}
	
	public BinaryTreeNode<E> sibling(BinaryTreeNode<E> p){
		if(p != null) {
			BinaryTreeNode<E> parent = p.parent;
			if(parent != null) {
				if(p == parent.left) return parent.right;
				if(p == parent.right) return parent.left;
			}
		}
		return null;
	}
	
	private void inorderTravel(BinaryTreeNode<E> p) {
		if(p != null) {
			inorderTravel(p.left);
			
			System.out.print(p.element + " ");
			
			inorderTravel(p.right);
		}
	}
	
	public void inorderPrint() {
		inorderTravel(this);
	}
	
	private void travel(BinaryTreeNode<E> p, String space) {
		if(p != null) {
			space += "\t";
			travel(p.right, space);
			
			System.out.print(space.substring(1));
			System.out.println(p.element);
			
			travel(p.left, space);
			
		}
	}
	
	public void printTree() {
		travel(this, "");
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> tree = new BinaryTreeNode<Integer>(1);
		tree.addLeft(tree, 2);
		tree.addRight(tree, 3);
		tree.addLeft(tree.left(tree), 4);
		tree.addRight(tree.left(tree), 5);
		tree.addLeft(tree.right(tree), 6);
		tree.addRight(tree.right(tree), 7);
		
		tree.printTree();
//		System.out.println(tree.size());
	}
}
