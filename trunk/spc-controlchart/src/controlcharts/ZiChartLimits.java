package controlcharts;

import java.util.ArrayList;
import java.util.Iterator;

import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.MeanStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetItem;

public class ZiChartLimits implements GenericChartLimits
{
	protected ArrayList<ArrayList<DataSetItem>> data;
	protected Integer sample_size;
	protected Double standardDeviation;
	protected Double totalAverage;
	
	public ZiChartLimits(Integer size)
	{
		this.init(size);
	}
	
	public ZiChartLimits()
	{
		
	}
	
	protected void init(int size)
	{
		this.sample_size = size;
		this.data = new ArrayList<ArrayList<DataSetItem>>(size);
	}
	
	public void addData(ArrayList<DataSetItem> new_data) 
	{
		this.data.add(new_data);
		
	}

	public Double calculateCentralLine(Double x) 
	{
		return 0.0;
	}

	public Double calculateLowerAdvertenceLimit(Double x) 
	{
		return 0.0;
	}

	public Double calculateLowerControlLimit(Double x) 
	{
		return 0.0;
	}
	//calcular os valores corretos
	public Double calculateUpperAdvertenceLimit(Double x) {
		return 0.0;
	}

	public Double calculateUpperControlLimit(Double x) 
	{
		if(this.standardDeviation==null)
		{
			GenericStatistic statistic = new AverageStatistic();
			this.standardDeviation = this.calculateStandardDeviation(statistic);
		}
		return 5*this.standardDeviation;
	}

	private Double calculateStandardDeviation(GenericStatistic statistic)
	{
		GenericStatistic averange = statistic;
		ArrayList<DataSetItem> dados_medios = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();
		it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = averange.generateStatistic(amostra);
			DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_medios.add(novo_item);
		}
		/***
		 * Guarda a media, caso alguém pergute por ela, ela está aqui. ;)
		 */
		AverageStatistic calculador_media = new AverageStatistic();
		this.totalAverage = calculador_media.generateStatistic(dados_medios);
		
		StandardDeviationStatistic calculador_desvio = new StandardDeviationStatistic(false);
		Double retorno = calculador_desvio.generateStatistic(dados_medios);
		return retorno;
	}
	
	public void setSampleSize(Integer size) 
	{
		this.sample_size = size;
		this.init(size);
		
	}

	public Double getStandardDeviation() 
	{
		return standardDeviation;
	}

	public Double getTotalAverage() 
	{
		return totalAverage;
	}

	public Integer getSample_size() {
		return sample_size;
	}

}
