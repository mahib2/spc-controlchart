package data;

import java.util.ArrayList;

public interface DataSetIterate 
{
	public ArrayList<DataSetItem> next() throws DataSetException;
	public boolean isEmpty();
}
