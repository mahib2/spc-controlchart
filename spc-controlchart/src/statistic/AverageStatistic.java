package statistic;

import java.util.ArrayList;

import data.DataSetItem;
import data.types.TypeUtilities;

public class AverageStatistic implements GenericStatistic 
{

	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		if(sample==null)
		{
			return null;
		}
		int tamanho = sample.size();
		Double retorno = null;
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			retorno = TypeUtilities.sumNumbers(sample_part.getY(),retorno);
		}
		retorno = TypeUtilities.divideNumbers(retorno,new Double(tamanho));
		return retorno;
	}
	
}
