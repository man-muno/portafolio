package week2;
import java.util.Scanner;

public class FractionalKnapsack {
	
	private static double[] densities;
	
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        
        densities = new double [values.length];
        double value = 0;
              
        for(int i=0;i<values.length;i++){
        	densities[i] = values[i]/weights[i];
        	System.out.println(densities[i]);
        }
        
        boolean full = false;
        while(!full){
        	int greatestDensityPos = getGreatestDensityPos(densities);
        	//No more available things to distribute
        	if(greatestDensityPos==-1)
        		full = true;
        	//capacity reached
        	else if(capacity == 0)
        		full = true;
        	else {
            	double deltaValue = values[greatestDensityPos];
            	double deltaWeight = weights[greatestDensityPos];
            	//check to see weather to pack all the biggest available or just a part
            	if(capacity >= deltaWeight){
            		capacity -= deltaWeight;
            		value += deltaValue;
            	} else {
            		double density = deltaValue/deltaWeight;
            		value += (density*capacity);
            		full = true;
            	}
        	}
        }
        
        return value;
    }

    private static int getGreatestDensityPos(double[] densities) {
    	double biggest = -1;
		int pos = -1;
		for (int i = 0; i < densities.length; i++) {
			if(densities[i]>biggest){
				biggest = densities[i];
				pos = i;
			}
		}
		if (pos!=-1)
			densities[pos]=-1;
		return pos;
	}

	public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
