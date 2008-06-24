package controlcharts;

import java.util.ArrayList;

import data.DataSetItem;

/**
 * 
 * @author Tácio Vinícius
 * Agora o calculo do limites de controle e mais a linha central, recebe um
 * argumento. Na maioria dos casos esse argumento deve ser ignorado, mas essa mudança
 * e para adequar a entrada do gráfico de regressão. Onde o Y varia para cada X.
 *
 */
public interface GenericChartLimits 
{
	public void addData(ArrayList<DataSetItem> new_data);
	public void setSampleSize(Integer size);
	public Double calculateCentralLine(Double x);
	public Double calculateUpperControlLimit(Double x);
	public Double calculateLowerControlLimit(Double x);
	public Double calculateUpperAdvertenceLimit(Double x);
	public Double calculateLowerAdvertenceLimit(Double x);	
}
