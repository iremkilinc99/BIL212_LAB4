
public interface SimpleBST<E extends Comparable<E>> {
	int size();
	boolean isEmpty();
	boolean contains(E element);
	void insert(E element);
	String toStringInOrder();
	String toStringPreOrder();
}
