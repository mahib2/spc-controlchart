package statistic;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.math.stat.regression.*;

import javax.swing.JFileChooser;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterator;
import data.types.DataConverter;
import data.types.DoubleDataConverter;

public class RegressionStatistic implements GenericStatistic 
{

	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{	

		if(sample==null)
		{
			return null;
		}
		int tamanho = sample.size();
		Double retorno = null;
		SimpleRegression regressao = new SimpleRegression();
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);

			double x  = sample_part.getX();
			double y  = sample_part.getY();
			regressao.addData(x,y);															

		}
		double intercept = regressao.getIntercept();
		System.out.println("intercept: "+intercept);
		double slope = regressao.getSlope();
		System.out.println("intercept: "+slope);
		//retorno = TypeUtilities.divideNumbers(retorno,new Double(tamanho));
		return null;
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
		DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;

		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = new RegressionStatistic();
			Number estatistica = statistica_teste.generateStatistic(item);
			System.out.println("Regressão: "+estatistica);

		}
	}
}	

