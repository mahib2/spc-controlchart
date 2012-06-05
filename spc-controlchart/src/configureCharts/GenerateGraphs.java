package configureCharts;


import java.awt.Color;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import statistic.GenericStatistic;
import statistic.RegressionStatistic;
import controlcharts.GenericChartLimits;
import controlcharts.RegressionChartLimits;
import data.DataSetException;
import statistic.ZiPStatistic;

public class GenerateGraphs 
{
//	Gráficos de linha
	
	public static JFreeChart lineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites) throws DataSetException
	{
		//	create a chart...
		JFreeChart chart;
		if(statistic instanceof RegressionStatistic)
		{
			
			XYDataset[] data_sets = RegressionChart.chart_regression((RegressionStatistic)statistic, arquivo, (RegressionChartLimits)limites);
			
			NumberAxis numberaxis = new NumberAxis("X");
	        numberaxis.setAutoRangeIncludesZero(false);
	        NumberAxis numberaxis1 = new NumberAxis("Y");
	        numberaxis1.setAutoRangeIncludesZero(false);
	        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
	        xylineandshaperenderer.setSeriesPaint(0, Color.YELLOW);
	        XYPlot xyplot = new XYPlot(data_sets[0], numberaxis, numberaxis1, xylineandshaperenderer);
	       // double ad[] = Regression.getOLSRegression(data1, 0);
			
			
			
			//LineFunction2D linefunction2d = new LineFunction2D(ad[0], ad[1]);
	        //XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, 2D, 11D, 100, "Estatística");
	        //data set Linha
	        xyplot.setDataset(1, data_sets[1]);
	        XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer1.setSeriesPaint(0, Color.BLUE);
	        xyplot.setRenderer(1, xylineandshaperenderer1);
	        
	        xyplot.setDataset(2, data_sets[2]);
	        XYLineAndShapeRenderer xylineandshaperenderer2 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer2.setSeriesPaint(0, Color.RED);
	        xyplot.setRenderer(2, xylineandshaperenderer2);
	        
	        xyplot.setDataset(3, data_sets[3]);
	        XYLineAndShapeRenderer xylineandshaperenderer3 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer3.setSeriesPaint(0, Color.BLACK);
	        xyplot.setRenderer(3, xylineandshaperenderer3);
			
	        xyplot.setDataset(4, data_sets[4]);
	        XYLineAndShapeRenderer xylineandshaperenderer4 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer4.setSeriesPaint(0, Color.RED);
	        xyplot.setRenderer(4, xylineandshaperenderer4);
	        
	        xyplot.setDataset(5, data_sets[5]);
	        XYLineAndShapeRenderer xylineandshaperenderer5 = new XYLineAndShapeRenderer(true, false);
	        xylineandshaperenderer5.setSeriesPaint(0, Color.BLUE);
	        
	        xyplot.setRenderer(5, xylineandshaperenderer5);
	        
			chart = new JFreeChart("Linear Regression", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
			ConfigureChart.alterarConfiguracaoGraficoRegressao(chart);
		}
                 else if (statistic instanceof ZiPStatistic)
		{
			chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatística",LineChart.dataLineChartZi(statistic, arquivo,limites),PlotOrientation.VERTICAL,true, true, false);
			ConfigureChart.alterarConfiguracaoGraficoLinha(chart);				
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
