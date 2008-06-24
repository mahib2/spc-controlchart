package controlcharts;

import java.util.ArrayList;

import data.DataSetItem;

public abstract class GenericChartLimitsBase implements GenericChartLimits
{
	protected Integer sample_size;
	protected Double ucl;
	protected Double lcl;
	protected Double ual;
	protected Double lal;
	protected Double cl;
	protected ArrayList<ArrayList<DataSetItem>> data;
	
	public GenericChartLimitsBase(Integer size)
	{
		this.init(size);
	}
	
	public GenericChartLimitsBase()
	{
		
	}
	
	protected void init(int size)
	{
		this.sample_size = size;
		this.data = new ArrayList<ArrayList<DataSetItem>>(size);
	}
	
	public final Double calculateCentralLine(Double x) 
	{
		if(this.cl==null)
		{
			this.cl = this.calculateTerm1();
		}
		return this.cl;
	}

	public final Double calculateUpperControlLimit(Double x) 
	{
		if(this.ucl==null)
		{
			this.ucl = this.calculateTerm1() + (3.0 * this.calculateTerm2());
		}
		return this.ucl;
	}

	public final Double calculateLowerControlLimit(Double x) 
	{
		if(this.lcl==null)
		{
			this.lcl = this.calculateTerm1() - (3.0 * this.calculateTerm2());
		}
		return this.lcl;
	}

	public final Double calculateUpperAdvertenceLimit(Double x) 
	{
		if(this.ual==null)
		{
			this.ual = this.calculateTerm1() + (1.5 * this.calculateTerm2());
		}
		return this.ual;
	}

	public final Double calculateLowerAdvertenceLimit(Double x) 
	{
		if(this.lal==null)
		{
			this.lal = this.calculateTerm1() - (1.5 * this.calculateTerm2());
		}
		return this.lal;
	}
	
	protected abstract Double calculateTerm1();
	protected abstract Double calculateTerm2();
}
