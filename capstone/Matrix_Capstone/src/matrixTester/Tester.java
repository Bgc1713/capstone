package matrixTester;


import java.util.Scanner;

import matrix.Matrix;

/**
 * A program used to demonstrate the usage of the Matrix class to perform many different matrix operations.
 * This program assumes all input is valid.
 * @author Brendan Caudill
 * @version 4-17-19
 */
public class Tester {

	/** 
	 * The main program
	 * @param args ignored
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String response;
		boolean useSameMatrix = false;
		int rows = 0;
		int columns = 0;
		Matrix mtx = new Matrix(rows, columns);
		boolean done = false;
		boolean hasResponded = false;
		while(!done)
		{
			if(!useSameMatrix)
			{
				System.out.print("Enter rows: ");
				rows = Integer.parseInt(input.nextLine());
				System.out.print("Enter columns: ");
				columns = Integer.parseInt(input.nextLine());
				mtx = new Matrix(rows, columns);
				for (int i = 1; i <= mtx.getRows(); i++)
				{
					for (int j = 1; j <= mtx.getColumns(); j++)
					{
						System.out.print("Please enter number in " + i + ", " + j + ": ");
						mtx.setItem(i, j, Double.parseDouble(input.nextLine()));
					}
				}
			}
			System.out.println("Your matrix is:");
			System.out.println(mtx.toString());
			System.out.println("What would you like to do with this matrix?");
			System.out.println("Add(a), Subtract(s), Multiply with a scalar(m), Dot with another Matrix(d), Gaussian Elimination(g), Gauss-Jordan Elimination(gj), get determinant(det), get inverse(i), get rank(r), get nullity(n), or get LU factorization(lu)");
			
			hasResponded = false;
			while(!hasResponded)
			{
				response = input.nextLine();
				switch(response)
					{
						case "a":
							hasResponded = true;
							Matrix addMtx = new Matrix(rows, columns);
							for (int i = 1; i <= addMtx.getRows(); i++)
							{
								for (int j = 1; j <= addMtx.getColumns(); j++)
								{
									System.out.print("Please enter number in " + i + ", " + j + ": ");
									addMtx.setItem(i, j, Double.parseDouble(input.nextLine()));
								}
							}
							System.out.println(mtx.toString()+ "Plus \n" + addMtx.toString() + "is \n"  + mtx.add(addMtx).toString());
							break;
							
						case "s":
							hasResponded = true;
							Matrix subMtx = new Matrix(rows, columns);
							for (int i = 1; i <= subMtx.getRows(); i++)
							{
								for (int j = 1; j <= subMtx.getColumns(); j++)
								{
									System.out.print("Please enter number in " + i + ", " + j + ": ");
									subMtx.setItem(i, j, Double.parseDouble(input.nextLine()));
								}
							}
							System.out.println(mtx.toString()+ "Minus \n" + subMtx.toString() + "is \n"  + mtx.subtract(subMtx).toString());		
							break;
							
						case "d":
							hasResponded = true;
							System.out.println("Enter total columns: ");
							int newRows = columns;
							int newColumns = Integer.parseInt(input.nextLine());
							Matrix dotMtx = new Matrix(newRows, newColumns);
							for (int i = 1; i <= dotMtx.getRows(); i++)
							{
								for (int j = 1; j <= dotMtx.getColumns(); j++)
								{
									System.out.print("Please enter number in " + i + ", " + j + ": ");
									dotMtx.setItem(i, j, Double.parseDouble(input.nextLine()));
								}
							}
							System.out.println(mtx.toString()+ "times \n" + dotMtx.toString() + "is \n"  + mtx.dotProduct(dotMtx).toString());
							break;
							
						case "m": 
							hasResponded = true;
							System.out.println("Enter scalar: ");
							Double scalar = Double.parseDouble(input.nextLine());
							System.out.println(mtx.toString() + "times \n" + scalar + "\n is \n" + mtx.multiply(scalar).toString());
							break;
							
						case "g":
							hasResponded = true;
							System.out.println("The row-echelon form of \n" + mtx.toString() + " is \n" + mtx.getRowEchelonForm().toString());
							break;
							
						case "gj":
							hasResponded = true;
							System.out.println("The reduced row-echelon form of \n" + mtx.toString() + " is \n" + mtx.getReducedRowEchelonForm().toString());
							break;
							
						case "det":
							hasResponded = true;
							System.out.println("The determinant of \n" + mtx.toString() + "is " + mtx.getDeterminant());
							break;
						
						case "lu":
							hasResponded = true;
							System.out.println("Out of \n" + mtx.toString());
							System.out.println("The lower triangular representation is: \n" + mtx.getLDecomposition().toString());
							System.out.println("The upper triangular representation is: \n" + mtx.getUDecomposition().toString());
							System.out.println("The permutation matrix is: \n" + mtx.getPermutationMatrix().toString());
							break;
							
						case "i":
							hasResponded = true;
							if(mtx.isInvertible())
							{
								System.out.println("The inverse of \n" + mtx.toString() + "is " + mtx.getInverse());
							}
							else
							{
								System.out.println("This matrix is not invertible.");
							}
							break;
						
						case "r":
							hasResponded = true;
							System.out.println("The rank of\n" + mtx.toString() + "is " + mtx.getRank());
							break;
							
						case "n":
							hasResponded = true;
							System.out.println("The nullity of\n" + mtx.toString() + "is " + mtx.getNullity());
							break;
							
						default:
							System.out.println("Input option not valid.");
							break;
					}
			}
			

			hasResponded = false;
			while(!hasResponded)
			{
				System.out.print("Would you like to continue? Y/N: ");
				response = input.nextLine();
				if(response.equals("No") || response.equals("no") || response.equals("N") || response.equals("n"))
				{
					input.close();
					System.exit(0);
				}
				else if(response.equals("Yes") || response.equals("yes") || response.equals("Y") || response.equals("y"))
				{
					hasResponded = true;
					done = false;
				}
			}
				
			hasResponded = false;

			while(!hasResponded)
			{
				System.out.print("Do you want to use the same matrix? Y/N: ");
				response = input.nextLine();
				if(response.equals("No") || response.equals("no") || response.equals("N") || response.equals("n"))
				{
					useSameMatrix = false;
					hasResponded = true;
				}
				else if(response.equals("Yes") || response.equals("yes") || response.equals("Y") || response.equals("y"))
				{
					useSameMatrix = true;
					hasResponded = true;
				}
			}
	
		}	
		
	}


}
