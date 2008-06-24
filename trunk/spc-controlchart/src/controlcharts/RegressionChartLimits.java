package controlcharts;

import java.util.ArrayList;

import org.apache.commons.math.MathException;
import org.apache.commons.math.stat.regression.SimpleRegression;

import data.DataSetItem;

public class RegressionChartLimits implements GenericChartLimits
{
	private SimpleRegression regressao;
	
	public RegressionChartLimits(SimpleRegression regressao)
	{
		this.regressao = regressao;
		System.out.println(this.regressao.getInterceptStdErr());
		System.out.println(this.regressao.getMeanSquareError());
		System.out.println(this.regressao.getSlopeStdErr());
		System.out.println(this.regressao.getSumSquaredErrors());
		System.out.println(this.regressao.getN());
		System.out.println(this.regressao.getR());
		System.out.println(this.regressao.getRegressionSumSquares());
		System.out.println(this.regressao.getRSquare());
		try {
			System.out.println(this.regressao.getSignificance());
			System.out.println(this.regressao.getSlopeConfidenceInterval());
			System.out.println(this.regressao.getInterceptStdErr());
		} catch (MathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(this.regressao.getSumSquaredErrors());
	}

	public void addData(ArrayList<DataSetItem> new_data) 
	{
		//this.data.add(new_data);
	}

	public Double calculateCentralLine(Double x) 
	{
		return this.regressao.predict(x);
	}

	public Double calculateLowerAdvertenceLimit(Double x) {
		// TODO Auto-generated method stub
		return 1.0;
	}

	public Double calculateLowerControlLimit(Double x) {
		// TODO Auto-generated method stub
		return 1.0;
	}

	public Double calculateUpperAdvertenceLimit(Double x) {
		// TODO Auto-generated method stub
		return 1.0;
	}

	public Double calculateUpperControlLimit(Double x) {
		// TODO Auto-generated method stub
		return 1.0;
	}
	
	/**
	 * Nao tem funcionalidade para o grafico de regressao
	 */
	public void setSampleSize(Integer size) 
	{
		
	}
}
