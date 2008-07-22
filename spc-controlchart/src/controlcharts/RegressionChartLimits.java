package controlcharts;

import java.util.ArrayList;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.regression.SimpleRegression;

import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterator;

public class RegressionChartLimits implements GenericChartLimits
{
	private SimpleRegression regressao;
	private double erro_padrao;
	
	public RegressionChartLimits(SimpleRegression regressao, DataSetIterator data_set) throws DataSetException
	{
		this.regressao = regressao;
		this.erro_padrao = this.calcular_erro_padrao(data_set);
		System.out.println("Erro padrao:");
		System.out.println(this.erro_padrao);
	}

	public void addData(ArrayList<DataSetItem> new_data) 
	{
		//this.data.add(new_data);
	}
	
	private double calcular_erro_padrao(DataSetIterator data_set) throws DataSetException
	{
		//int cont = 0;

		double y1 = 0;
		double y2 = 0;
		double desvio = 0;
		double diferenca = 0;
		double n = 0;

		Integer tamanho_amostra = null;
		while(!data_set.isEmpty())
		{
			//cont++;
			ArrayList<DataSetItem> item = data_set.next();
			int tamanho = item.size();
			if(tamanho_amostra==null)
			{
				double x = 0;
				double y = 0;
				for(int cont2=0;(cont2<2 && cont2<tamanho);cont2++)
				{
					DataSetItem sample_part = item.get(cont2);
					if(cont2==0)
					{
						x = sample_part.getY();
					}
					else
					{
						y = sample_part.getY();
					}
				}//fim-for
				y2 = this.regressao.predict(x);
				y1 = y;
				//somar os desvios
				diferenca = (y1 - y2);
				double dif_qudra = Math.pow(diferenca, 2);						
				desvio = desvio + (dif_qudra);
				n++;
				//this.regressao.addData(x, y);
			}//fim-if
		}//fim-while
		double denominador = n-2;
		double erro = Math.sqrt(desvio/denominador);
		
		return erro;
	}

	public Double calculateCentralLine(Double x) 
	{
		return this.regressao.predict(x);
	}
	
	

	public Double calculateLowerAdvertenceLimit(Double x) 
	{
		return this.regressao.predict(x)-(1.5*this.erro_padrao);
	}

	public Double calculateLowerControlLimit(Double x) 
	{
		return this.regressao.predict(x)-(3.0*this.erro_padrao);
	}

	public Double calculateUpperAdvertenceLimit(Double x) 
	{
		return this.regressao.predict(x)+(1.5*this.erro_padrao);
	}

	public Double calculateUpperControlLimit(Double x) 
	{
		return this.regressao.predict(x)+(3.0*this.erro_padrao);
	}
	
	/**
	 * Nao tem funcionalidade para o grafico de regressao
	 */
	public void setSampleSize(Integer size) 
	{
		
	}
}
