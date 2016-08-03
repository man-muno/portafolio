package week4;

import java.util.*;

public class PrimitiveCalculator {

	private static int[] steps;

	private static int[] operations;
	
	private static void optimal_sequence2(int n) {

		int i = 1;
		while (i <= n) {
			if (steps[i] == 0) {
				int mult3 = Integer.MAX_VALUE;
				int mult2 = Integer.MAX_VALUE;
				int plus1 = Integer.MAX_VALUE;
				
				if (i % 3 == 0)
					mult3 = steps[i / 3];
				if (i % 2 == 0)
					mult2 = steps[i / 2];
				plus1 = steps[i - 1];

				if (mult3 <= mult2 && mult3 <= plus1) {
					// mult3 is lower than the other two
					steps[i] = mult3 + 1;
					operations[i] = i / 3;
				} else if (mult2 <= mult3 && mult2 <= plus1) {
					// mult2 is lower than the other two
					steps[i] = mult2 + 1;
					operations[i] = i / 2;
				} else {
					// sum1 is the smallest
					steps[i] = plus1 + 1;
					operations[i] = i -1;
				}
			}

			i++;

		}
	}

	private static List<Integer> optimal_sequence(int n) {
		List<Integer> sequence = new ArrayList<Integer>();
		while (n >= 1) {
			sequence.add(n);
			if (n % 3 == 0) {
				n /= 3;
			} else if (n % 2 == 0) {
				n /= 2;
			} else {
				n -= 1;
			}
		}
		Collections.reverse(sequence);
		return sequence;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		steps = new int[n + 1];
		operations = new int[n + 1];
		steps[0] = -1;
		steps[1] = 1;
		steps[2] = 1;
		steps[3] = 1;
		
		operations[0] = -1;
		operations[1] = -1;
		operations[2] = 1;
		operations[3] = 1;
		optimal_sequence2(n);
		List<Integer> sequence = getValues(n);
		
		System.out.println(sequence.size() - 1); for (Integer x : sequence) {
		System.out.print(x + " "); }
		 
	}

	private static List<Integer> getValues(int n) {
		List<Integer> sequence = new ArrayList<Integer>();
		while (n >= 1) {
			sequence.add(n);
			n = operations[n];
		}
		Collections.reverse(sequence);
		return sequence;
	}

}
