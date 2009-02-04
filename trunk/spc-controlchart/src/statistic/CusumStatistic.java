package statistic;

import java.util.ArrayList;

import types.TypeUtilities;
import controlcharts.CusumChartLimits;
import data.DataSetItem;

public class CusumStatistic implements GenericStatistic
{

	private boolean comportamento;
	private Double k_big;
	private Double k_small;
	private Integer total_samples;
	private Double ci_previous = 0.0;
	private Double standard_deviation;
	private Double average;
	private ArrayList<Integer> stored_Ns = new ArrayList<Integer>(50);
	private CusumChartLimits chartLimits;
	
	/**
	 * Construtor 1 - Caso já tenha todos os valores em mãos
	 * 
	 * @param tipo_comportamento
	 * @param k
	 * @param standard_deviation
	 * @param average
	 * @param total_samples
	 */
	
	public CusumStatistic(boolean tipo_comportamento, double k_small, double standard_deviation, double average, int total_samples)
	{
		this.comportamento = tipo_comportamento;
		this.standard_deviation = standard_deviation;
		this.average = average;
		this.total_samples = total_samples;
		this.k_small = k_small;
	}
	
	
	/**
	 * Construtor 2 - Caso tenha o objeto dos limites de controle, ele possui as informações que esta classe necessita
	 * @param tipo_comportamento
	 * @param k_small
	 * @param chartLimits
	 */
	public CusumStatistic(boolean tipo_comportamento,Double k_small, CusumChartLimits chartLimits)
	{
		this.comportamento = tipo_comportamento;
		this.chartLimits = chartLimits;
		this.k_small = k_small;
	}
	
	private double calculate_k(double k, double standard_deviation, double numero_amostras)
	{
		return (k*standard_deviation)/Math.sqrt(numero_amostras);						
	}
	
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		//TODO acho que falta so o N+/- aqui.
		/**
		 * Ve se o K já foi calculado..
		 * se não foi entra no if
		 */
		if(this.k_big==null)
		{
			/***
			 * Ainda não foi calculado..
			 * então ver se já tem o desvio padrao
			 */
			if(this.standard_deviation==null)
			{
				/**
				 * Se não tem é por foi instanciado com o construtor que fornece os limites de controle
				 * pegar as informações dele pra calcular o k
				 * 
				 */
				this.standard_deviation = this.chartLimits.getStandardDeviation();
				this.average = this.chartLimits.getTotalAverage();
				this.total_samples = this.chartLimits.getSample_size();
			}
			
			this.k_big = this.calculate_k(this.k_small, this.standard_deviation, this.total_samples);
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
				ci = media_amostra - (this.average+this.k_big);
			}
			else
			{
				ci = (this.average-this.k_big) - media_amostra;
			}
			retorno = ci+ this.ci_previous;
		}
		
		if(retorno<0.0)
		{
			retorno = 0.0;
		}
		
		this.ci_previous = retorno.doubleValue();
		
		if(retorno.equals(0.0))
		{
			this.stored_Ns.add(0);
		}
		else
		{
			if(this.stored_Ns.size()<1)
			{
				this.stored_Ns.add(1);								
			}
			else
			{
				Integer last_n = this.stored_Ns.get(this.stored_Ns.size()-1);
				this.stored_Ns.add(last_n+1);
			}
			
		}
		
		return retorno;		
	}


	public ArrayList<Integer> getStored_Ns() 
	{
		return stored_Ns;
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

	}*/
}
