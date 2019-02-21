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
}