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
		test_mtx.setItem(1, 2, -3);
		test_mtx.setItem(1, 3, 0);
		test_mtx.setItem(2, 1, 0);
		test_mtx.setItem(2, 2, 2);
		test_mtx.setItem(2, 3, 6);
		test_mtx.setItem(3, 1, 2);
		test_mtx.setItem(3, 2, -10);
		test_mtx.setItem(3, 3, 2);
		Matrix L = test_mtx.getLDecomposition();
		Matrix U = test_mtx.getUDecomposition();
		System.out.println(L.toString());
		System.out.println(U.toString());

//		System.out.println("The inversion:");
//		System.out.println(test_mtx.toString());
//		System.out.println(inversion.toString());
//		Matrix test_mtx2 = new Matrix(3, 3);
//		System.out.println("Matrix 1:");
//		System.out.println(test_mtx.toString());
//		//test_mtx2.addTo(test_mtx);
//		System.out.println("Matrix 2 after addition:");
//		System.out.println(test_mtx2.toString());
//		//test_mtx2.addTo(test_mtx2);
//		System.out.println(test_mtx2.toString());
//		
//		Matrix additionTest = Mtxops.add(test_mtx, test_mtx2);
//		System.out.println("Add matrix: " + additionTest.toString());
//		Matrix subtractionTest = Mtxops.subtract(test_mtx, test_mtx);
//		System.out.println("Sub matrix: " + subtractionTest.toString());
//		
//		//test_mtx2.subtractFrom(test_mtx);
//		test_mtx2 = Mtxops.subtract(test_mtx2, test_mtx);
//		test_mtx2 = Mtxops.subtract(test_mtx2, test_mtx);
//		System.out.println("Matrix 2 after subtraction:");
//		System.out.println(test_mtx2.toString());
//		
//		Matrix multtest = Mtxops.multiply(test_mtx, 4.24);
//		System.out.println("Multmtx after mult: ");
//		System.out.println(multtest.toString());
//		
		Matrix A = new Matrix(2, 3);
		A.setItem(1, 1, 1);
		A.setItem(1, 2, 2);
		A.setItem(1, 3, 3);
		A.setItem(2, 1, 4);
		A.setItem(2, 2, 2);
		A.setItem(2, 3, 1);
		Matrix B = new Matrix(3, 4);

		B.setItem(1, 1, 2);
		B.setItem(1, 2, 2);
		B.setItem(1, 3, 4);
		B.setItem(1, 4, 12);
		B.setItem(2, 1, 1);
		B.setItem(2, 2, 1);
		B.setItem(2, 3, 1);
		B.setItem(2, 4, 8);
		B.setItem(3, 1, 3);
		B.setItem(3, 2, 3);
		B.setItem(3, 3, 2);
		B.setItem(3, 4, 19);
		Matrix C = new Matrix(B.getRowEchelonForm());
		Matrix E = new Matrix(3,6);
		E.setItem(1, 1, 0);
		E.setItem(1, 2, 4);
		E.setItem(1, 3, 2);
		E.setItem(1, 4, 1);
		E.setItem(1, 5, 0);
		E.setItem(1, 6, 0);
		
		E.setItem(2, 1, 1);
		E.setItem(2, 2, -2);
		E.setItem(2, 3, 0);
		E.setItem(2, 4, 0);
		E.setItem(2, 5, 1);
		E.setItem(2, 6, 0);

		E.setItem(3, 1, 2);
		E.setItem(3, 2, -1);
		E.setItem(3, 3, 1);
		E.setItem(3, 4, 0);
		E.setItem(3, 5, 0);
		E.setItem(3, 6, 1);
		
		E.reduceToReducedRowEchelonForm();
		System.out.println(E.toString());
//		Matrix C = A.dotProduct(B);
//		System.out.println(C.toString());
//		
//		Matrix D = C.multiply(C, 2);
//		System.out.println(D.toString());
//		
//		D.multiplyBy(2);
//		System.out.println(D.toString());
		B.reduceToRowEchelonForm();
		System.out.println(B.toString());
		System.out.println(C.toString());
		B.reduceToRowEchelonForm();
		System.out.println(B.toString());
//		Matrix elimd = Eliminations.rowEchelonForm(B);
//		System.out.println(elimd.toString());
//		Matrix aug = new Matrix(B, elimd);
//		System.out.println(aug.toString());
//		System.out.println(A.toString());
//		System.out.println(B.toString());
//		Matrix C = Mtxops.multiply(A, B);
//		System.out.println(C.toString());
//		
//		Matrix D = new Matrix(3, 2);
//		D.setItem(1, 1, 3);
//		D.setItem(1, 2, 2);
//		D.setItem(2, 1, 4);
//		D.setItem(2, 2, 1);
//		D.setItem(3, 1, 5);
//		D.setItem(3, 2, 6);
//		System.out.println(D.toString());
//		Matrix E = new Matrix(D);
//		E.setItem(1, 1, 0);
//		E.setItem(1, 2, 1);
//		E.setItem(2, 1, 3);
//		E.setItem(2, 2, 7);
//		System.out.println(E.toString());
//		Matrix reduced = Eliminations.rowEchelonForm(E);
//		Matrix F = Mtxops.multiply(D, E);
//		System.out.println(reduced.toString());
		
		
	}


}
