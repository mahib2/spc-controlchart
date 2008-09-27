package statistic;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

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
	private static ArrayList<ArrayList<DataSetItem>> data;
	private static Integer sample_size;
	
	
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
				magnitude = this.getPositiveMagnitude(sample);
				retorno = xi-magnitude;				
			}
			else
			{
				xi = sample.get(0).getY()+this.ci_anterior;
				//TODO COLOCAR O MI-k na magnitude
				Double magnitude = 0.0;
				magnitude = this.getNegativeMagnitude(sample);
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
	
	public Double getPositiveMagnitude(ArrayList<DataSetItem> sample)
	{
		Double desvio = this.getStandardDeviation(this);
		Double magnitude = this.getK(sample);
		Double positiveMagnitude = desvio + magnitude;
		return positiveMagnitude;
	}
	
	public Double getNegativeMagnitude(ArrayList<DataSetItem> sample)
	{
		Double desvio = this.getStandardDeviation(this);
		Double magnitude = this.getK(sample);
		Double positiveMagnitude = desvio - magnitude;
		return positiveMagnitude;
	}

	public Double getK(ArrayList<DataSetItem> sample)
	{
		StandardDeviationStatistic statistic = new StandardDeviationStatistic(false);
		Double desvio = this.getStandardDeviation(statistic);
		Double K = (5 * desvio);
		System.out.println("Valor de K: "+K);
		return K;
	}

	public Double getMean(GenericStatistic statistic) 
	{	

		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			dados_para_media.add(amostra.get(0));
		}
		AverageStatistic calculador_media = new AverageStatistic();
		Double retorno = calculador_media.generateStatistic(dados_para_media);
		System.out.println(retorno);
		return retorno;
	}

	public Double getStandardDeviation(GenericStatistic statistic) 
	{			
		ArrayList<DataSetItem> dados_para_desvio = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			dados_para_desvio.add(amostra.get(0));
		}
		StandardDeviationStatistic calculador_desvio = new StandardDeviationStatistic(true);
		Double retorno = calculador_desvio.generateStatistic(dados_para_desvio);
		System.out.println("Desvio padrão: "+retorno);
		return retorno;
	}

	public static void addData(ArrayList<DataSetItem> new_data) 
	{
		data.add(new_data);
	}

	protected void init(int size)
	{
		sample_size = size;
		data = new ArrayList<ArrayList<DataSetItem>>(size);
	}


	
	public static void main(String[] args) throws DataSetException 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File arquivo = chooser.getSelectedFile();

		if(arquivo==null)
		{
			System.out.println("Arquivo null");
		}
		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;
		int tamanho = data_set.tamanho();
		
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
