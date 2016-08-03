package week2;
import java.util.*;

public class DotProduct {
    private static long minDotProduct(int[] a, int[] b) {
        //write your code here
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i] * b[a.length - i -1 ];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        quickSort(a,0,a.length-1);
        quickSort(b,0,b.length-1);
        System.out.println(minDotProduct(a, b));
    }

	private static void quickSort(int[] array, int p, int r){
		if(r-p > 0){
			int pivot = partition(array,p,r);
	        quickSort(array,p,pivot-1);
	        quickSort(array,pivot+1,r);
		}
	}

	private static int partition(int[] array, int p, int r) {
	    int q = p;
	    for(int j=p;j<r;j++){
	        if(array[j]<=array[r]){
	            swap(array,j,q);
	            q++;
	        }
	    }
	    swap(array,r,q);
	    return q;
	}

	private static void swap(int[] array, int firstIndex, int secondIndex) {
	    int temp = array[firstIndex];
	    array[firstIndex] = array[secondIndex];
	    array[secondIndex] = temp;
	}

    
}

