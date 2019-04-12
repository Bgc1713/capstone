package matrix;

/* A class that provides static methods for performing matrix operations, similar to the default Math class
 * While using static methods does not follow OOP conventions, it provides a way for more procedurally oriented people
 * to work with my class in a friendlier and more intuitive way. It takes little work to create a class like this anyways,
 * as the methods basically act as a wrapper for calling the real methods in the Matrix class.
 */

public class Mtxops {

	/** Addition of two matrixes (A + B = C)
	 * @param addend1 The first addend, Matrix A.
	 * @param addend2 The second addend, Matrix B.
	 * @return The sum of the two addends, Matrix C.
	 */
	public static Matrix add (Matrix addend1, Matrix addend2)
	{
		return addend1.add(addend2);
	}
	
	
	/** Subtraction of two matrixes (A - B = C)
	 * Another clever concept: Using addition to copy a matrix to a matrix of zeroes. Since the new matrix is 
	 * already initialized on its own and filled with zeroes, using addition to copy is a way to sufficiently deep copy.
	 * @param minuend The first matrix to subtract from, Matrix A.
	 * @param subtrahend The second matrix to use for subtraction, Matrix B.
	 * @return The result matrix, Matrix C.
	 */
	public static Matrix subtract(Matrix minuend, Matrix subtrahend)
	{
		return minuend.subtract(subtrahend);
	}
	
	/** Multiplication of one matrix by a scalar (A * b = C)
	 * @param factor the Matrix to be multiplied, Matrix A.
	 * @param scalar the scalar constant to multiply with, scalar b.
	 * @return the product Matrix, Matrix C.
	 */
	public static Matrix multiply(Matrix factor, double scalar)
	{
		return factor.multiply(scalar);
	}
	
	/** Multiplication of two matrixes, A * B = AB
	 * @param multiplier The first matrix multiplier, Matrix A.
	 * @param multiplicand The second matrix multiplicand, Matrix B.
	 * @return the product matrix, Matrix AB. (Will have A's rows and B's columns)
	 */
	public static Matrix multiply(Matrix multiplier, Matrix multiplicand)
	{
		return multiplier.dotProduct(multiplicand);
	}
}
