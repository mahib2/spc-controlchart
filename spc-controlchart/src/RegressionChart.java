

import java.io.File;
import java.util.ArrayList;

import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import statistic.RegressionStatistic;
import types.DataConverter;
import types.DoubleDataConverter;
import controlcharts.RegressionChartLimits;
import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;


public class RegressionChart 
{
	public static XYDataset[] chart_regression(RegressionStatistic statistic, File arquivo, RegressionChartLimits limites_controle) throws DataSetException
	{
//		 define os nomes ads linhas
		String series1 = "LSC";
		String series2 = "LC";
		String series3 = "LIC";
		String series4 = "Amostras";
		String series5 = "LIA";
		String series6 = "LSA";

		// define o eixo x
		/*String type1 = "1";
		String type2 = "2";
		String type3 = "3";
		String type4 = "4";
		String type5 = "5";*/

		

		if(arquivo==null)
		{
			return null;
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set1 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		statistic = new RegressionStatistic(data_set1);
		limites_controle = new RegressionChartLimits(statistic.getRegressao(),new DataSetCsvIterator(arquivo,conversor_long,false,null));
		DataSetIterate data_set2 = new DataSetCsvIterator(arquivo,conversor_long,false,null);

		XYSeries lsc = new XYSeries(series1);
		XYSeries lc = new XYSeries(series2);
		XYSeries pontos = new XYSeries(series4);
		XYSeries lic = new XYSeries(series3);
		XYSeries lia = new XYSeries(series5);
		XYSeries lsa = new XYSeries(series6);
		
		double min_x = Double.POSITIVE_INFINITY;
		double max_x = Double.NEGATIVE_INFINITY;
		int num_samples = 0;
		
		int cont = 0;

		while(!data_set2.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set2.next();
			//ArrayList<DataSetItem> array_parametro = new ArrayList<DataSetItem>(1);
			//DataSetItem item_parametro = new DataSetItem();
			double x = 0;
			double y = 0;
			int tamanho = item.size();
			num_samples = (int)tamanho;
			if(tamanho>0)
			{

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
					if(x<min_x)
					{
						min_x = x;
					}
					if(x>max_x)
					{
						max_x = x;
					}
				}//fim-for
			}//fim-if
			pontos.add(x,y);
//			System.out.println(MathUtils.round(limites_controle.calculateCentralLine(x),2));
//			System.out.println(x);
			Double lc_v = limites_controle.calculateCentralLine(x);
			Double lsc_v = limites_controle.calculateUpperControlLimit(x);
			Double lic_v = limites_controle.calculateLowerControlLimit(x);
			Double lia_v = limites_controle.calculateLowerAdvertenceLimit(x);
			Double lsa_v = limites_controle.calculateUpperAdvertenceLimit(x);
			
			lc.add(x, lc_v);
			lsc.add(x, lsc_v);
			lic.add(x, lic_v);
			lia.add(x, lia_v);
			lsa.add(x, lsa_v);
			
		}//fim-while
		
		/**
		 * Criação da saída
		 */
		//TODO ver esses parametros do sample function
		XYDataset[] retorno = new XYDataset[6];
		retorno[0] = new XYSeriesCollection(pontos);
		
		double pontos_temp[] = Regression.getOLSRegression(new XYSeriesCollection(lsc), 0);
		LineFunction2D linefunction2d = new LineFunction2D(pontos_temp[0], pontos_temp[1]);
		retorno[1] = DatasetUtilities.sampleFunction2D(linefunction2d, min_x, max_x, num_samples, series1);
		
		pontos_temp = Regression.getOLSRegression(new XYSeriesCollection(lsa), 0);
		linefunction2d = new LineFunction2D(pontos_temp[0], pontos_temp[1]);
		retorno[2] = DatasetUtilities.sampleFunction2D(linefunction2d, min_x, max_x, num_samples, series6);
		
		pontos_temp = Regression.getOLSRegression(new XYSeriesCollection(lc), 0);
		linefunction2d = new LineFunction2D(pontos_temp[0], pontos_temp[1]);
		retorno[3] = DatasetUtilities.sampleFunction2D(linefunction2d, min_x, max_x, num_samples, series2);
		
		pontos_temp = Regression.getOLSRegression(new XYSeriesCollection(lia), 0);
		linefunction2d = new LineFunction2D(pontos_temp[0], pontos_temp[1]);
		retorno[4] = DatasetUtilities.sampleFunction2D(linefunction2d, min_x, max_x, num_samples, series5);
		
		pontos_temp = Regression.getOLSRegression(new XYSeriesCollection(lic), 0);
		linefunction2d = new LineFunction2D(pontos_temp[0], pontos_temp[1]);
		retorno[5] = DatasetUtilities.sampleFunction2D(linefunction2d, min_x, max_x, num_samples, series3);
		
		return retorno;
		
	}
}
