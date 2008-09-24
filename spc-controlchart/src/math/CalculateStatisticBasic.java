/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package math;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.math.stat.descriptive.*;

import types.DataConverter;
import types.DoubleDataConverter;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;
/**
 *
 * @author cibelle
 */

public class CalculateStatisticBasic {

	private DescriptiveStatistics descritive;
	public CalculateStatisticBasic(DataSetIterate data_set) throws DataSetException{
		this.descritive = new DescriptiveStatistics();
		Integer tamanho_amostra = null;
		int cont = 0;
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			int tamanho = item.size();
			tamanho_amostra = tamanho; 
			if(tamanho_amostra!=null)
			{
				double x = 0;
				double y = 0;
				for(int cont2=0;cont2<tamanho;cont2++)
				{
					DataSetItem sample_part = item.get(cont2);
					x = sample_part.getY();
					descritive.addValue(x);
				}
			}
		}
	}

	public double getMean()
	{
		double mean = descritive.getMax();	
		return mean;	
	}
	
	public long getQtdAmostras(){
		long n = descritive.getN();
		return n;
	}
	
	public double getMin(){
		double min = descritive.getMin();
		return min;
	}
	
	public double getMax(){
		double max = descritive.getMax();
		return max;
	}
	
	public double getVariance(){
		double variance = descritive.getVariance();
		return variance;
	}
	
	public double getDesvio(){
		double desvio = descritive.getStandardDeviation();
		return desvio;
	}
	
}
