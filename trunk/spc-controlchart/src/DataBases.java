import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.regression.*;
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
import data.DataSetIterator;
import data.types.DataConverter;
import data.types.DoubleDataConverter;

public class DataBases {
	private static double[] reg;

	public static DefaultPieDataset dataSetPieChart(){ 
		// create a dataset...
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("Livro", new Double(43.2));
		data.setValue("Música", new Double(27.9));
		data.setValue("televisão", new Double(79.5));
		return data;
	}
	
	public static XYSeriesCollection dataXYLineChart(){ 
		// create a dataset...
		XYSeries series1 = new XYSeries("LSC");		
		series1.add(181.8, 1);		
		series1.add(181.8, 2);
		series1.add(181.8, 3);
		series1.add(181.8, 4);
		series1.add(181.8, 5);
		
		XYSeries series2 = new XYSeries("LC");
		series2.add(150.0, 1);
		series2.add(150.0, 2);
		series2.add(150.0, 3);
		series2.add(150.0, 4);
		series2.add(150.0, 5);
		
		XYSeries series3 = new XYSeries("LIC");
		series3.add(129.6, 1);
		series3.add(129.6, 2);
		series3.add(129.6, 3);
		series3.add(129.6, 4);
		series3.add(129.6, 5);				
		
		XYSeries series4 = new XYSeries("Amostras");
		series4.add(179.8, 1);
		series4.add(131.8, 2);
		series4.add(167.3, 3);
		series4.add(153.8, 4);
		series4.add(158.8, 5);
		
		XYSeriesCollection data = new XYSeriesCollection();	
		data.addSeries(series1);
		data.addSeries(series2);
		data.addSeries(series3);
		data.addSeries(series4);
		//data.setAutoWidth(true);
		return data;
	}
	
	
	public static DefaultCategoryDataset dataLineChart_by_tacio() throws DataSetException
	{
//		 define os nomes das linhas
		String series1 = "LSC";
		String series2 = "LC";
		String series3 = "LIC";
		String series4 = "Amostras";
		
		// define o eixo x
		/*String type1 = "1";
		String type2 = "2";
		String type3 = "3";
		String type4 = "4";
		String type5 = "5";*/
		
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File arquivo = chooser.getSelectedFile();
        
        if(arquivo==null)
        {
        	return null;
        }
        
        DataConverter conversor_long = new DoubleDataConverter();
        DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        int cont = 0;
        GenericChartLimits limites_controle = null;
        while(!data_set.isEmpty())
        {
        	cont++;
        	ArrayList<DataSetItem> item = data_set.next();
        	GenericStatistic statistica_teste = new MedianStatistic();
        	System.out.println("mediana = "+statistica_teste);        	
        	Number estatistica = statistica_teste.generateStatistic(item);
        	if(limites_controle==null)
        	{
        		limites_controle = new StandardDeviationChartLimits(item.size());
        	}
        	limites_controle.addData(item);
        	dataset.addValue(estatistica, series4,String.valueOf(cont));
        }
        
        
		for(int cont2=1;cont2<=cont;cont2++)
		{
			Double lc = limites_controle.calculateCentralLine(new Double(cont2));
	        Double lsc = limites_controle.calculateUpperControlLimit(new Double(cont2));
	        Double lic = limites_controle.calculateLowerControlLimit(new Double(cont2));
			dataset.addValue(lsc, series1, String.valueOf(cont2));
        	dataset.addValue(lc, series2, String.valueOf(cont2));
        	dataset.addValue(lic, series3, String.valueOf(cont2));
		}
		return dataset;	
	}
	
	public static DefaultCategoryDataset dataLineChart_by_paula(GenericStatistic statistic, File arquivo, GenericChartLimits limites_controle) throws DataSetException
	{
//		 define os nomes das linhas
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
        DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
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
        DataSetIterator data_set1 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        statistic = new RegressionStatistic(data_set1);
        limites_controle = new RegressionChartLimits(statistic.getRegressao(),new DataSetCsvIterator(arquivo,conversor_long,false,null));
        DataSetIterator data_set2 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        
        
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
        	
        	Double lc = limites_controle.calculateCentralLine(x);
	        Double lsc = limites_controle.calculateUpperControlLimit(x);
	        Double lic = limites_controle.calculateLowerControlLimit(x);
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
	
	public static void dataLineChart_by_teste() throws DataSetException, MathException
	{	
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();						
		System.out.println("Valor regressao:"+reg);
		
		/*org.apache.commons.math.stat.regression.SimpleRegression reg = new SimpleRegression();
		double c = reg.getRegressionSumSquares();
		double b = reg.getIntercept();
		double a = reg.getSignificance();
		System.out.println("Valor b:"+b);
		System.out.println("Valor a:"+a);
		System.out.println("Valor c:"+c);*/		
                   				
	}
	
	
	public static DefaultCategoryDataset dataLineChart(){ 
		
		// define os nomes das linhas
		String series1 = "LSC";
		String series2 = "LC";
		String series3 = "LIC";
		String series4 = "Amostras";
		
		// define o eixo x
		String type1 = "1";
		String type2 = "2";
		String type3 = "3";
		String type4 = "4";
		String type5 = "5";
		
		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		
		dataset.addValue(181.8, series1, type1);
		dataset.addValue(181.8, series1, type2);
		dataset.addValue(181.8, series1, type3);
		dataset.addValue(181.8, series1, type4);
		dataset.addValue(181.8, series1, type5);
		
		dataset.addValue(150.0, series2, type1);
		dataset.addValue(150.0, series2, type2);
		dataset.addValue(150.0, series2, type3);
		dataset.addValue(150.0, series2, type4);
		dataset.addValue(150.0, series2, type5);
		
		
		dataset.addValue(129.6, series3, type1);
		dataset.addValue(129.6, series3, type2);
		dataset.addValue(129.6, series3, type3);
		dataset.addValue(129.6, series3, type4);
		dataset.addValue(129.6, series3, type5);
		
		dataset.addValue(179.8, series4, type1);
		dataset.addValue(131.8, series4, type2);
		dataset.addValue(167.3, series4, type3);
		dataset.addValue(153.8, series4, type4);
		dataset.addValue(167.6, series4, type5);		
		
		return dataset;	
	}
	
	public static XYDataset createDataset() {
		
		TimeSeries s1 = new TimeSeries("LSC", Month.class);		
		s1.add(new Month(1, 2005), 181.8);
		s1.add(new Month(2, 2005), 181.8);
		s1.add(new Month(3, 2005), 181.8);
		s1.add(new Month(4, 2005), 181.8);
		s1.add(new Month(5, 2005), 181.8);
		s1.add(new Month(6, 2005), 181.8);				
		
		TimeSeries s2 = new TimeSeries("LC", Month.class);
		s2.add(new Month(1, 2005), 150.0);
		s2.add(new Month(2, 2005), 150.0);
		s2.add(new Month(3, 2005), 150.0);
		s2.add(new Month(4, 2005), 150.0);
		s2.add(new Month(5, 2005), 150.0);
		s2.add(new Month(6, 2005), 150.0);
		
		TimeSeries s3 = new TimeSeries("LIC", Month.class);
		s3.add(new Month(1, 2005), 129.6);
		s3.add(new Month(2, 2005), 129.6);
		s3.add(new Month(3, 2005), 129.6);
		s3.add(new Month(4, 2005), 129.6);
		s3.add(new Month(5, 2005), 129.6);
		s3.add(new Month(6, 2005), 129.6);
		
		TimeSeries s4 = new TimeSeries("DP", Month.class);		
		s4.add(new Month(1, 2005), 179.8);
		s4.add(new Month(2, 2005), 131.8);
		s4.add(new Month(3, 2005), 167.3);
		s4.add(new Month(4, 2005), 153.8);
		s4.add(new Month(5, 2005), 167.6);
		s4.add(new Month(6, 2005), 158.8);	
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.addSeries(s2);
		dataset.addSeries(s3);
		dataset.addSeries(s4);
		dataset.setDomainIsPointsInTime(true);
		return dataset;
	}
	
	public static void main (String[] args) throws DataSetException, MathException
	{
		dataLineChart_by_teste();		
	}
}
