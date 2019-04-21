package matrix;

import java.util.ArrayList;

/**
 * A class representing a Matrix, the operations that can either be used by a Matrix or on a Matrix, 
 * and the different forms or decompositions that a Matrix can have.
 * @author Brendan Caudill
 * @version 4-17-19
 */

public class Matrix {

	private ArrayList<ArrayList<Double>> theMatrix = new ArrayList<ArrayList<Double>>();
	private int rows = 0;
	private int columns = 0;
	private static final double DEFAULT_NUM = 0;
	
	/**
	 * The Matrix constructor, creates a matrix with all elements set to 0
	 * @param totalRows the amount of rows the matrix will have.
	 * @param totalColumns the amount of columns the matrix will have.
	 */
	public Matrix(int totalRows, int totalColumns)
	{
		rows = totalRows;
		columns = totalColumns;
		for (int i = 0; i < rows; i++)
		{
			theMatrix.add(new ArrayList<Double>());
			for(int j = 0; j < columns; j++)
			{
				theMatrix.get(i).add(DEFAULT_NUM);
			}
		}
	}

	/** 
	 * The Matrix Copy constructor
	 * @param A The matrix to be copied.
	 */
	public Matrix(Matrix A)
	{
		rows = A.getRows();
		columns = A.getColumns();
		for (int i = 0; i < rows; i++)
		{
			theMatrix.add(new ArrayList<Double>());
			for(int j = 0; j < columns; j++)
			{
				theMatrix.get(i).add(A.getItem(i + 1, j + 1));
			}
		}

		
	}
	
	/** 
	 * The Matrix Augmentation constructor
	 * @param A The matrix to be copied.
	 */
	public Matrix(Matrix A, Matrix B)
	{
		rows = A.getRows();
		columns = A.getColumns() + B.getColumns();
		for (int i = 0; i < rows; i++)
		{
			theMatrix.add(new ArrayList<Double>());
			for(int j = 0; j < A.getColumns(); j++)
			{
				theMatrix.get(i).add(A.getItem(i + 1, j + 1));
			}
		}
		
		for (int i = 0; i < rows; i++)
		{
			theMatrix.add(new ArrayList<Double>());
			for(int j = 0; j < B.getColumns(); j++)
			{
				theMatrix.get(i).add(B.getItem(i + 1, j + 1));
			}
		}

		
	}
	
	/**
	 * Get the matrix represented as a string.
	 * @return A string representation of the matrix.
	 */
	public String toString()
	{
	  String mtxString = "";
	  for (int i = 0; i < this.getRows(); i++)
	  {
		  mtxString += (theMatrix.get(i).toString());
		  mtxString += "\n";
	  }
	  return mtxString;
	}

	/* setItem and getItem are frequently used by every other method in the matrix class, is it best for a class to interact
	 * with itself through setters and getters? I don't recall from CS260 what the best practice is.
	 * 
	 * I could interact directly with the arrayList instead of setItem and getItem. However readability benefits a LOT
	 * from using the setters and getters (It also means I'm not constantly reusing arrayList.set/get)
	 * 
	 * However, using setItem/getItem DOES require me to consider that i and j start at one when creating loops.
	 * Overall, the readability improves, especially since interacts with other matrices do require using the setters and getters.
	 * A matrix interacting with itself is easier to read when using "this.getItem", it also produces more uniform practices and code.
	 */
	
	
	/**
	 * Sets the value of a specific location in the matrix.
	 * @param row the row containing the location.
	 * @param column the column containing the location.
	 * @param the value to place at the location.
	 */
	public void setItem(int row, int column, double value)
	{
		theMatrix.get(row - 1).set(column - 1, value);
	}
	
	/**
	 * Get the item stored at a specific location.
	 * @param row the row containing the location.
	 * @param column the column containing the location.
	 * @return the value stored in this location.
	 */
	public double getItem(int row, int column)
	{
		return theMatrix.get(row - 1).get(column - 1);
	}
	/**
	 * Determines if a given location is in bounds
	 * @param row the row to check
	 * @param column the column to check 
	 * @return true if the row and column is within the Matrix, false otherwise
	 */
	public boolean isInBounds(int row, int column)
	{
		return(row <= this.getRows() && column <= this.getColumns());
	}
	
	/**
	 * Gets the total amount of rows in a matrix.
	 * @return the amount of rows in this matrix.
	 */
	public int getRows()
	{
		return rows;
	}
	
	/**
	 * Gets the total amount of columns in a matrix.
	 * @return the amount of columns in this matrix.
	 */
	public int getColumns()
	{
		return columns;
	}
	
	
	/**
	 * Swap two rows in a matrix.
	 * @param row1 The first row to swap.
	 * @param row2 The second row to swap.
	 */
	public void swapRows(int row1, int row2)
	{
		ArrayList<Double> tempRow = new ArrayList<Double>();
		tempRow = theMatrix.get(row1 - 1);
		theMatrix.set(row1 - 1, theMatrix.get(row2 - 1));
		theMatrix.set(row2 - 1, tempRow);
	}
	
	
	/**
	 * A row addition operation.
	 * @param targetRow The row to add to.
	 * @param sourcerow The row to use for addition.
	 */
	public void addToRow(int targetRow, int sourceRow)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, (this.getItem(sourceRow, i) + this.getItem(targetRow, i)) );
		}
	}
	
	
	/**
	 * A row addition operation with a scalar.
	 * @param targetRow The row to add to.
	 * @param sourceRow The row to use for addition.
	 * @param scalar The scalar to multiply the source row by.
	 */
	public void addToRow(int targetRow, int sourceRow, double scalar)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, (this.getItem(targetRow, i) + (scalar * this.getItem(sourceRow, i)) ) );
		}
	}
	
	
	/* So you add TO things when they're the target, but subtract FROM things when they're the target */
	/**
	 * Subtracts one row from another.
	 * @param targetRow The row to subtract from.
	 * @param sourceRow The row to use for subtraction.
	 */
	public void subtractFromRow(int targetRow, int sourceRow)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, (this.getItem(targetRow, i) - this.getItem(sourceRow, i)));
		}
	}
	
	/** 
	 * Subtracts one row from another.
	 * @param targetRow The row to subtract from.
	 * @param sourceRow The row to use for subtraction.
	 * @param scalar The scalar to multiply the source row by.
	 */
	public void subtractFromRow(int targetRow, int sourceRow, double scalar)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, (this.getItem(targetRow, i) - (scalar * this.getItem(sourceRow, i))));
		}
	}
	

	/**
	 * Multiply a row by a scalar constant
	 * @param targetRow The row to multiply.
	 * @param scalar The scalar to multiply the row by.
	 */
	public void multiplyRow(int targetRow, double scalar)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, this.getItem(targetRow, i) * scalar);
		}
	}
	
	/**
	 * Divide a row by a scalar constant
	 * @param targetRow The row to divide.
	 * @param scalar The scalar to divide the row by.
	 */
	public void divideRow(int targetRow, double scalar)
	{
		for (int i = 1; i <= this.getColumns(); i ++)
		{
			this.setItem(targetRow, i, this.getItem(targetRow, i) / scalar);
		}
	}	
	/**
	 * Addition to a matrix (A = A + B, plus-equals)
	 * @param source the matrix to add to this matrix
	 */
	public void addTo(Matrix source)
	{
		if ( (source.getRows() == this.getRows()) && (source.getColumns() == this.getColumns()) )
		{
			for(int i = 1; i <= this.getRows(); i++)
			{
				for(int j = 1; j <= this.getColumns(); j++)
				{
					this.setItem(i, j, (this.getItem(i, j) + source.getItem(i, j)));
				}
			}
		}
	}
	
	/** 
	 * Addition of two matrixes (C = A + B)
	 * I consider it fairly novel to use the void Matrix addition to add two matrices into default value matrix.
	 * @param source the matrix to sum with this matrix
	 */
	public Matrix add(Matrix source)
	{
		Matrix sum = new Matrix(source.getRows(), source.getColumns());
		sum.addTo(this);
		sum.addTo(source);
		return sum;
	}
	
	/**
	 *  Subtraction from a matrix (A = A - B, minus-equals)
	 * @param source Matrix B, The matrix to subtract from this matrix
	 */
	public void subtractFrom(Matrix source)
	{
		if ( (source.getRows() == this.getRows()) && (source.getColumns() == this.getColumns()) )
		{
			for(int i = 1; i <= this.getRows(); i++)
			{
				for(int j = 1; j <= this.getColumns(); j++)
				{
					this.setItem(i, j, (this.getItem(i, j) - source.getItem(i, j)));
				}
			}
		}
	}
	
	/*
	 * Another clever concept: Using addition to copy a matrix to a matrix of zeroes. Since the new matrix is 
	 * already initialized on its own and filled with zeroes, using addition to copy is viable. It also
	 * prevents me from having to create two matrices to perform the subtraction.
	 */
	
	/**
	 *  Subtraction of two matrixes (C = A - B)
	 * @param source Matrix B, the matrix to subtract from this matrix
	 */
	public Matrix subtract(Matrix source)
	{
		Matrix difference = new Matrix(this);
		difference.subtractFrom(source);
		return difference;
	}
	
	/**
	 * Determines if the instance of matrix has the same dimensions of another provided matrix.
	 * @param source the matrix to compare to
	 * @return true if the matrices have the same dimensions, false otherwise
	 */
	public boolean hasSameDimensions(Matrix source)
	{
		return ( (this.getRows() == source.getRows()) && (this.getColumns() == source.getColumns()) );
	}
	
	/** Multiplication of the Matrix by a scalar.
	 * @param scalar the scalar constant to multiply with.
	 */
	public void multiplyBy(double scalar)
	{
		for(int i = 1; i <= this.getRows(); i++)
		{
			for(int j = 1; j <= this.getColumns(); j++)
			{
				this.setItem(i, j, (this.getItem(i, j) * scalar));
			}
		}
	}
	
	/** Get the multiplication of the Matrix by a scalar
	 * @param scalar the scolar constant to multiply with.
	 * @return the product Matrix, Matrix C.
	 */
	public Matrix multiply(double scalar)
	{
		Matrix product = new Matrix(this);
		product.multiplyBy(scalar);
		return product;
	}
	
	
	/** Dot product of two matrixes, A * B = AB
	 * @param multiplicand The second matrix multiplicand, Matrix B.
	 * @return the product matrix, Matrix AB. (Will have A's rows and B's columns)
	 */
	public Matrix dotProduct(Matrix multiplicand)
	{
		Matrix product = new Matrix(this.getRows(), multiplicand.getColumns());
		for(int i = 1; i <= product.getRows(); i++)
		{
			for(int j = 1; j <= product.getColumns(); j++)
			{
				double thisLocationTotal = 0;
				for(int k = 1; k <= this.getColumns(); k++)
				{
					thisLocationTotal += this.getItem(i, k) * multiplicand.getItem(k, j);
				}
				product.setItem(i, j, thisLocationTotal);
			}
		}
		
		return product;
	}
	
	
	/**
	 * Determins if this matrix can be multiplied by a given other matrix.
	 * @param multiplicand the matrix to check this matrix against.
	 * @return true if the two matrices are compatible for a dot product, false otherwise
	 */
	public boolean canBeMultipliedWith(Matrix multiplicand)
	{
		return (this.getRows() == multiplicand.getColumns());
	}
	
	
	/**
	 * Puts the Matrix into row-echelon form
	 */
	public void reduceToRowEchelonForm()
	{
		int leadingRow = this.getRows();
		int leadingColumn = this.getColumns();
		//Do the proper leading column and resulting elims for every row
		int completedColumns = 0;
		int completedRows = 0;
		while(completedRows <= this.getRows() && completedColumns <= this.getColumns())
		{
			//Used rows and columns instead of i and j to improve readability, since this algorithm is column-first
			int row = completedRows + 1;
			int column = completedColumns + 1;
			boolean foundLeading = false;
			while(column <= this.getColumns() && !foundLeading)
			{
				while(row <= this.getRows() && !foundLeading)
				{
					if(this.getItem(row, column) != 0)
					{
						leadingRow = row;
						leadingColumn = column;
						foundLeading = true;
					}
					row++;
				}
				column++;
			}
			if(foundLeading)
			{
				if(completedRows + 1 != leadingRow)
				{
					this.swapRows(completedRows + 1, leadingRow);
				}
				this.divideRow(completedRows + 1, this.getItem(completedRows + 1, leadingColumn));
				for(int i = completedRows + 2; i <= this.getRows(); i++)
				{
					double scalar = this.getItem(i, leadingColumn);
					this.subtractFromRow(i, completedRows + 1, scalar);
				}
				completedRows++;
			}
			completedColumns++;
		}
		
	}

	/**
	 * Finds the row-echelon form of the Matrix.
	 * @return The row-echelon form of the matrix.
	 */
	public Matrix getRowEchelonForm()
	{
		Matrix result = new Matrix(this);
		result.reduceToRowEchelonForm();
		return result;
	}
	
	/**
	 * Reduces the matrix to reduced row-echelon form by gauss-jordan elimination.
	 */
	public void reduceToReducedRowEchelonForm()
	{
		int leadingRow = this.getRows();
		int leadingColumn = this.getColumns();
		//Do the proper leading column and resulting elims for every row
		int completedColumns = 0;
		int completedRows = 0;
		while(completedRows <= this.getRows() && completedColumns <= this.getColumns())
		{
			//Find current leftmost leading row, used rows and columns variable names instead of i and j for improved readability
			//especially since this loop is rows nested in columns, rather than columns nested in rows like every other loop in the proram
			int row = completedRows + 1;
			int column = completedColumns + 1;
			boolean foundLeading = false;
			while(column <= this.getColumns() && !foundLeading)
			{
				while(row <= this.getRows() && !foundLeading)
				{
					if(this.getItem(row, column) != 0)
					{
						leadingRow = row;
						leadingColumn = column;
						foundLeading = true;
					}
					row++;
				}
				column++;
			}
			if(foundLeading)
			{
				if(completedRows + 1 != leadingRow)
				{
					this.swapRows(completedRows + 1, leadingRow);
				}
				this.divideRow(completedRows + 1, this.getItem(completedRows + 1, leadingColumn));
				for(int i = 1; i <= this.getRows(); i++)
				{
					if(i != completedRows + 1)
					{
						double scalar = this.getItem(i, leadingColumn);
						this.subtractFromRow(i, completedRows + 1, scalar);
					}
				}
				completedRows++;
			}

			completedColumns++;
		}
		
	}
	/**
	 * Gets the reduced row-echelon form of the matrix.
	 * @return The reduced row-echelon form of the matrix
	 */
	public Matrix getReducedRowEchelonForm()
	{
		Matrix result = new Matrix(this);
		result.reduceToReducedRowEchelonForm();
		return result;
	}
	
	/**
	 * Inverts the matrix.
	 */
	public void invert()
	{
		Matrix identity = new Matrix(this.getRows(), this.getColumns());
		for (int i = 1; i <= identity.getColumns(); i++)
		{
			identity.setItem(i, i, 1);
		}
		Matrix augmented = new Matrix(this, identity);
		augmented.reduceToReducedRowEchelonForm();

		for (int i = 1; i <= this.getRows(); i++)
		{
			for (int j = 1; j <= this.getColumns(); j++)
			{
				this.setItem(i, j, augmented.getItem(i, j + this.getColumns()));
			}
		}
	}
	
	/**
	 * Gets the Inverse of the matrix.
	 * @return The inverse of the matrix
	 */
	public Matrix getInverse()
	{
		Matrix result = new Matrix(this);
		result.invert();
		return result;
	}
	
	/**
	 * Tells whether or not a Matrix is invertible.
	 * @return True if matrix is invertible, false otherwise
	 */
	public boolean isInvertible()
	{
		if (this.getDeterminant() != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Performs LU factorization using Doolittle's method. Will not work for a Matrix that needs row swaps to be factorizable.
	 * @param L Matrix to store the lower triangular result
	 * @param U Matrix to store the upper triangular result
	 */
	private void doLUFactorization(Matrix L, Matrix U, Matrix P)
	{
		//Initialize permutation identity matrix with diagonal ones
		for(int i = 1; i <= L.getRows(); i++)
		{
			P.setItem(i, i, 1);
		}
		int leadingRow = U.getRows();
		int leadingColumn = U.getColumns();
		//Do the proper leading column and resulting elims for every row
		int completedColumns = 0;
		int completedRows = 0;
		while(completedRows <= U.getRows() && completedColumns <= U.getColumns())
		{
			//Find current leftmost leading row, used rows and columns variable names instead of i and j for improved readability
			//especially since this loop iterates over every row of a column before moving to a new column
			int row = completedRows + 1;
			int column = completedColumns + 1;
			boolean foundLeading = false;
			while(column <= U.getColumns() && !foundLeading)
			{
				while(row <= U.getRows() && !foundLeading)
				{
					if(U.getItem(row, column) != 0)
					{
						leadingRow = row;
						leadingColumn = column;
						foundLeading = true;
					}
					row++;
				}
				column++;
			}
			if(foundLeading)
			{
				//Swap rows if needed, and make the same swap in every Matrix
				if(completedRows + 1 != leadingRow)
				{
					L.swapRows(completedRows + 1, leadingRow);
					U.swapRows(completedRows + 1, leadingRow);
					P.swapRows(completedRows + 1, leadingRow);
				}
				for(int i = completedRows + 2; i <= U.getRows(); i++)
				{
					double scalar = U.getItem(i, leadingColumn)/U.getItem(completedRows + 1, leadingColumn);
					U.subtractFromRow(i, completedRows + 1, scalar);
					L.setItem(i, leadingColumn, scalar);
				}
				completedRows++;
			}
			completedColumns++;
		}
		//Put diagonal ones into the lower diagonal matrix
		for(int i = 1; i <= L.getRows(); i++)
		{
			L.setItem(i, i, 1);
		}
	}
	
	/**
	 * Returns the lower triangular decomposition on a matrix.
	 * @return the lower triangular matrix
	 */
	public Matrix getLDecomposition()
	{
		Matrix L = new Matrix(this.getRows(), this.getColumns());
		Matrix U = new Matrix(this);		
		Matrix P = new Matrix(this.getRows(), this.getColumns());
        doLUFactorization(L, U, P);
		return L;
	}
	/**
	 * Returns the upper triangular decomposition on a matrix.
	 * @return the upper triangular matrix
	 */
	public Matrix getUDecomposition()
	{
		Matrix L = new Matrix(this.getRows(), this.getColumns());
		Matrix U = new Matrix(this);
		Matrix P = new Matrix(this.getRows(), this.getColumns());
        doLUFactorization(L, U, P);
		return U;
	}
	
	/**
	 * Returns the permutation matrix for the LU decomposition of this matrix.
	 * @return the permutation matrix
	 */
	public Matrix getPermutationMatrix()
	{
		Matrix L = new Matrix(this.getRows(), this.getColumns());
		Matrix U = new Matrix(this);
		Matrix P = new Matrix(this.getRows(), this.getColumns());
        doLUFactorization(L, U, P);
        return P;
	}
	
	/**
	 * Finds the determinant of a matrix.
	 * @return the determinant of the matrix
	 */
	public double getDeterminant()
	{
		Matrix workMatrix = new Matrix(this);
		int leadingRow = workMatrix.getRows();
		int leadingColumn = workMatrix.getColumns();
		//Do the proper leading column and resulting elims for every row
		int completedColumns = 0;
		int completedRows = 0;
		boolean negativeDeterminant = false;
		double determinant = 1;
		
		while(completedRows <= workMatrix.getRows() - 1 && completedColumns <= workMatrix.getColumns())
		{
			//Find current leftmost leading row, used rows and columns variable names instead of i and j for improved readability
			//especially since this loop iterates over every row of a column before moving to a new column
			int row = completedRows + 1;
			int column = completedColumns + 1;
			boolean foundLeading = false;

			while(column <= workMatrix.getColumns() && !foundLeading)
			{
				while(row <= workMatrix.getRows() && !foundLeading)
				{
					if(workMatrix.getItem(row, column) != 0)
					{
						leadingRow = row;
						leadingColumn = column;
						foundLeading = true;
					}
					row++;
				}
				column++;
			}
			if(foundLeading)
			{
				if(completedRows + 1 != leadingRow)
				{
					workMatrix.swapRows(completedRows + 1, leadingRow);
					if (negativeDeterminant)
					{
						negativeDeterminant = false;
					}
					else
					{
						negativeDeterminant = true;
					}
				}
				if(workMatrix.getItem(completedRows + 1, leadingColumn) != 1)
				{
					determinant *= (workMatrix.getItem(completedRows + 1, leadingColumn));
					workMatrix.divideRow(completedRows + 1, workMatrix.getItem(completedRows + 1, leadingColumn));
				}
				for(int i = completedRows + 2; i <= workMatrix.getRows(); i++)
				{
					double scalar = workMatrix.getItem(i, leadingColumn);
					workMatrix.subtractFromRow(i, completedRows + 1, scalar);
				}
				completedRows++;
			}
			completedColumns++;

		}
		if (negativeDeterminant)
		{
			determinant *= -1;
		}
		determinant *= workMatrix.getItem(workMatrix.getRows(), workMatrix.getColumns());
		return determinant;
	}
	
	/**
	 * Get the rank of a Matrix
	 * @return the rank of the matrix
	 */
	public int getRank()
	{
		int rank = 0;
		Matrix reduced = this.getRowEchelonForm();
		for(int i = 1; i <= reduced.getRows(); i++)
		{
			boolean isNonzero = false;
			for(int j = 1; j <= reduced.getColumns(); j++)
			{
				if (reduced.getItem(i, j) != 0)
				{
					isNonzero = true;
				}
			}
			if (isNonzero)
			{
				rank++;
			}
		}
		return rank;
	}
	
	/**
	 * Get the nullity of a Matrix
	 * @return the nullity of the matrix
	 */
	public int getNullity()
	{
		int nullity = this.getColumns() - this.getRank();
		return nullity;
	}
	
}

	
