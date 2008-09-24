package controlcharts;

import java.util.ArrayList;
import java.util.Iterator;

import constants.Cn;


import statistic.AverageStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetItem;

public class StandardDeviationChartLimits extends GenericChartLimitsBase
{
	private Double term1; 
	private Double term2;
	
	public StandardDeviationChartLimits(Integer size) 
	{
		super(size);
	}
	
	public StandardDeviationChartLimits() 
	{
		
	}
	
	@Override
	protected Double calculateTerm1() 
	{
		if(this.term1==null)
		{
			this.term1 = this.calculateAverageStandardDeviation(); 						
			
		}
		return term1; 
	}

	@Override
	protected Double calculateTerm2() 
	{
		if(this.term2==null)
		{
			Double cn = Cn.calculate(this.sample_size);
			Double S_barra = this.calculateAverageStandardDeviation();
			this.term2 = (S_barra/cn)*(Math.sqrt(1 - Math.pow(cn,2.0)));		
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
	
	private Double calculateAverageStandardDeviation()
	{
		StandardDeviationStatistic calculador_desvio_padrao = new StandardDeviationStatistic(true);
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = calculador_desvio_padrao.generateStatistic(amostra);
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
