package ui;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

import math.CalculateStatisticBasic;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

import types.DataConverter;
import types.DoubleDataConverter;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetIterate;

/*
 * BasicStatistic.java
 *
 * Created on 5 de Abril de 2008, 02:21
 */


/**
 *
 * @author  Administrador
 */
public class BasicStatistic extends javax.swing.JFrame {
    
    /** Creates new form BasicStatistic 
     * @throws DataSetException */
    public BasicStatistic(DataSetIterate data_set) throws DataSetException {
        initComponents();      
        
		CalculateStatisticBasic descritive = new CalculateStatisticBasic(data_set);
		String amostras = Double.toString(descritive.getQtdAmostras());
		String minimo = Double.toString(descritive.getMin());
		String maximo = Double.toString(descritive.getMax());
		String desvio_padrao = Double.toString(descritive.getDesvio());
		String media_amostras = Double.toString(descritive.getQtdAmostras());
		String variancia_amostras = Double.toString(descritive.getVariance());
		numero_amostras.setText(amostras);
		min.setText(minimo);
		max.setText(maximo);
		desvio.setText(desvio_padrao);
		media.setText(media_amostras);
		variancia.setText(variancia_amostras);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        variancia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numero_amostras = new javax.swing.JTextField();
        min = new javax.swing.JTextField();
        max = new javax.swing.JTextField();
        desvio = new javax.swing.JTextField();
        media = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("basic_estat"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel1.setText("Basic Statistics");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 90, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("M�dia:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel3.setText("Min:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setText("Max:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel5.setText("Desvio Padr�o:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        variancia.setEnabled(false);
        jPanel1.add(variancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 110, 20));

        jLabel6.setText("N:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel7.setText("Vari�ncia:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        numero_amostras.setEnabled(false);
        jPanel1.add(numero_amostras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, 20));

        min.setEnabled(false);
        jPanel1.add(min, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 110, 20));

        max.setEnabled(false);
        jPanel1.add(max, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 20));

        desvio.setEnabled(false);
        jPanel1.add(desvio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 110, 20));

        media.setEnabled(false);
        jPanel1.add(media, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 110, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 360, 170));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-452)/2, (screenSize.height-308)/2, 452, 308);
    }// </editor-fold>//GEN-END:initComponents
    
    
    /**
     * @param args the command line arguments
     */
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField desvio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField max;
    private javax.swing.JTextField media;
    private javax.swing.JTextField min;
    private javax.swing.JTextField numero_amostras;
    private javax.swing.JTextField variancia;
    // End of variables declaration//GEN-END:variables
    
}
