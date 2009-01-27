package statistic;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.rank.Median;

import types.DataConverter;
import types.DoubleDataConverter;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

public class MeanStatistic implements GenericStatistic {
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		int tamanho = sample.size();
		double[] array = new double[tamanho];
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			array[cont] = sample_part.getY();
		}

		if(sample==null)
		{
			return null;
		}
		Mean mean = new Mean();
		Double retorno = mean.evaluate(array);		
		return retorno;			
	}
	
	public static void main(String[] args) throws DataSetException 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File arquivo = chooser.getSelectedFile();

		if(arquivo==null)
		{
			System.out.println("Arquivo null");;
		}
		int tamanho = 0;
		double soma =0.0;
		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;

		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = new MeanStatistic();
			Number estatistica = statistica_teste.generateStatistic(item);
			System.out.println("Media: "+estatistica);
		
		}
	}
}
