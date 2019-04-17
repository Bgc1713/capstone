package matrixTester;
import java.util.Scanner;

import matrix.Matrix;

/**
 * A program used to demonstrate the usage of the Matrix class to check if a set of vectors are linearly independent.
 * This program assumes all input is valid.
 * @author Brendan Caudill
 * @version 4-16-18
 */

public class LinearDependence {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean done = false;
		
		while(!done)
		{
			System.out.print("How many vectors to check for linear independence?: " );
			int vectors = Integer.parseInt(input.nextLine());
			System.out.print("How long are these vectors?: ");
			int vectorLength = Integer.parseInt(input.nextLine());
			int lastLeading = 0;
			if(vectors > vectorLength)
			{
				System.out.println("There amount of vectors is greater than the length of the vectors (m > n), therefore the vectors are linearly dependant");
			}
			else
			{
				Matrix mtx = new Matrix(vectorLength, vectors);
				for (int i = 1; i <= mtx.getColumns(); i++)
				{
					for (int j = 1; j <= mtx.getRows(); j++)
					{
						System.out.print("Please enter value in index " + j  + " in vector " + i + ": ");
						mtx.setItem(j, i, Double.parseDouble(input.nextLine()));
					}
				}
				System.out.println(mtx.toString());
				//mtx.reduceToRowEchelonForm();
				System.out.println(mtx.toString());
				boolean independent = true;
				// Note that columns and rows go in reverse order
				for(int i = 1; i <= mtx.getColumns(); i++)
				{
					boolean hasLeadingOne = false;
					for (int j = 1; j <= mtx.getRows(); j++)
					{
						if(mtx.getItem(j, i) != 0)
						{
							if(mtx.getItem(j, i) == 1 && j > lastLeading)
							{
								hasLeadingOne = true;
								lastLeading = j;
							}
							else
							{
								hasLeadingOne = false;
							}
						}
					}
					if(hasLeadingOne == false)
					{
						independent = false;
					}
				}
				if(independent)
				{
					System.out.println("The vectors are linearly indepenedent.");
				}
				else
				{
					System.out.println("The vectors are not linearly independent.");
				}
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
	}
}


