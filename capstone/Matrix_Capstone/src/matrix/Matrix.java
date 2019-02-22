package matrix;

import java.util.ArrayList;

public class Matrix {

	private ArrayList<ArrayList<Double>> mtx = new ArrayList<ArrayList<Double>>();
	int rows = 0;
	int columns = 0;
	double defaultNum = 0;
	
	public Matrix(int m, int n)
	{
		rows = m;
		columns = n;
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
	
	public void setItem(int m, int n, double value)
	{
		mtx.get(m - 1).set(n - 1, value);
	}
	
	public double getItem(int m, int n)
	{
		return mtx.get(m - 1).get(n - 1);
	}
	
	public ArrayList<Double> getRow (int rowNum)
	{
		ArrayList<Double> row = new ArrayList<Double>();
		for (int i = 0; i < columns; i++)
		{
			row.add(mtx.get(rowNum - 1).get(i));
		}
		return row;
	}
	
	public void swapRows(int m, int n)
	{
		ArrayList<Double> tempRow = new ArrayList<Double>();
		tempRow = mtx.get(m - 1);
		mtx.set(m - 1, mtx.get(n - 1));
		mtx.set(n - 1, tempRow);
	}
	
	/* Row addition was a crazy challenge, because it becomes a question of "should I support this row operation"
	 * due to how convenient a single row addition can be for gaussian elimnation. In preparation, I created a "getRow"
	 * method failing to realize that if everything is within the base Matrix class, getRow isn't even usible, however
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
		
}

	
