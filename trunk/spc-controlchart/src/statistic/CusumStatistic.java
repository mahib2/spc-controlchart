package statistic;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;

import org.apache.commons.math.stat.descriptive.rank.Median;

import types.DataConverter;
import types.DoubleDataConverter;
import types.TypeUtilities;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

public class CusumStatistic implements GenericStatistic{

	private boolean comportamento;
	private Double k;
	private static ArrayList<ArrayList<DataSetItem>> data;
	private static Integer sample_size;
	private Double ci_anterior = 0.0;
	private double standard_deviation;
	private double average;
	
	
	public CusumStatistic(boolean tipo_comportamento, double k, double standard_deviation, double average)
	{
		this.comportamento = tipo_comportamento;
		this.standard_deviation = standard_deviation;
		this.average = average;
		//TODO ver se nao tem que char o init em algum momento
	}
	
	private double calculate_k(double k, double standard_deviation, double numero_amostras)
	{
		return (k*standard_deviation)/Math.sqrt(numero_amostras);						
	}
	
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		//TODO acho que falta so o N+/- aqui.
		if(this.k==null)
		{
			this.k = this.calculate_k(this.k, this.standard_deviation, CusumStatistic.sample_size);
		}
		
		if(sample==null)
		{
			return null;
		}
		
		int tamanho = sample.size();
		Double media_amostra = null;
		
		/*Acha a media 1º*/
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			media_amostra = TypeUtilities.sumNumbers(sample_part.getY(),media_amostra);
		}
		media_amostra = TypeUtilities.divideNumbers(media_amostra,new Double(tamanho));
		
		/*Agora sim o Ci*/
		Double retorno = 0.0;
		if(sample.size()>0)
		{
			double ci;
			if(this.comportamento)
			{
				ci = media_amostra - (this.average+this.k);
			}
			else
			{
				ci = (this.average-this.k) - media_amostra;
			}
			retorno = ci+ this.ci_anterior;
		}
		
		if(retorno<0.0)
		{
			retorno = 0.0;
		}
		
		this.ci_anterior = retorno.doubleValue();
		
		return retorno;		
	}
	
	/*
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
	*/

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
