package matrix;

import java.util.ArrayList;

public class Matrix {

	private ArrayList<ArrayList<Double>> mtx = new ArrayList<ArrayList<Double>>();
	int rows = 0;
	int columns = 0;
	double defaultNum = 0;
	
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
	
	public void setItem(int row, int column, double value)
	{
		mtx.get(row - 1).set(column - 1, value);
	}
	
	public double getItem(int row, int column)
	{
		return mtx.get(row - 1).get(column - 1);
	}
	
	public int getRows()
	{
		return rows;
	}
	
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
	public void addToRow(int targetRow, int sourceRow)
	{
		for (int i = 0; i < columns; i ++)
		{
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) + mtx.get(sourceRow - 1).get(i)));
		}
	}
	
	/* So you add TO things when they're the target, but subtract FROM things when they're the target */
	public void subtractFromRow(int targetRow, int sourceRow)
	{
		for (int i = 0; i < columns; i ++)
		{
			mtx.get(targetRow - 1).set(i, (mtx.get(targetRow - 1).get(i) - mtx.get(sourceRow - 1).get(i)));
		}
	}
	
	/* This brings up an interesting question, is it wise to create one method that uses an objects other methods?
	 * I could interact directly with the arrayList instead of setItem and getItem. However readability benefits a LOT
	 * from using the setters and getters (It also means I'm not constantly reusing arrayList.set/get)
	 * 
	 * However, using setItem/getItem DOES require me to add 1 to i and j before passing them.
	 */
	
	/* Addition to a matrix (A = A + B, plus-equals)
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
	
	/* Addition of two matrixes (C = A + B)
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
	
	/* Subraction from a matrix (A = A - B, minus-equals)
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
	
	/* Subtraction of two matrixes (C = A - B)
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

	
