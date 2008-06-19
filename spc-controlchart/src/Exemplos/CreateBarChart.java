package Exemplos;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.image.DataBufferInt;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class CreateBarChart {
	private void CreateBarChart(String[] campos, String[] valores,
			String titulo, int orientacao, String x, String y,HashMap cores) {
		
		String Cores =(String) cores.get("Cores");
		String [] coresSplited = Cores.split(";");
		Color [] ObjetosCores = new Color[coresSplited.length];
		String corHexa = "010010";
		for(int i = 0; i < coresSplited.length; i++)
		{
			if (coresSplited[i].toString().indexOf("#")>-1)
			{
				corHexa = coresSplited[i].toString();
				//ObjetosCores[i] = converteRGB(cor);
			} else {
				String corSplit1 = coresSplited[i].replace("RGB(","");
				corSplit1 = corSplit1.replace(")","");
				String[] corSplit3 = corSplit1.split(",");
				ObjetosCores[i] = new Color(Integer.parseInt(corSplit3[0]),Integer.parseInt(corSplit3[1]),Integer.parseInt(corSplit3[2]));
			}
		}
		
		/*int qntLinhas = 0;
		try {
			for (int i = 0; i < valores.length; i++) {
				createDatasetTeste.addValue(Double.parseDouble(valores[i].replace(",",".")),"(" + valores[i].toString().replace(",",".") +")" + campos[i], Integer.toString(i+1) ); //Adiciona os valores no category para gerar as barras
				qntLinhas++;
			}
			
//			Gera o gráfico
			if (orientacao == 1) //Valida a Orientação -- horizontal ou vertical
			{
				JFreeChart chart = ChartFactory.createBarChart(titulo, x, y, createDatasetTeste(),
						PlotOrientation.HORIZONTAL, true, true, true); //Gera o gráfico de barra
			} else {
				JFreeChart chart = ChartFactory.createBarChart(titulo, x, y, createDatasetTeste(),
						PlotOrientation.VERTICAL, true, true, true);
			}
			
			JFreeChart chart2;
			CategoryPlot plot = chart2.getCategoryPlot(); //Pega o plot para pintar as barras
			BarRenderer renderer = (BarRenderer) plot.getRenderer();
			renderer.setDrawBarOutline(true); //Desenha a linha externa nas barras (bordas das barras)
			for (int i = 0; i <= qntLinhas; i++)
			{
				renderer.setSeriesPaint(i,
						new GradientPaint(0.0f, 0.0f,ObjetosCores[i], 0.0f, 0.0f,
								Color.lightGray)); //Pinta no degradê q eu quero.
			}
			
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}*/
	}
	
	private static CategoryDataset createDatasetTeste() {
					
		CategoryDataset dataset = null;		
		return dataset;
		
		
	}
	
	public static void main(String[] args) {
		CreateBarChart janela = new CreateBarChart();		
	}
	}
