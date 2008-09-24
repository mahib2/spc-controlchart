package statistic;

import java.awt.Dimension;
import java.awt.image.SampleModel;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.math.stat.descriptive.rank.Max;
import org.apache.commons.math.stat.descriptive.rank.Median;
import org.apache.commons.math.stat.descriptive.rank.Min;
import org.jfree.chart.ChartFrame;

import types.DataConverter;
import types.DoubleDataConverter;
import types.TypeUtilities;

import controlcharts.GenericChartLimits;
import controlcharts.StandardDeviationChartLimits;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

public class AmplitudeStatistic implements GenericStatistic 
{

	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		//	ordena array

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
		
		Max max = new Max();
		double maximo = max.evaluate(array);
		double teste = max.getResult();
		Min min = new Min();
		double minimo = min.evaluate(array);
		double retorno = maximo - minimo;
		
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
		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;

		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = new AmplitudeStatistic();
			Number estatistica = statistica_teste.generateStatistic(item);
			System.out.println("Amplitude: "+estatistica);

		}
	}
}