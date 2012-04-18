package controlcharts;

import java.util.ArrayList;
import java.util.Iterator;

import constants.D2;
import constants.D3;



import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.MovingRangeStatistic;


import data.DataSetItem;

public class XIndividualChartLimits extends GenericChartLimitsBase
{
	private Double term1; 
	private Double term2;		
		
	protected Double calculateTerm1() 
	{
		if(this.term1==null)
		{
			MedianStatistic x = new MedianStatistic();
			this.term1 = this.calculateAverage(x); 						
			
		}
		return term1; 
	}

	@Override
	protected Double calculateTerm2() 
	{
		if(this.term2==null)
		{
			Double d2 = D2.calculate(2);
						
			//calculo do termo2
			MovingRangeStatistic mr = new MovingRangeStatistic();
			Double media_MR = this.calculateAverage(mr);
			this.term2 =  media_MR/d2;
		}
		return this.term2;
	}

	public void addData(ArrayList<DataSetItem> new_data) 
	{
		this.data.add(new_data);
	}

	public void setSampleSize(Integer size) 
	{
		this.sample_size = size;
		this.init(size);
	}
		
	private Double calculateAverage(GenericStatistic statistic)
	{
		GenericStatistic moving_range = statistic;
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();
		it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = moving_range.generateStatistic(amostra);
			DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);
		}
		AverageStatistic calculador_media = new AverageStatistic();
		Double retorno = calculador_media.generateStatistic(dados_para_media);
		return retorno;
	}
}