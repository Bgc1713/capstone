package matrixTester;
import java.util.Scanner;
import matrix.Matrix;

/**
 * A program used to demonstrate the usage of the Matrix class to check if w is in the span of a set of vectors.
 * This program assumes all input is valid.
 * @author Brendan Caudill
 * @version 4-16-19
 */

public class Span {

	/** 
	 * The main program
	 * @param args ignored
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean done = false;
		while(!done)
		{
			System.out.print("How many vectors to check span (including w vector)?: " );
			int vectors = Integer.parseInt(input.nextLine());
			System.out.print("How long are these vectors?: ");
			int vectorLength = Integer.parseInt(input.nextLine());
			Matrix spanMtx = new Matrix(vectorLength, vectors);
			for (int i = 1; i <= spanMtx.getColumns(); i++)
			{
				for (int j = 1; j <= spanMtx.getRows(); j++)
				{
					if(i == spanMtx.getColumns())
					{
						System.out.print("Please enter value in index " + j + " in vector w: ");
					}
					else
					{
						System.out.print("Please enter value in index " + j  + " in vector " + i + ": ");
					}
					spanMtx.setItem(j, i, Double.parseDouble(input.nextLine()));
				}
			}

			spanMtx.reduceToRowEchelonForm();
			boolean vectorsAreZero = true;
			boolean invalidSolution = false;
			for(int i = 1; i <= spanMtx.getRows(); i++)
			{
				vectorsAreZero = true;
				for (int j = 1; j <= spanMtx.getColumns(); j++)
				{
					if (spanMtx.getItem(i, j) != 0 && j != spanMtx.getColumns())
					{
						vectorsAreZero = false;
					}
				}
				if (vectorsAreZero && spanMtx.getItem(i, spanMtx.getColumns()) != 0)
				{
					invalidSolution = true;
				}
			}
			
			if(invalidSolution)
			{
				System.out.println("W is not in the span of the vectors");
			}
			else
			{
				System.out.println("W is in the span of the vectors");
			}
			
			boolean hasResponded = false;
			while(!hasResponded)
			{
				System.out.print("Do you want to check another set of vectors? Y/N: ");
				String response = input.nextLine();
				if(response.equals("No") || response.equals("no") || response.equals("N") || response.equals("n"))
				{
					input.close();
					System.exit(0);
				}
				else if(response.equals("Yes") || response.equals("yes") || response.equals("Y") || response.equals("y"))
				{
					hasResponded = true;
				}
			}
		}
		input.close();
	}

}
