import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import controlcharts.GenericChartLimits;
import controlcharts.GenericChartLimitsBase;
import controlcharts.RegressionChartLimits;


import statistic.GenericStatistic;
import statistic.RegressionStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetException;

public class GenerateGraphs {
//	Gráfico de Pizza com dados PieDataSet
	
	public static JFreeChart lineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites) throws DataSetException{
		//	create a chart...
		JFreeChart chart;
		if(statistic instanceof RegressionStatistic)
		{
			chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatística",LineChart.dataLineChart_regression((RegressionStatistic)statistic, arquivo,(RegressionChartLimits)limites),PlotOrientation.VERTICAL,true, true, false);
		}
		else
		{
			chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatística",LineChart.dataLineChart(statistic, arquivo,limites),PlotOrientation.VERTICAL,true, true, false);
		}
		
		ConfigureChart.alterarConfiguracaoGrafico(chart);					
		return chart;	
	}
	
}
