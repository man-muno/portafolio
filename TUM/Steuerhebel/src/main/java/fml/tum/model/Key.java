package fml.tum.model;

public class Key implements Comparable<Key>{

    private final char row;
    private final int column;

    public Key(char row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return (row == key.row) && column == key.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

	@Override
	public int compareTo(Key o) {
	     if(o==this)
	    	 return 0;
	     Key cp = (Key)o;
	     int i = Integer.compare(column, cp.column);
	     if(i!=0) 
	    	 return i;
	     return Character.compare(row, cp.row);
	}

	@Override
	public String toString() {
		return "Key [row=" + row + ", column=" + column + "]";
	}
    


}