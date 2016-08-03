package week4;

import java.util.*;

public class CopyOfKnapsack {

	private static int[][] resp;

	static int optimalWeight2(int total_weight, int[] weights) {
		for (int i = 1; i <= weights.length; i++) {
			for (int w = 1; w <= total_weight; w++) {
				if (i != 0 && w != 0) {
					// get value from previous iteration of available weights
					int previous = resp[w][i - 1];
					// Get max value between
					int previousPlusNew = previous + weights[i - 1];
					int newWeight = weights[i - 1];
					int val = 0;
					if (newWeight > w)
						// New weight alone exceeds current allowed knapsack
						// weight
						val = previous;
					else if (previousPlusNew <= w)
						// previous plus new is carry-able
						val = previousPlusNew;
					else
						val = newWeight;
					resp[w][i] = val;
				}
			}
		}

		// write you code here
		int result = 0;
		for (int i = 0; i < weights.length; i++) {
			if (result + weights[i] <= total_weight) {
				result += weights[i];
			}
		}
		return result;
	}

	static int optimalWeight(int total_weight, int[] weights) {

		for (int w = 1; w <= total_weight; w++) {
			for (int i = 1; i <= weights.length; i++) {
				if (i != 0 && w != 0) {
					// get value from previous iteration of available weights
					int previous = resp[w][i - 1];
					// Get max value between
					int newWeight = weights[i - 1];
					int previousPlusNew = previous + newWeight;
					int val = 0;
					if (newWeight > w)
						// New weight alone exceeds current allowed knapsack
						// weight
						val = previous;
					else if (previousPlusNew <= w)
						// previous plus new is carry-able
						val = previousPlusNew;
					else
						val = newWeight;
					resp[w][i] = val;
				}
			}
		}


		return resp[total_weight][weights.length];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int total_weight, n;
		total_weight = scanner.nextInt();
		n = scanner.nextInt();
		int[] weights = new int[n];
		resp = new int[total_weight + 1][n + 1];
		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
		}
		System.out.println(optimalWeight(total_weight, weights));
	}
}
