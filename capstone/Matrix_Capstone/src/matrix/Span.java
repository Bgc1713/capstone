package matrix;

import java.util.Scanner;

public class Span {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
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
		System.out.println(spanMtx.toString());
		spanMtx.reduceToRowEchelonForm();
		System.out.println(spanMtx.toString());
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

	}

}
