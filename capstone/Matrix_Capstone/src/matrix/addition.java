package matrix;

//Theoretical addition object, realised that would require the user to pass the matrix itself, and that's just awful
//Will deprecate this method most likely, and keep basic ops within the matrix itself, I'm surprised how non-modular this might be 
//Did more work and realised most ops must remain in matrix.
public class addition {

	/* Effectively a plus-equals operation.
	 * @param source the source matrix.
	 * @param target the target matrix which will have addition performed on it.
	 */
	public void addto (Matrix source, Matrix target)
	{
		if ( (source.getRows() == target.getRows()) && (source.getColumns() == target.getColumns()) )
		{
			for(int i = 0; i < target.getRows(); i++)
			{
				for(int j = 0; j < target.getColumns(); j++)
				{
					double thisLocationResult = 0;
					thisLocationResult = source.getItem(i, j) + target.getItem(i, j);
					target.setItem(i, j, thisLocationResult);
				}
			}
		}
	}
	
	
	/* Adding both matrixes to a matrix of zeroes seems like very bad practice, but is very clever usage.
	 * The more important concept at work is that this method returns a new matrix, rather than operating on an old one
	 */
	public Matrix add (Matrix additive1, Matrix additive2)
	{
		Matrix result = new Matrix (additive1.getRows(), additive1.getColumns());
		return result;
	}
}
