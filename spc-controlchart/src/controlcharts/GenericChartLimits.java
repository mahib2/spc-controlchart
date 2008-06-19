package controlcharts;

import java.util.ArrayList;

import data.DataSetItem;

public interface GenericChartLimits 
{
	public void addData(ArrayList<DataSetItem> new_data);
	public void setSampleSize(Integer size);
	public Double calculateCentralLine();
	public Double calculateUpperControlLimit();
	public Double calculateLowerControlLimit();
	public Double calculateUpperAdvertenceLimit();
	public Double calculateLowerAdvertenceLimit();
}
