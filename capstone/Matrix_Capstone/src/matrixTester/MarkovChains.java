package matrixTester;


import matrix.Matrix;
import java.util.Scanner;
import java.util.ArrayList;

/** A program used to demonstrate the usage of the Matrix class to perform Markov chains.
 * @author Brendan Caudill
 * @version 4-22-19
 */

public class MarkovChains {

	/** 
	 * The main program
	 * @param args ignored
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.print("How many outcomes are there?: ");
			int outcomes = Integer.parseInt(input.nextLine());
			Matrix transitionMatrix = new Matrix(outcomes, outcomes);
			Matrix currentProbabilityMatrix = new Matrix(outcomes, 1);
			ArrayList<String> outcomeList = new ArrayList<String>();
			for (int i = 0; i < outcomes; i ++)
			{
				System.out.print("What is the name of outcome " + (i + 1) + "?: ");
				outcomeList.add(input.nextLine());
			}
			for (int i = 0; i < outcomes; i++)
			{
				for (int j = 0; j < outcomes; j++)
				{
					System.out.print("What are the odds of the outcome " + outcomeList.get(j) + " if outcome " + outcomeList.get(i) + " just occurred?: ");
					double currentProbability = Double.parseDouble(input.nextLine());
					transitionMatrix.setItem(j + 1, i + 1, currentProbability);
				}
			}
			System.out.println(transitionMatrix.toString());
			for(int i = 0; i < outcomes; i++)
			{
				System.out.print("What are the odds of the outcome " + outcomeList.get(i) + " currently?: ");
				double currentProbability = Double.parseDouble(input.nextLine());
				currentProbabilityMatrix.setItem(i + 1, 1, currentProbability);
			}
			boolean doneWithChains = false;
			while(!doneWithChains)
			{
				currentProbabilityMatrix = transitionMatrix.dotProduct(currentProbabilityMatrix);
				System.out.println("The probability for the next outcomes are:");
				for(int i = 0; i < outcomes; i++)
				{
					System.out.printf("%s: %.5f \n", outcomeList.get(i), currentProbabilityMatrix.getItem(i + 1, 1));
				}

				boolean hasResponded = false;
				while(!hasResponded)
				{
					System.out.print("Would you like to compute the next outcome? Y/N: ");
					String response = input.nextLine();
					if(response.equals("No") || response.equals("no") || response.equals("N") || response.equals("n"))
					{
						hasResponded = true;
						doneWithChains = true;
					}
					else if(response.equals("Yes") || response.equals("yes") || response.equals("Y") || response.equals("y"))
					{
						hasResponded = true;
					}
				}
			}
			
			boolean hasResponded = false;
			while(!hasResponded)
			{
				System.out.print("Do you want to check another set of probabilities? Y/N: ");
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
