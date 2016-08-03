package week4;

import java.util.*;

public class Knapsack {

	// A utility function that returns maximum of two integers
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// Returns the maximum value that can be put in a knapsack of capacity W
	static int knapSack(int totalWeight, int weights[], int values[]) {
		int resp[][] = new int[weights.length + 1][totalWeight + 1];

		for (int i = 0; i <= weights.length; i++) {
			for (int w = 0; w <= totalWeight; w++) {
				if (i != 0 && w != 0){
					//weight to analyze
					int weight = weights[i - 1];
					//check that the new weight is not bigger than the current weight w
					if (weight <= w){
						//Get the previous value for the total weight minus the weight
						int previousOptimizedValue = resp[i - 1][w - weight];
						//Add to that value the current value
						int previousOptimizedValuePlusNewValue = previousOptimizedValue + values[i - 1];
						//select max between the previous value and the previous 
						resp[i][w] = max(previousOptimizedValuePlusNewValue ,resp[i - 1][w]);
					} else
						resp[i][w] = resp[i - 1][w];
				}
			}
		}

		return resp[weights.length][totalWeight];
	}

	// Driver program to test above function
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int total_weight, n;
		total_weight = scanner.nextInt();
		n = scanner.nextInt();
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
		}
		System.out.println(knapSack(total_weight, weights, weights));
	}

}
