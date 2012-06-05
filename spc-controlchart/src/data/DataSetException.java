package data;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        public DataSetException(Exception e, String mensagem, int lin , int col){
            JFrame frameMsg = new JFrame("JOptionPane showMessageDialog example");

    // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frameMsg,mensagem + lin + " e coluna" + col );
        System.exit(0);
        }
	
}
