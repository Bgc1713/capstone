package matrix;

/* A class used for performing matrix operations, similar to the default Math class
 */

public class Mtxops {

	/* Addition of two matrixes (A + B = C)
	 * @param addend1 The first addend, Matrix A.
	 * @param addend2 The second addend, Matrix B.
	 * @return The sum of the two addends, Matrix C.
	 */
	public static Matrix add (Matrix addend1, Matrix addend2)
	{
		Matrix sum = new Matrix (addend1.getRows(), addend2.getColumns());
		
		if ( (addend1.getRows() == addend2.getRows()) && (addend1.getColumns() == addend2.getColumns()) )
		{
			for(int i = 1; i <= addend1.getRows(); i++)
			{
				for(int j = 1; j <= addend1.getColumns(); j++)
				{
					double thisLocationSum = 0;
					thisLocationSum = addend1.getItem(i, j) + addend2.getItem(i, j);
					sum.setItem(i, j, thisLocationSum);
				}
			}
		}
		
		return sum;
	}
	
	
	/* Subtraction of two matrixes (A - B = C)
	 * Another clever concept: Using addition to copy a matrix to a matrix of zeroes. Since the new matrix is 
	 * already initialized on its own and filled with zeroes, using addition to copy is a way to sufficiently deep copy.
	 * @param minuend The first matrix to subtract from, Matrix A.
	 * @param subtrahend The second matrix to use for subtraction, Matrix B.
	 * @return The result matrix, Matrix C.
	 */
	public static Matrix subtract(Matrix minuend, Matrix subtrahend)
	{
		Matrix difference = new Matrix(minuend.getRows(), minuend.getColumns());
		difference = add(difference, minuend);
		
		if ( (minuend.getRows() == subtrahend.getRows()) && (minuend.getColumns() == subtrahend.getColumns()) )
		{
			for(int i = 1; i <= minuend.getRows(); i++)
			{
				for(int j = 1; j <= minuend.getColumns(); j++)
				{
					double thisLocationDifference = 0;
					thisLocationDifference = minuend.getItem(i, j) - subtrahend.getItem(i, j);
					difference.setItem(i, j, thisLocationDifference);
				}
			}
		}
		return difference;
	}
	
	/* Multiplication of one matrix by a scalar (A * b = C)
	 * @param factor the Matrix to be multiplied, Matrix A.
	 * @param scalar the scalar constant to multiply with, scalar b.
	 * @return the product Matrix, Matrix C.
	 */
	public static Matrix multiply(Matrix factor, double scalar)
	{
		Matrix product = new Matrix(factor.getRows(), factor.getColumns());
		for(int i = 1; i <= factor.getRows(); i++)
		{
			for(int j = 1; j <= factor.getColumns(); j++)
			{
				double thisLocationProduct = 0;
				thisLocationProduct = (factor.getItem(i, j) * scalar);
				product.setItem(i, j, thisLocationProduct);
			}
		}
		return product;
	}
	
	/* Multiplication of two matrixes, A * B = AB
	 * @param multiplier The first matrix multiplier, Matrix A.
	 * @param multiplicand The second matrix multiplicand, Matrix B.
	 * @return the product matrix, Matrix AB. (Will have A's rows and B's columns)
	 */
	public static Matrix multiply(Matrix multiplier, Matrix multiplicand)
	{
		Matrix product = new Matrix(multiplier.getRows(), multiplicand.getColumns());
		for(int i = 1; i <= product.getRows(); i++)
		{
			for(int j = 1; j <= product.getColumns(); j++)
			{
				double thisLocationTotal = 0;
				for(int k = 1; k <= multiplier.getColumns(); k++)
				{
					thisLocationTotal += multiplier.getItem(i, k) * multiplicand.getItem(k, j);
				}
				product.setItem(i, j, thisLocationTotal);
			}
		}
		
		return product;
	}
}
