package week1;
import java.util.*;

public class FibonacciHuge {

	private static long getFibonacciHuge(long n, long m) {

		if (n <= 1)
			return n;

		long[] fibNumbers = new long[(int) n];
		long[] pisano = new long[(int) n];

		fibNumbers[0] = 0;
		fibNumbers[1] = 1;
		pisano[0] = 0;
		pisano[1] = 1;
		int frequency = 0;

		//System.out.println("i " + 0 + " " + " pisano " + pisano[0]);
		//System.out.println("i " + 1 + " " + " pisano " + pisano[1]);

		//System.out.println("i " + 0 + " " + " fib " + fibNumbers[0]);
		//System.out.println("i " + 1 + " " + " fib " + fibNumbers[1]);

		for (int i = 2; i < fibNumbers.length && frequency == 0; i++) {
			fibNumbers[i] = (fibNumbers[i - 1] + fibNumbers[i - 2]);
			pisano[i] = fibNumbers[i] % m;
			//System.out.println("i " + i + " " + " pisano " + pisano[i]);
			//System.out.println("i " + i + " " + " fib " + fibNumbers[i]);

			if (i > 3 && pisano[i] == 1 && pisano[i - 1] == 1
					&& pisano[i - 2] == 0) {
				frequency = i - 2;
			}
		}

		//System.out.println("n=" + n + " freq= " + frequency);
		int pos = (int) (n % frequency);
		//System.out.println(pos);
		return pisano[pos];

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long m = scanner.nextLong();
		System.out.println(getFibonacciHuge(n, m));
	}

}
