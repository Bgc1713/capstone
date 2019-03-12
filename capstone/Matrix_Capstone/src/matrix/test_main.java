package matrix;

public class test_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Matrix test_mtx = new Matrix(5,5);
//		System.out.print(test_mtx.toString());
//		System.out.print("\n");
//		test_mtx.setItem(2, 1, 1);
//		test_mtx.setItem(2, 2, 2);
//		test_mtx.setItem(2, 3, 3);
//		test_mtx.setItem(2, 4, 4);
//		test_mtx.setItem(2, 5, 5);
//		System.out.print(test_mtx.toString());
//		System.out.print("\n");
//		//System.out.print(test_mtx.getRow(2));
//		test_mtx.swapRows(2, 5);
//		System.out.print(test_mtx.toString());
//		test_mtx.addToRow(4, 5);
//		System.out.println(test_mtx.toString());
//
//		test_mtx.addToRow(4, 5);
//		System.out.println(test_mtx.toString());
//		test_mtx.subtractFromRow(5, 4);
//		System.out.println(test_mtx.toString());
//		
//		Matrix test_mtx2 = new Matrix(5,5);
//		System.out.println(test_mtx2.toString());
//		test_mtx2.addto(test_mtx);
//		System.out.println(test_mtx2.toString());
		Matrix test_mtx = new Matrix(3, 3);
		test_mtx.setItem(1, 1, 1);
		test_mtx.setItem(1, 2, 2);
		test_mtx.setItem(1, 3, 3);
		test_mtx.setItem(2, 1, 4);
		test_mtx.setItem(2, 2, 5);
		test_mtx.setItem(2, 3, 6);
		test_mtx.setItem(3, 1, 7);
		test_mtx.setItem(3, 2, 8);
		test_mtx.setItem(3, 3, 9);
		Matrix test_mtx2 = new Matrix(3, 3);
		System.out.println("Matrix 1:");
		System.out.println(test_mtx.toString());
		test_mtx2.addTo(test_mtx);
		System.out.println("Matrix 2 after addition:");
		System.out.println(test_mtx2.toString());
		test_mtx2.addTo(test_mtx2);
		System.out.println(test_mtx2.toString());
		
		Matrix additionTest = test_mtx.add(test_mtx2);
		System.out.println("Add matrix: " + additionTest.toString());
		Matrix subtractionTest = test_mtx.subtract(test_mtx2);
		System.out.println("Sub matrix: " + subtractionTest.toString());
		
		test_mtx2.subtractFrom(test_mtx);
		System.out.println("Matrix 2 after subtraction:");
		System.out.println(test_mtx2.toString());
	}


}
