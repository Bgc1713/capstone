package matrixTester;

import java.util.Scanner;
import matrix.Matrix;

/** A program used to demonstrate the usage of the Matrix class to simplify systems equations,
 *  it also tells the user how many solutions a system of equations has.
 * @author Brendan Caudill
 * @version 4-22-19
 */

public class EquationsSimplifier {

	/** 
	 * The main program
	 * @param args ignored
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.print("How many equations are there?: ");
			int equations = Integer.parseInt(input.nextLine());
			System.out.print("How many variables are there?: ");
			int variables = Integer.parseInt(input.nextLine());
			Matrix systemOfEquations = new Matrix(equations, variables+1);

			for(int i = 1; i <= systemOfEquations.getRows(); i++)
			{
				for(int j = 1; j <= systemOfEquations.getColumns(); j++)
				{
					if(j < systemOfEquations.getColumns())
					{
						System.out.print("What is the constant multiplier for x" + j + " in equation " + i + "?: ");
						double multiplier = Double.parseDouble(input.nextLine());
						systemOfEquations.setItem(i, j, multiplier);
					}
					else
					{
						System.out.print("What is the constant to the right of the equals in equation " + i + "?: ");
						double constant = Double.parseDouble(input.nextLine());
						systemOfEquations.setItem(i, j, constant);
					}
				}
			}

			System.out.println("The original system of equations is:");
			printSystem(systemOfEquations);
			systemOfEquations.reduceToRowEchelonForm();
			System.out.println("The simplified system of equations is:");
			printSystem(systemOfEquations);

			//Use code created to check span to check if there is at least one solution
			boolean vectorsAreZero = true;
			boolean invalidSolution = false;
			for(int i = 1; i <= systemOfEquations.getRows(); i++)
			{
				vectorsAreZero = true;
				for (int j = 1; j <= systemOfEquations.getColumns(); j++)
				{
					if (systemOfEquations.getItem(i, j) != 0 && j != systemOfEquations.getColumns())
					{
						vectorsAreZero = false;
					}
				}
				if (vectorsAreZero && systemOfEquations.getItem(i, systemOfEquations.getColumns()) != 0)
				{
					invalidSolution = true;
				}
			}
			if(invalidSolution)
			{
				System.out.println("This system of equations has no solutions");
			}
			else
			{
				//Assemble a matrix of just the variables without the constants to the right of the equals sign, then check that matrix for nullity
				Matrix answersRemovedMatrix = new Matrix(systemOfEquations.getRows(), systemOfEquations.getColumns()-1);
				for(int i = 1; i <= answersRemovedMatrix.getRows(); i++)
				{
					for(int j = 1; j <= answersRemovedMatrix.getColumns(); j++)
					{
						answersRemovedMatrix.setItem(i, j, systemOfEquations.getItem(i, j));
					}
				}
				if(answersRemovedMatrix.getNullity() == 0)
				{
					System.out.println("This system of equations has one solution.");
				}
				else
				{
					System.out.println("This system of equations has infinitely many solutions.");
				}
			}

			boolean hasResponded = false;
			while(!hasResponded)
			{
				System.out.print("Do you want to simplify another system of equations? Y/N: ");
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

	//Method to print the matrix with added variables and plus/minus signs
	private static void printSystem(Matrix mtx)
	{
		for(int i = 1; i <= mtx.getRows(); i++)
		{
			for(int j = 1; j <= mtx.getColumns(); j++)
			{
				if(j == 1)
				{
					//Don't worry about the sign for the first numer in row
					System.out.print(mtx.getItem(i, j) + "x" + j);
				}
				else if(j < mtx.getColumns())
				{
					if(mtx.getItem(i, j) < 0)
					{
						//Print minus sign and make the number within the matrix positive
						System.out.print(" - " + (mtx.getItem(i, j) * -1) + "x" + j);
					}
					else
					{
						//Print plus sign and leave number unchanged
						System.out.print(" + " + mtx.getItem(i, j) + "x" + j);
					}
				}
				else
				{
					//Print equals sign for final number in row
					System.out.print(" = " + mtx.getItem(i, j));
				}
			}
			System.out.print("\n");
		}
	}

}
