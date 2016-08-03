package week3;
import java.util.Random;
import java.util.Scanner;

public class PointsAndSegments {

	private static Random random = new Random();

	private static int[] naiveCountSegments(int[] starts, int[] ends,
			int[] points) {
		int[] cnt = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < starts.length; j++) {
				if (starts[j] <= points[i] && points[i] <= ends[j]) {
					cnt[i]++;
				}
			}
		}
		return cnt;
	}

	private static void swap(Pair[] a, int l, int j) {
		Pair t = a[l];
		a[l] = a[j];
		a[j] = t;

	}

	private static void randomizedQuickSort(Pair[] a, int low, int high) {
		if (low >= high) {
			return;
		}
		int randomIndex = random.nextInt(high - low + 1) + low;
		swap(a, low, randomIndex);
		// use partition3
		int pivot = partition3(a,low, high);
		randomizedQuickSort(a, low, pivot - 1);
		randomizedQuickSort(a, pivot + 1, high);
	}

	private static int partition3(Pair[] a, int l, int r) {
		// write your code here
		int start_pivot = l;
		int end_pivot = l;
		int start_bigger = r;
		int pivot = a[l].getNumber();

		for (int i = l + 1; i <= start_bigger; i++) {
			if (a[i].getNumber() < pivot) {
				swap(a, start_pivot, i);
				start_pivot++;
				end_pivot++;
			} else if (a[i].getNumber() > pivot) {
				swap(a,start_bigger, i);
				i--;
				start_bigger--;
			} else
				end_pivot++;
		}

		return start_pivot;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		int total= n+n+m;
		Pair[] pairs = new Pair[total];
		for (int i = 0; i < n*2; i+=2) {
			pairs[i] = new PointsAndSegments(). new Pair(scanner.nextInt(),Pair.START);
			pairs[i+1] = new PointsAndSegments(). new Pair(scanner.nextInt(),Pair.END);
		}
		for (int i = n+n; i < total; i++) {
			pairs[i] = new PointsAndSegments(). new Pair(scanner.nextInt(),Pair.POINT);
		}
		// use fastCountSegments
		randomizedQuickSort(pairs, 0, pairs.length - 1);
		int[] cnt = fastCountSegments(pairs,m);
		for (int x : cnt) {
			System.out.print(x + " ");
		}
	}
	
	private static int[] fastCountSegments(Pair[] pairs, int length) {

		int[] cnt = new int[length];
		int open_segment = 0;
		int points_counter = 0;
		
		for (int i = 0; i < pairs.length; i++) {
			Pair temp = pairs[i];
			if(temp.getType() == Pair.START)
				open_segment++;
			else if(temp.getType() == Pair.END)
				open_segment--;
			else {
				cnt[points_counter++]=open_segment;
			}
		}
		
		return cnt;
	}

	public class Pair{

		private static final int START = -1;
		private static final int END = 0;
		private static final int POINT = 1;	
		private int number;
		private int type;
		
		public Pair(int number, int type)
		{
			this.number = number;
			this.type = type;
		}
		
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "(" + number + "," + type+")";
		}
		
		
	}
	
}
