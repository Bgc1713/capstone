package matrix;

import java.util.ArrayList;

public class Matrix {

	private ArrayList<ArrayList<Double>> mtx = new ArrayList<ArrayList<Double>>();
	int rows = 0;
	int columns = 0;
	double defaultNum = 0;
	
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
			mtx.add(new ArrayList<Double>());
			for(int j = 0; j < columns; j++)
			{
				mtx.get(i).add(defaultNum);
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
			mtx.add(new ArrayList<Double>());
			for(int j = 0; j < columns; j++)
			{
				mtx.get(i).add(A.getItem(i + 1, j + 1));
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
			mtx.add(new ArrayList<Double>());
			for(int j = 0; j < A.getColumns(); j++)
			{
				mtx.get(i).add(A.getItem(i + 1, j + 1));
			}
		}
		
		for (int i = 0; i < rows; i++)
		{
			mtx.add(new ArrayList<Double>());
			for(int j = 0; j < B.getColumns(); j++)
			{
				mtx.get(i).add(B.getItem(i + 1, j + 1));
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
		  mtxString += (mtx.get(i).toString());
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
		mtx.get(row - 1).set(column - 1, value);
	}
	
	/**
	 * Get the item stored at a specific location.
	 * @param row the row containing the location.
	 * @param column the column containing the location.
	 * @return the value stored in this location.
	 */
	public double getItem(int row, int column)
	{
		return mtx.get(row - 1).get(column - 1);
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
			row.add(mtx.get(rowNum - 1).get(i));
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
		tempRow = mtx.get(row1 - 1);
		mtx.set(row1 - 1, mtx.get(row2 - 1));
		mtx.set(row2 - 1, tempRow);
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
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) + mtx.get(sourceRow - 1).get(i)));
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
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) + (scalar* mtx.get(sourceRow - 1).get(i))));
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
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) - mtx.get(sourceRow - 1).get(i)));
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
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) - (scalar * mtx.get(sourceRow - 1).get(i))));
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
			mtx.get(targetRow - 1).set(i, ((mtx.get(targetRow - 1).get(i) * scalar)) );
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
			mtx.get(targetRow - 1).set(i, ((mtx.get(targetRow - 1).get(i) / scalar)) );
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
	 * I consider it fairly clever to use the previous addition in a novel way to create a new matrix of
	 * two other matrices being added together. See deprecated addition class to see more thoughts on it.
	 * @param source the matrix to be added to the matrix the function being called on
	 */
	public Matrix add(Matrix source)
	{
		Matrix newMatrix = new Matrix(source.getRows(), source.getColumns());
		newMatrix.addTo(this);
		newMatrix.addTo(source);
		return newMatrix;
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
	
	/**
	 *  Subtraction of two matrixes (C = A - B)
	 * Another clever concept: Using addition to copy a matrix to a matrix of zeroes. Since the new matrix is 
	 * already initialized on its own and filled with zeroes, using addition to copy is a way to sufficiently deep copy.
	 * @param source Matrix B, the matrix to subtract from the matrix subtract is being called on
	 */
	public Matrix subtract(Matrix source)
	{
		Matrix newMatrix = new Matrix(source.getRows(), source.getColumns());
		newMatrix.addTo(this);
		newMatrix.subtractFrom(source);
		return newMatrix;
	}
	
	
}

	
