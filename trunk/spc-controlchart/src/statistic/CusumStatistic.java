package statistic;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.commons.math.stat.descriptive.rank.Median;

import types.DataConverter;
import types.DoubleDataConverter;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

public class CusumStatistic implements GenericStatistic{

	private boolean comportamento;
	private Double maximo = 0.0;
	
	public CusumStatistic(boolean tipo_comportamento)
	{
		this.comportamento = tipo_comportamento;
	}
	
	private Double ci_anterior = 0.0;
	
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		Double retorno = 0.0;
		Double xi =0.0;
		if(sample.size()>0)
		{
			if(this.comportamento)
			{
				xi = sample.get(0).getY()+this.ci_anterior;
				//TODO COLOCAR O MI+k na magnitude
				Double magnitude = 0.0;
				retorno = xi-magnitude;				
			}
			else
			{
				xi = sample.get(0).getY()+this.ci_anterior;
				//TODO COLOCAR O MI-k na magnitude
				Double magnitude = 0.0;
				retorno = magnitude-xi;	
			}
			
		}
		
		if(retorno>this.maximo)
		{
			this.maximo = retorno;
		}
		
		this.ci_anterior = this.maximo;
		return this.maximo;		
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
			GenericStatistic statistica_teste = new MedianStatistic();
			Number estatistica = statistica_teste.generateStatistic(item);
			System.out.println("Mediana: "+estatistica);

		}

	}
}
