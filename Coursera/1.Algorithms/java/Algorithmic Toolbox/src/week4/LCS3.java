package week4;

import java.util.*;

public class LCS3 {

    private static int lcs3(int[] a, int[] b, int[] c) {
        //Write your code here
        return Math.min(Math.min(a.length, b.length), c.length);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        
        
        System.out.println(alligmentScore(a, b,c));
    }
    
    
	public static int alligmentScore(int[] seq1, int[] seq2, int[] seq3) {
		// write your code here

		
		int[][] resp1and2 = new int[seq1.length+1][seq2.length+1];
		int[][] resp1and3 = new int[seq1.length+1][seq3.length+1];
		
		
		for(int i=0;i<=seq1.length;i++){
			for(int j=0;j<=seq2.length;j++){
				if(j==0){
					resp1and2[i][j]=i;
				} 
				if(i==0){
					resp1and2[i][j]=j;
				}
				if (i!=0 && j!=0){
					int insertion = resp1and2[i][j-1]+1;
					int deletion = resp1and2[i-1][j]+1;
					if(seq1[i-1] == seq2[j-1]){
						resp1and2[i][j] = Math.min(Math.min(insertion, deletion),resp1and2[i-1][j-1]);
					} else {
						resp1and2[i][j]= Math.min(Math.min(insertion, deletion),resp1and2[i-1][j-1]+1);
					}	
				}
			}

			for(int j=0;j<=seq3.length;j++){
				if(j==0){
					resp1and3[i][j]=i;
				} 
				if(i==0){
					resp1and3[i][j]=j;
				}
				if (i!=0 && j!=0){
					int insertion = resp1and3[i][j-1]+1;
					int deletion = resp1and3[i-1][j]+1;
					if(seq1[i-1] == seq3[j-1]){
						resp1and3[i][j] = Math.min(Math.min(insertion, deletion),resp1and3[i-1][j-1]);
					} else {
						resp1and3[i][j]= Math.min(Math.min(insertion, deletion),resp1and3[i-1][j-1]+1);
					}	
				}
			}
			
		
		}
		
		return Math.min(resp1and2[seq1.length][seq2.length],resp1and3[seq1.length][seq3.length]);
	}
    
}

