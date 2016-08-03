package week4;

import java.util.*;

class EditDistance {
	public static int EditDistance(String vertical, String horizontal) {
		// write your code here

		int[][] resp = new int[vertical.length()+1][horizontal.length()+1];
		
		for(int i=0;i<=vertical.length();i++){
			for(int j=0;j<=horizontal.length();j++){
				if(j==0){
					resp[i][j]=i;
				} 
				if(i==0){
					resp[i][j]=j;
				}
				if (i!=0 && j!=0){
					int insertion = resp[i][j-1]+1;
					int deletion = resp[i-1][j]+1;
					if(vertical.charAt(i-1) == horizontal.charAt(j-1)){
						resp[i][j] = Math.min(Math.min(insertion, deletion),resp[i-1][j-1]);
					} else {
						resp[i][j]= Math.min(Math.min(insertion, deletion),resp[i-1][j-1]+1);
					}	
				}
			}
		}
		
		return resp[vertical.length()][horizontal.length()];
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		String t = scan.next();

		System.out.println(EditDistance(s, t));
	}

}