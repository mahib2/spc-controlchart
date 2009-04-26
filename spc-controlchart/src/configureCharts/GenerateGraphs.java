package configureCharts;


import java.awt.Color;
import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controlcharts.GenericChartLimits;
import controlcharts.GenericChartLimitsBase;
import controlcharts.RegressionChartLimits;


import statistic.GenericStatistic;
import statistic.RegressionStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetException;

public class GenerateGraphs 
{
//	Gráficos de linha
	
	public static JFreeChart lineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites) throws DataSetException
	{
		//	create a chart...
		JFreeChart chart;
		if(statistic instanceof RegressionStatistic)
		{
			/*XYSeries xyseries = new XYSeries("Series 1");
	        xyseries.add(2D, 56.270000000000003D);
	        xyseries.add(3D, 41.32D);
	        xyseries.add(4D, 31.449999999999999D);
	        xyseries.add(5D, 30.050000000000001D);
	        xyseries.add(6D, 24.690000000000001D);
	        xyseries.add(7D, 19.780000000000001D);
	        xyseries.add(8D, 20.940000000000001D);
	        xyseries.add(9D, 16.73D);
	        xyseries.add(10D, 14.210000000000001D);
	        xyseries.add(11D, 12.44D);
	        
	        //data set pontos
	        XYSeriesCollection data1 = new XYSeriesCollection(xyseries);*/
	                
			/* codigo antigo
			 * chart = ChartFactory.createLineChart(
			"Gráfico de Controle",
			"Amostras",
			"Estatística",
			LineChart.dataLineChart_regression((RegressionStatistic)statistic, arquivo,(RegressionChartLimits)limites)
			,PlotOrientation.VERTICAL,
			true, 
			true, 
			false);*/
			//ConfigureChart.alterarConfiguracaoGraficoLinha(chart);
			
			XYDataset[] data_sets = RegressionChart.chart_regression((RegressionStatistic)statistic, arquivo, (RegressionChartLimits)limites);
			
			NumberAxis numberaxis = new NumberAxis("X");
	        numberaxis.setAutoRangeIncludesZero(false);
	        NumberAxis numberaxis1 = new NumberAxis("Y");
	        numberaxis1.setAutoRangeIncludesZero(false);
	        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
	        XYPlot xyplot = new XYPlot(data_sets[0], numberaxis, numberaxis1, xylineandshaperenderer);
	       // double ad[] = Regression.getOLSRegression(data1, 0);
			
			
			
			//LineFunction2D linefunction2d = new LineFunction2D(ad[0], ad[1]);
	        //XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, 2D, 11D, 100, "Estatística");
	        //data set Linha
	        xyplot.setDataset(1, data_sets[1]);
	        XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
	        xyplot.setRenderer(1, xylineandshaperenderer1);
	        
	        xyplot.setDataset(2, data_sets[2]);
	        XYLineAndShapeRenderer xylineandshaperenderer2 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.yellow);
	        xyplot.setRenderer(2, xylineandshaperenderer2);
	        
	        xyplot.setDataset(3, data_sets[3]);
	        XYLineAndShapeRenderer xylineandshaperenderer3 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.orange);
	        xyplot.setRenderer(3, xylineandshaperenderer3);
			
	        xyplot.setDataset(4, data_sets[4]);
	        XYLineAndShapeRenderer xylineandshaperenderer4 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.green);
	        xyplot.setRenderer(4, xylineandshaperenderer4);
	        
	        xyplot.setDataset(5, data_sets[5]);
	        XYLineAndShapeRenderer xylineandshaperenderer5 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.gray);
	        xyplot.setRenderer(5, xylineandshaperenderer5);
	        
			chart = new JFreeChart("Linear Regression", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
		}
		else
		{
			chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatística",LineChart.dataLineChart(statistic, arquivo,limites),PlotOrientation.VERTICAL,true, true, false);
			ConfigureChart.alterarConfiguracaoGraficoLinha(chart);	
		}
		return chart;	
	}
	
	public static JFreeChart doubleLineChart(GenericStatistic statistic1,GenericStatistic statistic2, File arquivo, GenericChartLimits limites) throws DataSetException
	{
		//	create a chart...
		JFreeChart chart;
		chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatísticas",LineChart.dataLineDoubleChart(statistic1,statistic2, arquivo,limites),PlotOrientation.VERTICAL,true, true, false);
		ConfigureChart.alterarConfiguracaoGraficoLinhaDuplo(chart);
		return chart;	
	}
	
}
