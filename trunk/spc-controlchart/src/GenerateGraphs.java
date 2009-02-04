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

public class GenerateGraphs 
{
//	Gráficos de linha
	
	public static JFreeChart lineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites) throws DataSetException
	{
		//	create a chart...
		JFreeChart chart;
		if(statistic instanceof RegressionStatistic)
		{
			chart = ChartFactory.createLineChart("Gráfico de Controle","Amostras","Estatística",LineChart.dataLineChart_regression((RegressionStatistic)statistic, arquivo,(RegressionChartLimits)limites),PlotOrientation.VERTICAL,true, true, false);
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
