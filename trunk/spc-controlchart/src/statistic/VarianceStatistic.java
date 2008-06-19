package statistic;

import java.util.ArrayList;


import data.DataSetItem;
import data.types.TypeUtilities;

public class VarianceStatistic implements GenericStatistic 
{
	private boolean is_amostral;
	
	public VarianceStatistic(boolean is_amostral)
	{
		this.is_amostral = is_amostral;		
	}
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		if(sample==null)
		{
			return null;
		}
		int tamanho = sample.size();
		AverageStatistic gerador_media = new AverageStatistic();
		Double media = gerador_media.generateStatistic(sample);
		Double soma_quadrados = (double)0;
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			Double diferenca_media = TypeUtilities.sumNumbers(sample_part.getY(),-media);
			Double quadrado_diferenca = Math.pow(diferenca_media,(double)2);
			soma_quadrados += quadrado_diferenca;
		}
		Double denominador = null;
		if(this.is_amostral)
		{
			denominador = (double)(tamanho - 1);
		}
		else
		{
			denominador = (double)tamanho;
		}
		return soma_quadrados / denominador;
	}

}
