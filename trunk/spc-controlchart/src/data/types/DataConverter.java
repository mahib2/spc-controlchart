package data.types;

public interface DataConverter 
{
	public Double convertString(String string) throws NumberFormatException;
	public Double convertString(String string, Double valor_default);
}
