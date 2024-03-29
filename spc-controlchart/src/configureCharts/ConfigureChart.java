package configureCharts;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class ConfigureChart 
{
	public static void alterarConfiguracaoGraficoLinha(JFreeChart chart)
	{
		// adicionando t�tulo
		/*TextTitle title = new TextTitle("T�tulo do Gr�fico");
		chart.addSubtitle(title);*/		
		//remover a legenda
		//chart.removeLegend();
		// mudar cor do fundo
		chart.setBackgroundPaint(Color.white);

		// alterar o fundo com efeitos (gradiente), etc...
		//Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
		//chart.setBackgroundPaint(p);

		//instanciar gr�fico
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		//mudar a cor do fundo
		plot.setBackgroundPaint(Color.white);
		//mudar a cor da linha horizontal
		plot.setRangeGridlinePaint(Color.white);
		//tirar as linhas horizontais
		plot.setRangeGridlinesVisible(false);		


                /* customiza��o original
//		customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//fazer o eixo ficar pr�ximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(false);
               */
                // modificado
                NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		//rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//fazer o eixo ficar pr?ximos do menor valor
                //rangeAxis.setRange(0, 0.4);  
		rangeAxis.setAutoRangeIncludesZero(false);//modificado antes era false

//		customise the s�ries - cor, Shapes

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		//renderer.setSeriesLinesVisible(0, false);

		//Amostras
		renderer.setSeriesPaint(0,Color.BLUE);
		//LSC
		renderer.setSeriesPaint(1,Color.BLUE);
		//M�DIA
		renderer.setSeriesPaint(2,Color.BLACK);
		//LIC
		renderer.setSeriesPaint(3,Color.BLUE);
		//LIA
		renderer.setSeriesPaint(4,Color.RED);
		//LSA
		renderer.setSeriesPaint(5,Color.RED);

		//mudar o shape (desenho que aparece no gr�fico)

		renderer.setSeriesShape(0,renderer.DEFAULT_SHAPE);


		//Amostras
		renderer.setSeriesShapesVisible(0, true);        
		//pintar o shape de outra cor
		renderer.setSeriesFillPaint(0, Color.BLUE, false);
		renderer.setSeriesShapesFilled(0,true);                                     


		//mudar s�ries - pontilhada
		/*renderer.setSeriesStroke(
            0, new BasicStroke(
                2.0f, BasicStroke.JOIN_ROUND, BasicStroke.JOIN_ROUND,
                2.0f, new float[] {10.0f, 6.0f}, 1.0f
            )
        );

        renderer.setSeriesStroke(
            1, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {6.0f, 6.0f}, 0.0f
            )
        );
        renderer.setSeriesStroke(
            2, new BasicStroke(
                2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                1.0f, new float[] {2.0f, 6.0f}, 0.0f
            )
        );*/                
	}
	
	public static void alterarConfiguracaoGraficoLinhaDuplo(JFreeChart chart)
	{
		// adicionando t�tulo
		/*TextTitle title = new TextTitle("T�tulo do Gr�fico");
		chart.addSubtitle(title);*/		
		//remover a legenda
		//chart.removeLegend();
		// mudar cor do fundo
		chart.setBackgroundPaint(Color.white);

		// alterar o fundo com efeitos (gradiente), etc...
		//Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
		//chart.setBackgroundPaint(p);

		//instanciar gr�fico
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		//mudar a cor do fundo
		plot.setBackgroundPaint(Color.white);
		//mudar a cor da linha horizontal
		plot.setRangeGridlinePaint(Color.white);
		//tirar as linhas horizontais
		plot.setRangeGridlinesVisible(false);		

//		customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		rangeAxis.setNegativeArrowVisible(true);
		
		
		//fazer o eixo ficar pr�ximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(true);


//		customise the s�ries - cor, Shapes

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		//renderer.setSeriesLinesVisible(0, false);

		//Amostras
		renderer.setSeriesPaint(0,Color.BLUE);
		//Ci+
		renderer.setSeriesPaint(1,Color.RED);
		//Ci-
		renderer.setSeriesPaint(2,Color.BLACK);
		
		//mudar o shape (desenho que aparece no gr�fico)

		renderer.setSeriesShape(1,AbstractRenderer.DEFAULT_SHAPE);
		renderer.setSeriesShape(2,AbstractRenderer.DEFAULT_SHAPE);


		//Amostras
		renderer.setSeriesShapesVisible(1, true);
		renderer.setSeriesShapesVisible(2, true);
		//pintar o shape de outra cor
		renderer.setSeriesFillPaint(0, Color.BLUE, false);
		renderer.setSeriesShapesFilled(0,true);                                     
              
	}
	
	public static void alterarConfiguracaoGraficoRegressao(JFreeChart chart)
	{
		chart.setBackgroundPaint(Color.white);
		/*
		//instanciar gr�fico
		XYPlot plot = (XYPlot) chart.getPlot();
		//mudar a cor do fundo
		plot.setBackgroundPaint(Color.white);
		//mudar a cor da linha horizontal
		plot.setRangeGridlinePaint(Color.white);
		//tirar as linhas horizontais
		plot.setRangeGridlinesVisible(false);		

//		customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//fazer o eixo ficar pr�ximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(false);
		//instanciar gr�fico
		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
		//Amostras
		renderer.setSeriesShapesVisible(0, true);        
		renderer.setSeriesShapesVisible(1, true);
		//pintar o shape de outra cor
		//LIA
		renderer.setSeriesPaint(0,Color.BLACK);
		//Ci+
		renderer.setSeriesPaint(1,Color.BLACK);
		//Ci-
		renderer.setSeriesPaint(2,Color.BLACK);*/
		
		
		
		
		/*renderer.setSeriesFillPaint(0, Color.YELLOW, false);
		renderer.setSeriesShapesFilled(0,true);     
		renderer.setSeriesShape(0,renderer.DEFAULT_SHAPE);*/
		
	}

}
