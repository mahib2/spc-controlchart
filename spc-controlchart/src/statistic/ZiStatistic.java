package statistic;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.rank.Median;

import types.DataConverter;
import types.DoubleDataConverter;
import types.TypeUtilities;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

public class ZiStatistic implements GenericStatistic {
	Double constante;
	Double average;
	private Double zi_anterior = 0.0;
	int it = 0;

	public ZiStatistic (Double r, Double media){
		this.constante = r;
		this.average = media;
	}

	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		Double r = constante;
		Double media = average;
		int tamanho = sample.size();
				
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
				zi_anterior = (r*x)+(1-r)*media;
				retorno = zi_anterior;
			}			
			else 
			{
			zi = (r*x)+(1-r)*zi_anterior;	
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

	public static void main(String[] args) throws DataSetException 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File arquivo = chooser.getSelectedFile();
		GenericStatistic statistica_teste = new ZiStatistic(new Double(0.1),new Double(10));

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
			System.out.println("Zi: "+statistica_teste.generateStatistic(item));

		}

	}


}
