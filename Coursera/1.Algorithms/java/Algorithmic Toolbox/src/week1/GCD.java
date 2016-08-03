package week1;
import java.util.*;

public class GCD {
	private static int gcd(int a, int b) {

		int current_mod = a % b;
		while(current_mod != 0){
			//System.out.println("a=" + a + " b=" +b + " mod="+current_mod );
			a = b;
			b = current_mod;
			current_mod = a % b;
		}
		
		return b;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(gcd(a, b));
	}
}
