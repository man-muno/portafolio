package week3;
import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length;
        //write your code here
        return binarySearch(a,0,a.length-1,x);
    }

    private static int binarySearch(int[] a, int start, int end, int x) {
    	
    	int middle = (start+end)/2;
        if( a[middle]==x)
        	return middle;
        if(end <= start )
        	return -1;
        else if(a[middle]>x)
        	return binarySearch(a, start, middle-1, x);
        else if(a[middle]<x)
        	return binarySearch(a,middle+1,end,x);
        return -1;
	}
    
    private static int binarySearchIt(int[] a, int x) {
    	int start = 0;
    	int end = a.length -1;
    	int pos = -1;
    	while(end > start){
    		int middle = (start+end)/2;
            if( a[middle]==x)
            	return middle;
            else if(a[middle]>x)
            	end = middle-1;
            else if(a[middle]<x)
            	start = middle+1;   
    	}
        return -1;
	}

	static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearchIt(a, b[i]) + " ");
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
