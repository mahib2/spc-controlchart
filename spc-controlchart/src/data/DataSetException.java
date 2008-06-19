package data;

public class DataSetException extends Exception 
{
	
	public DataSetException(Exception e)
	{
		super();
		e.printStackTrace();
	}
	
	public DataSetException(Exception e, String mensagem)
	{
		super(mensagem);
		e.printStackTrace();
	}
	
}
