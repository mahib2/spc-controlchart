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

public class GeraGraficos {
//	Gr�fico de Pizza com dados PieDataSet
	public static JFreeChart pieChart(){				
		// create a chart...
		JFreeChart chart = ChartFactory.createPieChart("Gr�fico de Pizza", DataBases.dataSetPieChart(), true,true,true);
	    ConfigureChart.alterarConfiguracaoGrafico(chart);		
		return chart;
		
	}	
	
	//	Gr�fico de Linha com dados XTDataSet
	public static JFreeChart XYLineChart(){
		//	create a chart...
		PlotOrientation orientation = PlotOrientation.HORIZONTAL;
		JFreeChart chart = ChartFactory.createXYLineChart("Gr�fico de XYLinhas Exemplo","Desvio Padr�o","Amostras",DataBases.dataXYLineChart(),orientation,true, true, true);
		//AlterarLineChart(chart);					
		return chart;	
	}
	
	public static JFreeChart lineChart(GenericStatistic statistic, File arquivo, GenericChartLimits limites) throws DataSetException{
		//	create a chart...
		JFreeChart chart;
		if(statistic instanceof RegressionStatistic)
		{
			chart = ChartFactory.createLineChart("Gr�fico de Controle","Amostras","Estat�stica",DataBases.dataLineChart_regression((RegressionStatistic)statistic, arquivo,(RegressionChartLimits)limites),PlotOrientation.VERTICAL,true, true, false);
		}
		else
		{
			chart = ChartFactory.createLineChart("Gr�fico de Controle","Amostras","Estat�stica",DataBases.dataLineChart_by_paula(statistic, arquivo,limites),PlotOrientation.VERTICAL,true, true, false);
		}
		
		ConfigureChart.alterarConfiguracaoGrafico(chart);					
		return chart;	
	}
	
	public static JFreeChart timeSeriesChart(){
		//	create a chart...
		PlotOrientation orientation = PlotOrientation.HORIZONTAL;
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Gr�fico de Linhas Exemplo","Amostras","Desvio Padr�o",DataBases.createDataset(),true, true, true);						
		return chart;	
	}	
}
