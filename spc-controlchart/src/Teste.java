
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.ShapeGraphicAttribute;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.demo.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xml.CategoryDatasetHandler;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeList;
import org.jfree.chart.ChartFrame;

import controlcharts.GenericChartLimits;
import controlcharts.MedianChartLimits;
import controlcharts.StandardDeviationChartLimits;

import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetException;


public class Teste extends org.jfree.ui.ApplicationFrame
{
	
	
	public Teste(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	/**
	 * @throws DataSetException 
	 * 
	 */	
		
	public static void main(String[] args) throws DataSetException {		
		//ler o arquivo
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File arquivo = chooser.getSelectedFile();           
		//	create and display a frame...        
		GenericStatistic statistic = new StandardDeviationStatistic(true);
		
		//GenericStatistic statistic = new MedianStatistic();
		//define limits		
		MedianChartLimits limites = new MedianChartLimits();
		
		ChartFrame frame = new ChartFrame("Gerando Gráfico", GeraGraficos.lineChart(statistic, arquivo,limites));
		frame.pack();
		frame.setVisible(true);
	}		
	
}




