import java.util.ArrayList;
import java.util.List;

public class LinkedBST<E> implements SimpleBST {

	protected static class Node<E> implements Position<E> {
		private E element; // an element stored at this node
		private Node<E> parent; // a reference to the parent node (if any)
		private Node<E> left; // a reference to the left child (if any)
		private Node<E> right; // a reference to the right child (if any)

		public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			element = e;
			parent = above;
			left = leftChild;
			right = rightChild;
		}

	
		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public Node<E> getRight() {
			return right;
		}

		// update methods
		public void setElement(E e) {
			element = e;
		}

		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}

		public void setLeft(Node<E> leftChild) {
			left = leftChild;
		}

		public void setRight(Node<E> rightChild) {
			right = rightChild;
		}

	}

	private Node<E> root;
	private int size;

	public LinkedBST(E element) {
		Node<E> node = new Node<>(element, null, null, null);
		root = node;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root.getLeft() == null && root.getRight() == null;
	}

	@Override
	public boolean contains(Comparable element) {
		System.out.println(element);
		for (Position<E> t: this.inorder()) {
			System.out.println(t);
			if (t.getElement().equals(element))
				return false;
		}
		return true;
	}

	@Override
	public void insert(Comparable element) {
		if (isEmpty()) {
			root.setElement((E) element);
			this.addLeft(root(), null);
			this.addRight(root, null);
		} else {
			this.insert(element, this.root());
		}
	}

	@Override
	public String toStringInOrder() {
		for (Position<E> a : inorder())
			return a.getElement() + " ";
		return null;
	}

	@Override
	public String toStringPreOrder() {
		for (Position<E> a : preorder())
			return a.getElement() + " ";

		return null;
	}

	public boolean insert(Comparable e, Position<E> p) {
			if (!contains(e)) {
			if (right(p) == null && left(p) == null) {
				Node<E> ourNode = (Node<E>) p;
				ourNode.setElement((E) e);
				this.addLeft(p, null);
				this.addRight(p, null);
			}
			if (e.compareTo(p.getElement()) < 0)
				return insert(e, this.left(p));
			if (e.compareTo(p.getElement()) > 0)
				return insert(e, this.right(p));
		}else {
			return false;
		}
		return false;
	}

	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	public Position<E> root() {
		return root;
	}

	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getLeft();
	}

	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getRight();
	}

	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getLeft() != null)
			throw new IllegalArgumentException("p already has a left child");
		Node<E> child = new Node(e, parent, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}

	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getRight() != null)
			throw new IllegalArgumentException("p already has a right child");
		Node<E> child = new Node(e, parent, null, null);
		parent.setRight(child);
		size++;
		return child;
	}

	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> snapshot = new ArrayList<>(2); // max capacity of 2
		if (left(p) != null)
			snapshot.add(left(p));
		if (right(p) != null)
			snapshot.add(right(p));
		return snapshot;
	}

	public Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			preorderSubtree(root(), snapshot); // fill the snapshot recursively
		return snapshot;
	}

	private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		snapshot.add(p); // for preorder, we add position p before exploring subtrees
		for (Position<E> c : children(p))
			preorderSubtree(c, snapshot);
	}

	public Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if (!isEmpty())
			inorderSubtree(root(), snapshot); // fill the snapshot recursively
		return snapshot;
	}

	private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
		if (left(p) != null)
			inorderSubtree(left(p), snapshot);
		snapshot.add(p);
		if (right(p) != null)
			inorderSubtree(right(p), snapshot);
	}

	public static void main(String...strings) {
		LinkedBST bst =new LinkedBST(4);
		bst.insert(8);
		bst.insert(15);
		bst.insert(8);
		bst.insert(20);
		bst.insert(0);
		bst.insert(112);
		bst.size();
		bst.contains(0);
		bst.inorder();
		bst.preorder();
		}
}
