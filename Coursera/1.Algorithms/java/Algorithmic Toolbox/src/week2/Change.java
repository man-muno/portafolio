package week2;
import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
    	
    	int coin1 = 1;
    	int coin5 = 5;
    	int coin10 = 10;
    	
    	int total10Coins = n/coin10;
    	int coin10Value = total10Coins*coin10;
    	int total5Coins = (n-coin10Value)/coin5;
    	int coin5Value = total5Coins * coin5;
    	int total1coins= (n-coin10Value-coin5Value)/coin1;
    	
        return total10Coins+total5Coins+total1coins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}

