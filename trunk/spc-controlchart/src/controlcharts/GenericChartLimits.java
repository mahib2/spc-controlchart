package controlcharts;

import java.util.ArrayList;

import data.DataSetItem;

/**
 * 
 * @author T�cio Vin�cius
 * Agora o calculo do limites de controle e mais a linha central, recebe um
 * argumento. Na maioria dos casos esse argumento deve ser ignorado, mas essa mudan�a
 * e para adequar a entrada do gr�fico de regress�o. Onde o Y varia para cada X.
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
