package week3;
import java.util.*;
import java.io.*;

public class MajorityElement {
	
	
	private static int getMajorityElement(int[] a) {
        //Get middle point
		int middle = a.length/2;
		
		//search on the first half of the array if all elments are equal.
		boolean isEqual = true;
		boolean foundMatch = false;
		int firstElement = a[0];
		for(int i=0; i < middle && !isEqual;i++){
			if(a[i]!=firstElement)
				isEqual = false;
		}
		
		if(isEqual){
			//all the elements are equal to firstElement. It needs to look for firstElement on the second half
			for(int i=middle; i < a.length	&& !foundMatch;i++){
				if(a[i]==firstElement)
					foundMatch = true;
			}
		} else {
			// It needs to repeat the process with the second half
			firstElement = a[middle];
			for(int i=middle; i < a.length	&& !isEqual;i++){
				if(a[i]!=firstElement)
					isEqual = false;
			}
			for(int i=0; i < middle && !isEqual;i++){
				if(a[i]==firstElement)
					foundMatch = true;
			}
		}
		
		
        //write your code here
        return foundMatch ? 1 : -1;
    }
	
    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        //write your code here
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //if (getMajorityElement(a, 0, a.length) != -1) {
        if (getMajorityElement(a) != -1) {	
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

