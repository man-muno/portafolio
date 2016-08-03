package week3;
import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
        	if(a[left] > a[right]){
        		swap(a, left, right);
            	numberOfInversions++;
        	}
            return numberOfInversions;
        }
        int average = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, average);
        numberOfInversions += getNumberOfInversions(a, b, average+1, right);
        numberOfInversions += merge(a,left,average,right);
        return numberOfInversions;
    }

	private static long merge(int[] a, int left, int average, int right) {
	    int[] lowHalf = new int[(average-left)+1];
	    int[] highHalf = new int[right-average];
	    long numberOfInversions = 0;
	    int k = left;
	    int i=0;
	    int j=0;
	    for (; k <= average; i++, k++)
	        lowHalf[i] = a[k];
	    for (; k <= right; j++, k++)
	        highHalf[j] = a[k];
	    
	    
	    k = left;
	    i=0;
	    j=0;
	    
	    while(i < lowHalf.length && j < highHalf.length){
	        if(lowHalf[i] < highHalf[j]){
	            a[k] = lowHalf[i];
	            i++;
	        } else {
	            a[k] = highHalf[j];
	            j++;
	            numberOfInversions += ((average-left)+1)-i;
	        }
	        k++;
	    }
	    
	    while(i<lowHalf.length){
	        a[k] = lowHalf[i];
	        i++;
	        k++;
	    }
	    while(j<highHalf.length){
	        a[k] = highHalf[j];
	        j++;
	        k++;
	    }
	    
	    
	    
		return numberOfInversions;
	}

	private static void swap(int[] a, int left, int right) {
		int temp = a[right];
		a[right] = a[left];
		a[left] = temp;
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length-1));
    }
}

