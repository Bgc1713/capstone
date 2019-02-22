package matrix;

public class test_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Matrix test_mtx = new Matrix(5,5);
		System.out.print(test_mtx.toString());
		System.out.print("\n");
		test_mtx.setItem(2, 1, 1);
		test_mtx.setItem(2, 2, 2);
		test_mtx.setItem(2, 3, 3);
		test_mtx.setItem(2, 4, 4);
		test_mtx.setItem(2, 5, 5);
		System.out.print(test_mtx.toString());
		System.out.print("\n");
		//System.out.print(test_mtx.getRow(2));
		test_mtx.swapRows(2, 5);
		System.out.print(test_mtx.toString());
		test_mtx.addToRow(4, 5);
		System.out.println(test_mtx.toString());

		test_mtx.addToRow(4, 5);
		System.out.println(test_mtx.toString());
		test_mtx.subtractFromRow(5, 4);
		System.out.println(test_mtx.toString());
	}


}
