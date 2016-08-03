package week2;
import java.util.*;

public class CoveringSegments {

	private static int[] optimalPoints(Segment[] segments) {
		// write your code here
		quickSort(segments, 0, segments.length - 1);
		ArrayList<Integer> resp = new ArrayList<Integer>();
		for (int i=0;i<segments.length;i++){
			Segment toCheckSegmentOne = segments[i];
			if(toCheckSegmentOne != null){
				int leastRightEndpoint = toCheckSegmentOne.end;
				for (int j=i+1;j<segments.length;j++) {
					Segment toCheckSegmentTwo = segments[j];
					if(toCheckSegmentTwo.start <= leastRightEndpoint && leastRightEndpoint <= toCheckSegmentTwo.end)
						segments[j] = null;
				}
				resp.add(new Integer(leastRightEndpoint));
			}
			
		}
		int[] points = new int[resp.size()];
		for(int i=0;i<resp.size();i++){
			points[i]=resp.get(i);
		}
			
		return points;
	}

	private static class Segment {
		int start, end;

		Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			int start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}

		int[] points = optimalPoints(segments);
		System.out.println(points.length);
		for (int point : points) {
			System.out.print(point + " ");
		}
	}

	private static void quickSort(Segment[] array, int p, int r) {
		if (r - p > 0) {
			int pivot = partition(array, p, r);
			quickSort(array, p, pivot - 1);
			quickSort(array, pivot + 1, r);
		}
	}

	private static int partition(Segment[] array, int p, int r) {
		int q = p;
		for (int j = p; j < r; j++) {
			if (array[j].end <= array[r].end) {
				swap(array, j, q);
				q++;
			}
		}
		swap(array, r, q);
		return q;
	}

	private static void swap(Segment[] array, int firstIndex, int secondIndex) {
		Segment temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}

}
