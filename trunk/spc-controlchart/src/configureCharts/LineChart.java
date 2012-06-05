package configureCharts;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.regression.*;
import org.apache.commons.math.util.MathUtils;
import org.jfree.data.statistics.Regression;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import controlcharts.GenericChartLimits;
import controlcharts.RegressionChartLimits;
import controlcharts.StandardDeviationChartLimits;
import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.RegressionStatistic;
import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;
import types.DataConverter;
import types.DoubleDataConverter;

public class LineChart {


	public static DefaultCategoryDataset dataLineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites_controle) throws DataSetException
	{
//		define os nomes das linhas
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

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();


		if(arquivo==null)
		{
			return null;
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;

		Integer tamanho_amostra = null;
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = statistic;
			Number estatistica = statistica_teste.generateStatistic(item);
			if(tamanho_amostra==null)
			{
				tamanho_amostra = item.size();
				limites_controle.setSampleSize(tamanho_amostra);
			}
			limites_controle.addData(item);
			dataset.addValue(estatistica, series4,String.valueOf(cont));
		}



		for(int cont2=1;cont2<=cont;cont2++)
		{
			Double lc = limites_controle.calculateCentralLine(new Double(cont2));
			Double lsc = limites_controle.calculateUpperControlLimit(new Double(cont2));
			Double lic = limites_controle.calculateLowerControlLimit(new Double(cont2));
			Double lia = limites_controle.calculateLowerAdvertenceLimit(new Double(cont2));
			Double lsa = limites_controle.calculateUpperAdvertenceLimit(new Double(cont2));
			dataset.addValue(lsc, series1, String.valueOf(cont2));
			dataset.addValue(lc, series2, String.valueOf(cont2));
			dataset.addValue(lic, series3, String.valueOf(cont2));
			dataset.addValue(lia, series5, String.valueOf(cont2));
			dataset.addValue(lsa, series6, String.valueOf(cont2));
		}
		return dataset;	
	}
	
	
	public static DefaultCategoryDataset dataLineDoubleChart(GenericStatistic statistic1, GenericStatistic statistic2 , File arquivo, GenericChartLimits limites_controle) throws DataSetException
	{
//		define os nomes das linhas
		//TODO ver se esses nomes não poderão variar
		String series1 = "LSC";
		String series2 = "Ci+";
		String series3 = "Ci-";
		
		// define o eixo x
		/*String type1 = "1";
		String type2 = "2";
		String type3 = "3";
		String type4 = "4";
		String type5 = "5";*/

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();


		if(arquivo==null)
		{
			return null;
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set1 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		//int cont = 0;

		Integer tamanho_amostra = null;
		while(!data_set1.isEmpty())
		{
			//cont++;
			ArrayList<DataSetItem> item = data_set1.next();
			if(tamanho_amostra==null)
			{
				tamanho_amostra = item.size();
				limites_controle.setSampleSize(tamanho_amostra);
			}
			limites_controle.addData(item);
		}
		
		DataSetIterate data_set2 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont2 = 1;
		while(!data_set2.isEmpty())
		{
			ArrayList<DataSetItem> item = data_set2.next();
			
			Double lsc = limites_controle.calculateUpperControlLimit(new Double(cont2));
			dataset.addValue(lsc, series1, String.valueOf(cont2));
			
			GenericStatistic statistica_teste = statistic1;
			GenericStatistic statistica_teste2 = statistic2;
			Number estatistica1 = statistica_teste.generateStatistic(item);
			Number estatistica2 = statistica_teste2.generateStatistic(item);
			
			dataset.addValue(estatistica1, series2,String.valueOf(cont2));
			dataset.addValue(estatistica2, series3,String.valueOf(cont2));
			cont2++;
		}

		return dataset;	
	}
         public static DefaultCategoryDataset dataLineChartZi(GenericStatistic statistic, File arquivo, GenericChartLimits limites_controle) throws DataSetException
	{
//		define os nomes das linhas
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

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();


		if(arquivo==null)
		{
			return null;
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;

		Integer tamanho_amostra = null;
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
                        if(tamanho_amostra==null)
			{
				tamanho_amostra = item.size();
				limites_controle.setSampleSize(tamanho_amostra);
			}
			limites_controle.addData(item);
			GenericStatistic statistica_teste = statistic;
			Number estatistica = statistica_teste.generateStatistic(item);
		
			dataset.addValue(estatistica, series4,String.valueOf(cont));
		}



		for(int cont2=1;cont2<=cont;cont2++)
		{
			Double lc = limites_controle.calculateCentralLine(new Double(cont2));
			Double lsc = limites_controle.calculateUpperControlLimit(new Double(cont2));
			Double lic = limites_controle.calculateLowerControlLimit(new Double(cont2));
			Double lia = limites_controle.calculateLowerAdvertenceLimit(new Double(cont2));
			Double lsa = limites_controle.calculateUpperAdvertenceLimit(new Double(cont2));
			dataset.addValue(lsc, series1, String.valueOf(cont2));
			dataset.addValue(lc, series2, String.valueOf(cont2));
			dataset.addValue(lic, series3, String.valueOf(cont2));
			dataset.addValue(lia, series5, String.valueOf(cont2));
			dataset.addValue(lsa, series6, String.valueOf(cont2));
		}
		return dataset;	
	}
	
	/**
	 * Desativado
	 * @param statistic
	 * @param arquivo
	 * @param limites_controle
	 * @return
	 * @throws DataSetException
	 */
	public static DefaultCategoryDataset dataLineChart_regression(RegressionStatistic statistic, File arquivo, RegressionChartLimits limites_controle) throws DataSetException
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

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();


		if(arquivo==null)
		{
			return null;
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set1 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		statistic = new RegressionStatistic(data_set1);
		limites_controle = new RegressionChartLimits(statistic.getRegressao(),new DataSetCsvIterator(arquivo,conversor_long,false,null));
		DataSetIterate data_set2 = new DataSetCsvIterator(arquivo,conversor_long,false,null);


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
				}//fim-for
			}//fim-if
			dataset.addValue(y, series4,String.valueOf(x));
//			System.out.println(MathUtils.round(limites_controle.calculateCentralLine(x),2));
//			System.out.println(x);
			Double lc = limites_controle.calculateCentralLine(x);
			Double lsc = limites_controle.calculateUpperControlLimit(x);
			Double lic = limites_controle.calculateLowerControlLimit(x);
			System.out.println(lsc+" "+lic);
			Double lia = limites_controle.calculateLowerAdvertenceLimit(x);
			Double lsa = limites_controle.calculateUpperAdvertenceLimit(x);
			dataset.addValue(lsc, series1, String.valueOf(x));
			dataset.addValue(lc, series2, String.valueOf(x));
			dataset.addValue(lic, series3, String.valueOf(x));
			dataset.addValue(lia, series5, String.valueOf(x));
			dataset.addValue(lsa, series6, String.valueOf(x));
		}//fim-while

		return dataset;	
	}
}
