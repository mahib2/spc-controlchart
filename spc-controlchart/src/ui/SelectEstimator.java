package ui;



import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.jfree.chart.ChartFrame;
import statistic.GenericStatistic;
import statistic.MedianStatistic;
import statistic.StandardDeviationStatistic;
import configureCharts.GenerateGraphs;
import controlcharts.GenericChartLimits;
import controlcharts.MedianChartLimits;
import controlcharts.StandardDeviationChartLimits;
import data.DataSetException;

/*
 * ControlChart.java
 *
 * Created on 7 de Abril de 2008, 22:26
 */

/**
 *
 * @author  Administrador
 */
public class SelectEstimator extends javax.swing.JFrame {

	private Main parent;

	/** Creates new form ControlChart */
	public SelectEstimator(Main parent) 
	{
		this.parent = parent;        
		initComponents();    	
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        estimate = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecionar Estimador");
        setName("control_chart"); // NOI18N

        estimate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "selecionar", "Sbar/dn", "Rbar/d2" }));

        jLabel2.setText("Estimator");

        jButton1.setText("Generate Median Control Chart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("Select Estimator");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(estimate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(71, 71, 71)
                        .add(jButton1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel3)
                .add(25, 25, 25)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(estimate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 45, Short.MAX_VALUE)
                .add(jButton1)
                .add(29, 29, 29))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(30, 30, 30)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-235)/2, 400, 235);
    }// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		this.setVisible(false);
		GenericStatistic statistic = new MedianStatistic();
		GenericChartLimits limites = new MedianChartLimits(); 
		File arquivo = this.parent.abrirArquivo();
		ChartFrame frame;		
		try 
		{
			frame = new ChartFrame("Gerando Gr�fico", GenerateGraphs.lineChart(statistic, arquivo,limites));
			frame.pack();
			frame.setVisible(true);                        
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();                          
			frame.setLocation(200,200);                        
		} catch (DataSetException e) {
			// TODO tratar essa excess�o
			e.printStackTrace();
		}


	}//GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param agrs the command line arguments
	 */
	


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox estimate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
