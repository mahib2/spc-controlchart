package statistic;

import java.util.ArrayList;

import data.DataSetItem;

public class MovingRangeStatistic implements GenericStatistic 
{
	private Double valor_anterior = null;
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		if(sample==null)
		{
			return null;
		}
		int tamanho = sample.size();
		if(tamanho>0)
		{
			Double valor = sample.get(0).getY();
			if(this.valor_anterior==null)
			{
				this.valor_anterior = valor;
				return 0.0;
			}
			else
			{
				Double retorno = this.valor_anterior - valor;
				this.valor_anterior = valor;
				return (retorno<0)?(-retorno):retorno;
			}
		}
		else
		{
			return 0.0;
		}
	}
	
}
