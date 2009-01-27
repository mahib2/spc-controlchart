package controlcharts;

import java.util.ArrayList;
import java.util.Iterator;

import constants.D2;
import constants.D3;
import constants.Hn;


import statistic.AmplitudeStatistic;
import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetItem;

public class AmplitudeChartLimits extends GenericChartLimitsBase
{
	private Double term1; 
	private Double term2;		
		
	protected Double calculateTerm1() 
	{
		if(this.term1==null)
		{
			AmplitudeStatistic amplitude = new AmplitudeStatistic();
			this.term1 = this.calculateAverage(amplitude); 						
			
		}
		return term1; 
	}

	@Override
	protected Double calculateTerm2() 
	{
		if(this.term2==null)
		{
			//verifica os valores da tabela 
			Double d2 = D2.calculate(this.sample_size);
			Double d3 = D3.calculate(this.sample_size);

			//calculo do termo2
			AmplitudeStatistic amplitude = new AmplitudeStatistic();
			Double R_barra = this.calculateAverage(amplitude);
			this.term2 =  d3*(R_barra/d2);
			
			
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
		GenericStatistic median = statistic;
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();
		it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = median.generateStatistic(amostra);
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