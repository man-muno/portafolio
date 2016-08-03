package week3;
import java.io.*;
import java.util.*;

public class Sorting {
	private static Random random = new Random();

	private static int partition3(int[] a, int l, int r) {
		// write your code here

		int start_pivot = l;
		int end_pivot = l;
		int start_bigger = r;
		int pivot = a[l];
		
		for(int i = l+1;i<=start_bigger;i++){
			if(a[i]<pivot){
				swap(a,start_pivot,i);
				start_pivot++;
				end_pivot++;
			} else if(a[i]>pivot){
				swap(a,start_bigger,i);
				i--;
				start_bigger--;
			} else
				end_pivot++;
		}
		
		
		return start_pivot;
	}

	private static int partition2(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for (int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				swap(a, i, j);
			}
		}
		swap(a, l, j);
		return j;
	}

	private static void swap(int[] a, int l, int j) {
		int t = a[l];
		a[l] = a[j];
		a[j] = t;
	}

	private static void randomizedQuickSort(int[] a, int low, int high) {
		if (low >= high) {
			return;
		}
		int randomIndex = random.nextInt(high - low + 1) + low;
		swap(a, low, randomIndex);
		// use partition3
		int pivot = partition3(a, low, high);
		randomizedQuickSort(a, low, pivot - 1);
		randomizedQuickSort(a, pivot + 1, high);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		randomizedQuickSort(a, 0, n - 1);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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
