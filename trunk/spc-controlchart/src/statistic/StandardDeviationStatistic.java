package statistic;

import java.util.ArrayList;

import data.DataSetItem;

public class StandardDeviationStatistic implements GenericStatistic 
{
	private VarianceStatistic variance_generator;
	
	
	public StandardDeviationStatistic(boolean is_amostral)
	{
		this.variance_generator = new VarianceStatistic(is_amostral);		
	}
	
	
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		Double variance = this.variance_generator.generateStatistic(sample);
		return Math.sqrt(variance);
	}

}
