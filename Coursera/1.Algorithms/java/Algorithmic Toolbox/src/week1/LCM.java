package week1;
import java.util.*;

public class LCM {
	private static long lcm(long a, long b) {
		// write your code here
		long resp = a * b;
		return resp / gcd(a, b);
	}

	private static long gcd(long a, long b) {

		long current_mod = a % b;
		while (current_mod != 0) {
			// System.out.println("a=" + a + " b=" +b + " mod="+current_mod );
			a = b;
			b = current_mod;
			current_mod = a % b;
		}

		return b;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		long a = scanner.nextLong();
		long b = scanner.nextLong();

		System.out.println(lcm(a, b));
	}
}
