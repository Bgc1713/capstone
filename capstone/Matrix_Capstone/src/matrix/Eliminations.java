package matrix;

public class Eliminations {
	
	/**
	 * Finds the row echelon form of a Matrix.
	 * @param A The matrix to reduce.
	 * @return The row echelon form of the matrix.
	 */
	public static Matrix rowEchelonForm(Matrix A)
	{
		Matrix result = new Matrix(A);
		int leadingRow = result.getRows();
		int leadingColumn = result.getColumns();
		//Do the proper leading column and resulting elims for every row
		int completedColumns = 0;
		int completedRows = 0;
		while(completedRows <= result.getRows() && completedColumns <= result.getColumns())
		{
//			for(int i = 1; i <= result.getRows(); i++)
//			{	
//				int j = 1;
//				boolean foundLeadingColumn = false;
//				while(!foundLeadingColumn && j <= result.getColumns())
//				{
//					if(result.getItem(i, j) != 0)
//					{
//						foundLeadingColumn = true;
//						if(j < leadingColumn)
//						{
//							leadingRow = i;
//							leadingColumn = j;
//						}
//					}
//					j++;
//				}
//			}
			//find current leftmost leading row, used rows and columns instead of i and j for improved readability
			//especially since this loop iterates over every row of a column before moving to a new column
			int row = completedRows + 1;
			int column = completedColumns + 1;
			boolean foundLeading = false;
			while(column <= result.getColumns() && !foundLeading)
			{
				while(row <= result.getRows() && !foundLeading)
				{
					if(result.getItem(row, column) != -0.0)
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
				result.swapRows(completedRows + 1, leadingRow);
				result.divideRow(completedRows + 1, result.getItem(completedRows + 1, leadingColumn));
				for(int i = completedRows + 2; i <= result.getRows(); i++)
				{
					double scalar = result.getItem(i, leadingColumn);
					result.subtractFromRow(i, completedRows + 1, scalar);
				}
				completedRows++;
			}
			System.out.println("System after " + (completedColumns + 1) + " interations");
			System.out.println(result.toString());
			completedColumns++;
		}
		
		return result;
	}

	
	public static Matrix reducedRowEchelonForm(Matrix A)
	{
		Matrix result = new Matrix(A.getRows(), A.getColumns());
		return result;
	}
}
