import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendGraphic;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.StrokeSample;
import org.jfree.util.ShapeList;

public class ConfigureChart 
{
	public static void alterarConfiguracaoGraficoLinha(JFreeChart chart)
	{
		// adicionando título
		/*TextTitle title = new TextTitle("Título do Gráfico");
		chart.addSubtitle(title);*/		
		//remover a legenda
		//chart.removeLegend();
		// mudar cor do fundo
		chart.setBackgroundPaint(Color.white);

		// alterar o fundo com efeitos (gradiente), etc...
		//Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
		//chart.setBackgroundPaint(p);

		//instanciar gráfico
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
		//fazer o eixo ficar próximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(false);


//		customise the séries - cor, Shapes

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		//renderer.setSeriesLinesVisible(0, false);

		//Amostras
		renderer.setSeriesPaint(0,Color.BLUE);
		//LSC
		renderer.setSeriesPaint(1,Color.BLUE);
		//MÉDIA
		renderer.setSeriesPaint(2,Color.BLACK);
		//LIC
		renderer.setSeriesPaint(3,Color.BLUE);
		//LIA
		renderer.setSeriesPaint(4,Color.RED);
		//LSA
		renderer.setSeriesPaint(5,Color.RED);

		//mudar o shape (desenho que aparece no gráfico)

		renderer.setSeriesShape(0,renderer.DEFAULT_SHAPE);


		//Amostras
		renderer.setSeriesShapesVisible(0, true);        
		//pintar o shape de outra cor
		renderer.setSeriesFillPaint(0, Color.BLUE, false);
		renderer.setSeriesShapesFilled(0,true);                                     


		//mudar séries - pontilhada
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
		// adicionando título
		/*TextTitle title = new TextTitle("Título do Gráfico");
		chart.addSubtitle(title);*/		
		//remover a legenda
		//chart.removeLegend();
		// mudar cor do fundo
		chart.setBackgroundPaint(Color.white);

		// alterar o fundo com efeitos (gradiente), etc...
		//Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
		//chart.setBackgroundPaint(p);

		//instanciar gráfico
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
		//fazer o eixo ficar próximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(false);


//		customise the séries - cor, Shapes

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		//renderer.setSeriesLinesVisible(0, false);

		//Amostras
		renderer.setSeriesPaint(0,Color.BLUE);
		//LSC
		renderer.setSeriesPaint(1,Color.BLUE);
		//MÉDIA
		renderer.setSeriesPaint(2,Color.BLACK);
		//LIC
		/*renderer.setSeriesPaint(3,Color.BLUE);
		//LIA
		renderer.setSeriesPaint(4,Color.RED);
		//LSA
		renderer.setSeriesPaint(5,Color.RED);*/

		//mudar o shape (desenho que aparece no gráfico)

		renderer.setSeriesShape(0,AbstractRenderer.DEFAULT_SHAPE);


		//Amostras
		renderer.setSeriesShapesVisible(0, true);        
		//pintar o shape de outra cor
		renderer.setSeriesFillPaint(0, Color.BLUE, false);
		renderer.setSeriesShapesFilled(0,true);                                     


		//mudar séries - pontilhada
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
	
	public static void alterarConfiguracaoGraficoRegressao(JFreeChart chart)
	{
		// adicionando título
		/*TextTitle title = new TextTitle("Título do Gráfico");
		chart.addSubtitle(title);*/		
		//remover a legenda
		//chart.removeLegend();
		// mudar cor do fundo
		chart.setBackgroundPaint(Color.white);

		// alterar o fundo com efeitos (gradiente), etc...
		//Paint p = new GradientPaint(0, 0, Color.white, 1000, 0, Color.green);
		//chart.setBackgroundPaint(p);

		//instanciar gráfico
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
		//fazer o eixo ficar próximos do menor valor
		rangeAxis.setAutoRangeIncludesZero(false);
		//instanciar gráfico
		CategoryPlot plotregressao = (CategoryPlot) chart.getPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plotregressao.getRenderer();
		//Amostras
		renderer.setSeriesShapesVisible(0, true);        
		//pintar o shape de outra cor
		renderer.setSeriesFillPaint(0, Color.YELLOW, false);
		renderer.setSeriesShapesFilled(0,true);     
		renderer.setSeriesShape(0,renderer.DEFAULT_SHAPE);
		
	}

}
