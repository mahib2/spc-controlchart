package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

import types.DataConverter;


/**
 * O formato de leitura dessa classe e:
 * 1 - Um objeto por linha
 * 2 - O cabeçalho deve estar na primeira linha 
 * 3 - campos separados por delimitador, sendo que ';' e o default
 */
public class DataSetCsvIterator implements DataSetIterate
{
	private BufferedReader arquivo;
	private static String delimitador = ";";
    private ArrayList<String> nomes_atributos;
    private String linha_atual;
    private DataConverter conversor;
    private boolean permitir_erros;
    private Double valor_default;
	
	/**
	 * @param arquivo
	 * @throws FileNotFoundException 
	 * @roseuid 44F6E32A037A
	 */
	public DataSetCsvIterator(java.io.File arquivo, DataConverter conversor, boolean permitir_erros, Double valor_default) 
	{
		this(arquivo,DataSetCsvIterator.delimitador,conversor,permitir_erros,valor_default);
	}
	
	/**
	 * @param arquivo
	 * @param delimitador
	 * @throws FileNotFoundException 
	 * @roseuid 44EC50CC0242
	 */
	public DataSetCsvIterator(java.io.File arquivo, String delimitador, DataConverter conversor, boolean permitir_erros, Double valor_default)
	{
		this.conversor = conversor;
		this.permitir_erros = permitir_erros;
		this.valor_default = valor_default;
		/*Guarda o valor do demilitador de instancia o leitor de arquivo*/
		DataSetCsvIterator.delimitador = delimitador;
		FileReader fileReader;
        try 
        {
            fileReader = new FileReader(arquivo);
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
            return;
        }
		this.arquivo = new BufferedReader(fileReader);
	}//fim-CsvIO
	
    private ArrayList<String> lerCabecalho(String linha_texto)
    {
        if(linha_texto==null)
        {
            return null;
        }
        StringTokenizer tokenizer = new StringTokenizer(linha_texto,DataSetCsvIterator.delimitador);
        /*Ocorre quando a linha nao posssui delimitadores*/
        if (tokenizer.countTokens()==0)
        {
            return null;
        }
        ArrayList<String> retorno = new ArrayList<String>(tokenizer.countTokens()); 
        while(tokenizer.hasMoreTokens())
        {
            String nome_atributo = tokenizer.nextToken();
            if (nome_atributo==null)
            {
                continue;
            }
            /*Caixa alta*/
            retorno.add(nome_atributo);
        }
        retorno.trimToSize();
        return retorno;     
    }//fim-lerCabecalho
    
	/**
	 * @return formato.Ocorrencia
	 * @throws IOException 
	 * @roseuid 44EC50CC0243
	 */
	public ArrayList<DataSetItem> next() throws DataSetException 
	{
		try
		{
			String linha;
			if (this.linha_atual!=null)
			{
				linha = this.linha_atual;
			}
			else
			{
				/*Ocorre quando o arquivo nao foi aberto*/
				if (this.arquivo==null)
				{
					return null;
				}/*Le uma linha do arquivo*/
				linha = this.corrigirAtributosBrancos(this.arquivo.readLine());            
			}//fim-else
			
			/*Ler o cabeçalho, caso ainda não tenha sido lido*/
			while((this.nomes_atributos==null)&&(linha!=null))
			{
				this.nomes_atributos = this.lerCabecalho(linha);
				linha = this.corrigirAtributosBrancos(this.arquivo.readLine());
			}
			
			/*Ocorre quando o arquivo chegou no seu fim*/
			if(linha==null)
			{
				this.arquivo.close();
				this.arquivo = null;
				return null;
			}
			//System.out.print(linha);
			/*Instancia o StringTokenizer, para separar os atributos pelos delimitadores*/
			StringTokenizer tokenizer = new StringTokenizer(linha,DataSetCsvIterator.delimitador);
			/*Ocorre quando a linha nao posssui delimitadores*/
			if (tokenizer.countTokens()==0)
			{
				return null;
			}
			/*Instancia o objeto de retorno*/
			ArrayList<DataSetItem> retorno = new ArrayList<DataSetItem>(50); 
			int index_atributos = 0;
			while((tokenizer.hasMoreTokens())&& (index_atributos<this.nomes_atributos.size()))
			{
				DataSetItem retorno_item = new DataSetItem();
				String atributo = tokenizer.nextToken(); 
				if (atributo==null)
				{
					continue;
				}
				else
				{
					//retorno.addAttribute(this.nomes_atributos.get(index_atributos),atributo);
					index_atributos++;
					retorno_item.setX((double)index_atributos);
					if(this.permitir_erros)
					{
						retorno_item.setY(this.conversor.convertString(atributo,this.valor_default));
					}
					else
					{
						retorno_item.setY(this.conversor.convertString(atributo));
					}
				}
				retorno.add(retorno_item);
			}//fim-while
			
			/*ler logo a próxima linha do arquivo para ficar em buffer*/
			if (this.arquivo!=null)
			{
				this.linha_atual = this.corrigirAtributosBrancos(this.arquivo.readLine());
				if(this.linha_atual==null)
				{
					this.arquivo.close();
					this.arquivo = null;
				}
			}
			//System.out.println("--------------------------------------");
			retorno.trimToSize();
			return retorno;
		}
		catch (IOException e) 
		{
			throw new DataSetException(e,"Erro ao ler o arquivo de dados");
		}
		
	}//fim-lerOcorrencia()
	
	/**
     * Corrige a String para evitar problemas com campos em branco
     */
    private String corrigirAtributosBrancos(String linha)
    {
        if (linha==null)
        {
            return null;
        }
        String retorno = linha.toString();
        while(retorno.indexOf(DataSetCsvIterator.delimitador+DataSetCsvIterator.delimitador)!=-1)
        {
            retorno = retorno.replaceAll(DataSetCsvIterator.delimitador+DataSetCsvIterator.delimitador,DataSetCsvIterator.delimitador+" "+DataSetCsvIterator.delimitador);
            //System.out.println(retorno);
        }
        return retorno.toUpperCase();
    }//fim-corrigirAtributosBrancos

    public boolean isEmpty() 
    {
        if ((this.arquivo!=null)||(this.linha_atual!=null))
        {
            return false;
        }
        else
        {
            return true;            
        }
    }//fim-isEmpty
    
    public void close() throws IOException 
    {
        if (this.arquivo!=null)
        {
            this.arquivo.close();
        }
    }



    
    
}//fim-classe
