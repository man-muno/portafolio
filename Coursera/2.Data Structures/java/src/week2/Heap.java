package week2;

import java.util.ArrayList;

import week2.BuildHeap.Swap;

public class Heap<T> {

	private Comparable[] data;
	
	private int limit=0;

	public Heap(int size) {
		data = new Comparable[size];
	}

	private int getLeftChild(int pos) {
		return 2 * pos + 1;
	}

	private int getRightChild(int pos) {
		return 2 * pos + 2;
	}

	private void checkHeapRule(int pos) {
		int leftChildPos = getLeftChild(pos);
		int rightChildPos = getRightChild(pos);
		if (leftChildPos < limit && rightChildPos < limit) {
			int smallestPos = -1;
			if (rightChildPos < limit)
				smallestPos = data[leftChildPos].compareTo(data[rightChildPos]) > 0 ? rightChildPos	: leftChildPos;
			if (data[smallestPos].compareTo(data[pos]) < 0){
				swap(smallestPos, pos);
				checkHeapRule(smallestPos);
			}
		}
	}

	private void swap(int i, int j) {
		Comparable tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public T getPriorityWorker() {
		T response = null;
		if(limit > 0){
			response = (T) data[0];
			data[0] = data[limit];
			data[limit] = null;
			checkHeapRule(0);
		}
		return response;
	}

	public void add(Comparable element) {
		if(limit < data.length){
			data[limit]=element;
			siffUp(limit);
			if(limit != data.length-1)
				limit++;
			
		}
	}
	
	private void siffUp(int pos) {
		int parentPos = getParent(pos);
		if(data[pos].compareTo(data[parentPos]) < 0)
			swap(pos,parentPos);
	}
	
	private int getParent(int pos){
		return pos/2;
	}

	public void load(Comparable element) {
		data[limit]=element;
		if(limit != data.length-1)
			limit++;
	}

}
