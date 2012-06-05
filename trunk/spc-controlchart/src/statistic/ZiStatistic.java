package statistic;

import controlcharts.ZiChartLimits;
import data.DataSetItem;
import java.util.ArrayList;

public class ZiStatistic implements GenericStatistic {
	private Double constante;
	private Double average;
	private ZiChartLimits chartLimits;
	private Double zi_anterior = 0.0;
	int it = 0;

	public ZiStatistic (Double constante, ZiChartLimits chartLimits){
		this.constante = constante;		
		this.chartLimits = chartLimits; 
	}

	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		int tamanho = sample.size();
		this.average = this.chartLimits.getTotalAverage();

		//Calcular Zi
		Double retorno = 0.0;		
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			Double x = sample_part.getY();
			double zi=0.0;
			if (it==0)
			{
				it++;
				zi_anterior = (constante*x)+(1-constante)*average;
				retorno = zi_anterior;
			}			
			else 
			{
				zi = (constante*x)+(1-constante)*zi_anterior;	
				retorno = zi;
			}			
		}

		if(sample==null)
		{
			return null;
		}	
		this.zi_anterior = retorno.doubleValue();
		return retorno;
	}		
}
