package controlcharts;

import java.util.ArrayList;
import java.util.Iterator;

import constants.D2;
import constants.Hn;


import statistic.AmplitudeStatistic;
import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetItem;

public class AverageChartLimits extends GenericChartLimitsBase
{
	private Double term1; 
	private Double term2;		
		
	protected Double calculateTerm1() 
	{
		if(this.term1==null)
		{
			AverageStatistic average = new AverageStatistic();
			this.term1 = this.calculateAverage(average); 						
			
		}
		return term1; 
	}

	@Override
	protected Double calculateTerm2() 
	{
		if(this.term2==null)
		{
			int n = this.sample_size;
			double n_raiz = Math.sqrt(n); 
			Double d2 = D2.calculate(this.sample_size);
//			Double cn = Cn.calculate(this.sample_size);
			// colocar a escolha do estimador aqui!!!
			
			//calculo do termo2
			AmplitudeStatistic amplitude = new AmplitudeStatistic();
			Double R_barra = this.calculateAverage(amplitude);
			this.term2 =  R_barra/(d2*n_raiz);
			
			//testes Sbarra/cn
//			StandardDeviationStatistic desvio = new StandardDeviationStatistic(true);
//			Double S_barra = this.calculateAverageMedian(desvio);
//			this.term2 = S_barra/(cn*n_raiz);
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