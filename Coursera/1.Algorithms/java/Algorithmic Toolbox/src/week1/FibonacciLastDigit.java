package week1;
import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigit(int n) {
        if (n <= 1)
            return n;
        
		int[] fibNumbers = new int[n];

		fibNumbers[0] = 1;
		fibNumbers[1] = 1;

		for (int i = 2; i < fibNumbers.length; i++) {
			fibNumbers[i] = (fibNumbers[i - 1] + fibNumbers[i - 2]) % 10;
		}

		return fibNumbers[n - 1];
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

