import java.util.ArrayList;


public class Driver {

	public static int sum(BinaryTree<Integer> b) {
		LinkedBinaryTree<Integer> fakeB = (LinkedBinaryTree<Integer>) b;
		if (fakeB.isEmpty())
			return 0;
		for (Position<Integer> c : fakeB.positions()) {
			int temp = c.getElement();
			fakeB.remove(c);
			return temp + sum(fakeB);
		}
		return 0;
	}
   
	public static void main(String... strings) {
		LinkedBinaryTree<Integer> lbl = new LinkedBinaryTree<>();

		lbl.addRoot(-4);
		lbl.addLeft(lbl.root(), -7);
		lbl.addRight(lbl.root.getLeft(), -9);
		lbl.addRight(lbl.root(),-10 );


	}
}
