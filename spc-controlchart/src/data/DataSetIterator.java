package data;

import java.util.ArrayList;

public interface DataSetIterator 
{
	public ArrayList<DataSetItem> next() throws DataSetException;
	public boolean isEmpty();
}
