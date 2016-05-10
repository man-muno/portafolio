package fml.tum.model; 

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Properties;

import fml.tum.utils.PropertiesValues;

public class Shelf extends Observable{
	
	private int desiredMastHeightEmptyA;
	private int desiredMastHeightEmptyB;
	private int desiredMastHeightEmptyC;

	private int desiredMastHeightLoadedA;
	private int desiredMastHeightLoadedB;
	private int desiredMastHeightLoadedC;
	
	private int desiredGapDelta;
	private int fistColumnDesiresDropPoint;
	private int secondColumnDesiresDropPoint;
	private int thirdColumnDesiresDropPoint;
	
	private Map<Key,ShelfSpace> spaces;

	public Shelf(Properties properties)
	{
		desiredMastHeightEmptyA = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_EMPTY_A));
		desiredMastHeightEmptyB = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_EMPTY_B));
		desiredMastHeightEmptyC = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_EMPTY_C));
		
		desiredMastHeightLoadedA = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_LOADED_A));
		desiredMastHeightLoadedB = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_LOADED_B));
		desiredMastHeightLoadedC = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_MAST_HEIGHT_LOADED_C));
		
		desiredGapDelta  = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_GAP_DELTA));
	
		fistColumnDesiresDropPoint = Integer.parseInt(properties.getProperty(PropertiesValues.FIST_COLUMN_DESIRED_DROP_POINT));
		secondColumnDesiresDropPoint = Integer.parseInt(properties.getProperty(PropertiesValues.SECOND_COLUMN_DESIRED_DROP_POINT));
		thirdColumnDesiresDropPoint = Integer.parseInt(properties.getProperty(PropertiesValues.THIRD_COLUMN_DESIRED_DROP_POINT));
		
		spaces = new HashMap<Key,ShelfSpace>();
		//First row
		Key ssa1 = new Key('A', 1);
		spaces.put(ssa1, new ShelfSpace());
		Key ssa2 = new Key('A', 2);
		spaces.put(ssa2, new ShelfSpace());
		Key ssa3 = new Key('A', 3);
		spaces.put(ssa3, new ShelfSpace());
		
		//Second row
		Key ssb1 = new Key('B', 1);
		spaces.put(ssb1, new ShelfSpace());
		Key ssb2 = new Key('B', 2);
		spaces.put(ssb2, new ShelfSpace());
		Key ssb3 = new Key('B', 3);
		spaces.put(ssb3, new ShelfSpace());
				
		//First row
		Key ssc1 = new Key('C', 1);
		spaces.put(ssc1, new ShelfSpace());
		Key ssc2 = new Key('C', 2);
		spaces.put(ssc2, new ShelfSpace());
		Key ssc3 = new Key('C', 3);
		spaces.put(ssc3, new ShelfSpace());
	}
	
	/**
	 * Returns the desired mast height for a given shelf row. The height is defined on the constants DESIRED_HEIGHT_A, DESIRED_HEIGHT_B, DESIRED_HEIGHT_C.
	 * @param shelfRow 'A' or 'B' or 'C'
	 * @return -1 for unfamiliar rows
	 */
	public int getDesiredMastHeight(char shelfRow, boolean isLoaded) {
		int height = -1;
		switch (shelfRow) {
		case 'A':
			
			height = isLoaded ? desiredMastHeightLoadedA : desiredMastHeightEmptyA ;
			break;
		case 'B':
			height = isLoaded ? desiredMastHeightLoadedB : desiredMastHeightEmptyB;
			break;
		case 'C':
			height = isLoaded ? desiredMastHeightLoadedC : desiredMastHeightEmptyC;
			break;			
		default:
			break;
		}

		return height;
	}

	/**
	 * Returns the desired point in which the pallet can enter given the column. The distances are define on the constants FIST_COLUMN_DESIRED_DROP_POINT, SECOND_COLUMN_DESIRED_DROP_POINT, THIRD_COLUMN_DESIRED_DROP_POINT 
	 * @param column 1,2,3.
	 * @return -1 for an unfamiliar column
	 */
	public int getDesiredDropPoint(int column) {
		switch (column) {
		case 1:
			return fistColumnDesiresDropPoint;
		case 2:
			return secondColumnDesiresDropPoint;
		case 3:
			return thirdColumnDesiresDropPoint;
		}
		return -1;
	}

	/**
	 *  True if the currentX is with in the DESIRED_GAP_DELTA to the left or to the right of the middle point of said column 
	 * @param column Column 1 or 2 or 3
	 * @param currentX Positive integer
	 * @return 
	 */
	public boolean isCorrectColumn(int column, int currentX) {
		int middle = getDesiredDropPoint(column);
		System.out.println("Current X: " + currentX + " Instruction is go to column " + column + " on the middle point " + middle + ""  );
		return Math.abs(middle-currentX)<=desiredGapDelta;
	}	
}
