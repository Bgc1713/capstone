package matrix;

import java.util.ArrayList;

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
	  for (int i = 0; i < rows; i++)
	  {
		  mtxString += (theMatrix.get(i).toString());
		  mtxString += "\n";
	  }
	  return mtxString;
	}
	
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
	
	//This may not even be used since rowops don't use this function.
	public ArrayList<Double> getRow (int rowNum)
	{
		ArrayList<Double> row = new ArrayList<Double>();
		for (int i = 0; i < columns; i++)
		{
			row.add(theMatrix.get(rowNum - 1).get(i));
		}
		return row;
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
	
	/* Row addition was a crazy challenge, because it becomes a question of "should I support this row operation"
	 * due to how convenient a single row addition can be for gaussian elimination. In preparation, I created a "getRow"
	 * method failing to realize that if everything is within the base Matrix class, getRow isn't even usable, however
	 * I have the matrix in its base 2d arraylist form, meaning I can just add them together like this
	 * 
	 * As I soon consider doing multiplication of rows (Which is equivalent to repeated adds, it becomes a question 
	 * of "Do I code this like I would work it by hand, or do easier code that accomplishes the same end result?"
	 * 
	 * I also realized that since row operations are only ever done in the context of one matrix, creating public methods 
	 * that aid in row operation are completely useless, as I'm writing code to be used in the ONE place it isn't helpful.
	 */
	/**
	 * A row addition operation.
	 * @param targetRow The row to add to.
	 * @param sourcerow The row to use for addition.
	 */
	public void addToRow(int targetRow, int sourceRow)
	{
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, (theMatrix.get(targetRow - 1).get(i) + theMatrix.get(sourceRow - 1).get(i)));
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
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, (theMatrix.get(targetRow - 1).get(i) + (scalar* theMatrix.get(sourceRow - 1).get(i))));
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
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, (theMatrix.get(targetRow - 1).get(i) - theMatrix.get(sourceRow - 1).get(i)));
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
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, (theMatrix.get(targetRow - 1).get(i) - (scalar * theMatrix.get(sourceRow - 1).get(i))));
		}
	}
	
	/* This brings up an interesting question, is it wise to create one method that uses an objects other methods?
	 * I could interact directly with the arrayList instead of setItem and getItem. However readability benefits a LOT
	 * from using the setters and getters (It also means I'm not constantly reusing arrayList.set/get)
	 * 
	 * However, using setItem/getItem DOES require me to add 1 to i and j before passing them.
	 */
	
	/**
	 * Multiply a row by a scalar constant
	 * @param targetRow The row to multiply.
	 * @param scalar The scalar to multiply the row by.
	 */
	public void multiplyRow(int targetRow, double scalar)
	{
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, ((theMatrix.get(targetRow - 1).get(i) * scalar)) );
		}
	}
	
	/**
	 * Divide a row by a scalar constant
	 * @param targetRow The row to divide.
	 * @param scalar The scalar to divide the row by.
	 */
	public void divideRow(int targetRow, double scalar)
	{
		for (int i = 0; i < columns; i ++)
		{
			theMatrix.get(targetRow - 1).set(i, ((theMatrix.get(targetRow - 1).get(i) / scalar)) );
		}
	}	
	/**
	 * Addition to a matrix (A = A + B, plus-equals)
	 * @param source the matrix that will be added to the matrix this function is being called from
	 */
	public void addTo(Matrix source)
	{
		if ( (source.getRows() == rows) && (source.getColumns() == columns) )
		{
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					int thisRow = i + 1;
					int thisColumn = j + 1;
					double thisLocationResult = 0;
					thisLocationResult = source.getItem(thisRow, thisColumn) + this.getItem(thisRow, thisColumn);
					this.setItem(thisRow, thisColumn, thisLocationResult);
				}
			}
		}
	}
	
	/** 
	 * Addition of two matrixes (C = A + B)
	 * I consider it fairly novel to use the void Matrix addition to add two matrices into default value matrix.
	 * @param source the matrix to be added to the matrix the function being called on
	 */
	public Matrix add(Matrix source)
	{
		Matrix sum = new Matrix(source.getRows(), source.getColumns());
		sum.addTo(this);
		sum.addTo(source);
		return sum;
	}
	
	/**
	 *  Subraction from a matrix (A = A - B, minus-equals)
	 * @param source Matrix B, The matrix to subtract from the matrix subtractFrom is being called on)
	 */
	public void subtractFrom(Matrix source)
	{
		if ( (source.getRows() == rows) && (source.getColumns() == columns) )
		{
			for(int i = 0; i < rows; i++)
			{
				for(int j = 0; j < columns; j++)
				{
					int thisRow = i + 1;
					int thisColumn = j + 1;
					double thisLocationResult = 0;
					thisLocationResult = this.getItem(thisRow, thisColumn) - source.getItem(thisRow, thisColumn);
					this.setItem(thisRow, thisColumn, thisLocationResult);
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
	 * @param source Matrix B, the matrix to subtract from the matrix subtract is being called on
	 */
	public Matrix subtract(Matrix source)
	{
		Matrix difference = new Matrix(this);
		difference.subtractFrom(source);
		return difference;
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
				double thisLocationProduct = 0;
				thisLocationProduct = (this.getItem(i, j) * scalar);
				this.setItem(i, j, thisLocationProduct);
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
		product.multiply(scalar);
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
			//Find current leftmost leading row, used rows and columns variable names instead of i and j for improved readability
			//especially since this loop iterates over every row of a column before moving to a new column
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
//			System.out.println("System after " + (completedColumns + 1) + " interations");
//			System.out.println(this.toString());
			completedColumns++;
		}
		
	}
	
	//I really enjoy how I can just call the other reduction function with a copy of this matrix to return a reduced matrix
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
//			System.out.println("System after " + (completedColumns + 1) + " interations");
//			System.out.println(this.toString());
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
	private void doLUFactorization(Matrix L, Matrix U)
	{
		for (int i = 1; i <= this.getRows(); i++)
		{
			for(int k = i; k <= this.getRows(); k++)
			{
				int sum = 0;
				for(int j = 1; j <= this.getRows(); j++)
				{
					sum += (L.getItem(i, j) * U.getItem(j, k));
				}
				U.setItem(i, k, (this.getItem(i,k) - sum));
			}
			for (int k = i; k <= this.getColumns(); k++)
			{
				if(k == i)
				{
					L.setItem(i, i, 1);
				}
				else
				{
					int sum = 0;
					for(int j = 1; j <= i; j++)
					{
						sum += (L.getItem(k, j) * U.getItem(j, i));
					}
					assert U.getItem(i, i) != 0: "Unable to find LU factorization through Doolittle Method, 0 in U diagonal part of matrix.";
					L.setItem(k, i, (this.getItem(k, i) - sum) / U.getItem(i, i) );
				}
			}
		}
		

		
	}
	
	/**
	 * Returns the lower triangle decomposition on a matrix that does not require permutations to find an LU factorization.
	 * @return the lower triangle matrix
	 */
	public Matrix getLDecomposition()
	{
		Matrix L = new Matrix(this.getRows(), this.getColumns());
		Matrix U = new Matrix(this.getRows(), this.getColumns());
        doLUFactorization(L, U);
		return L;
	}
	/**
	 * Returns the upper triangle decomposition on a matrix that does not require permutations to find an LU factorization.
	 * @return the upper triangle matrix
	 */
	public Matrix getUDecomposition()
	{
		Matrix L = new Matrix(this.getRows(), this.getColumns());
		Matrix U = new Matrix(this.getRows(), this.getColumns());
        doLUFactorization(L, U);
		return U;
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
	
}

	
