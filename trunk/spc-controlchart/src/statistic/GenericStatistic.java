package statistic;

import java.util.ArrayList;

import data.DataSetItem;

public interface GenericStatistic 
{
	public Double generateStatistic(ArrayList<DataSetItem> sample);	
}
