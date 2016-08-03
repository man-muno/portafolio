package week4;

import java.util.Scanner;

public class PlacingParentheses {
	private static long getMaximValue(String exp) {
		// write your code here
		int numbers = (exp.length() + 1)/2;

		long[][] max = new long[numbers][numbers];
		long[][] min = new long[numbers][numbers];

		for(int i=0;i<exp.length();i+=2){
			max[i/2][i/2] = Integer.parseInt(exp.substring(i, i+1));
			min[i/2][i/2] = Integer.parseInt(exp.substring(i, i+1));
		}
		
		for(int s=1;s<=numbers;s++){
			for(int i=1;i<=numbers-s;i++){
				int j = i+s;
				long[] minMax = minmax(i-1,j-1,exp,max,min);
				max[i-1][j-1]=minMax[1];
				min[i-1][j-1]=minMax[0];
			}
		}
		
		return max[0][numbers-1];
	}

	private static long[] minmax(int i, int j, String exp, long[][] max, long[][] min) {
		long minValue = Long.MAX_VALUE;
		long maxValue = Long.MIN_VALUE;
		for(int k=i;k<j;k++){
			char operator = exp.charAt(k*2+1);
			long a = eval(max[i][k],max[k+1][j],operator);
			long b = eval(max[i][k],min[k+1][j],operator);
			long c = eval(min[i][k],max[k+1][j],operator);
			long d = eval(min[i][k],min[k+1][j],operator);
			minValue = Math.min(minValue, Math.min(a, Math.min(b, Math.min(c, d))));
			maxValue = Math.max(maxValue, Math.max(a, Math.max(b, Math.max(c, d))));
		}
		
		long[] respArray = {minValue,maxValue};
		
		return respArray;
	}

	private static long eval(long a, long b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else if (op == '*') {
			return a * b;
		} else {
			assert false;
			return 0;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String exp = scanner.next();
		System.out.println(getMaximValue(exp));
	}
}
