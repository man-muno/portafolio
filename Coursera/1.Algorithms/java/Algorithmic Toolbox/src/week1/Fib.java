package week1;
import java.util.Scanner;

public class Fib {
	private static long calc_fib(int n) {
		if (n <= 1)
			return n;

		long[] fibNumbers = new long[n];

		fibNumbers[0] = 1;
		fibNumbers[1] = 1;

		for (int i = 2; i < fibNumbers.length; i++) {
			fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];

			// System.out.println(fibNumbers[i]);
		}

		return fibNumbers[n - 1];
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println(calc_fib(n));
	}
}
