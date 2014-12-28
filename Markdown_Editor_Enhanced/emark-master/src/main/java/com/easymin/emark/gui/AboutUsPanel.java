/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easymin.emark.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @author Shine
 */
public class AboutUsPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2329285463080692697L;

	/**
	 * Creates new form AboutUsPanel
	 */
	public AboutUsPanel() {
		initComponents();
		drawLogo();
	}

	private void drawLogo() {

		File file = new File(System.getProperty("user.dir"), "res/logo.gif");
		try {
			this.jLabel5.setIcon(new ImageIcon(ImageIO.read(file)));
		} catch (IOException ex) {
			Logger.getLogger(AboutUsPanel.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();

		jLabel1.setFont(new java.awt.Font("宋体", 1, 24)); // NOI18N
		jLabel1.setText("EMark");

		jLabel2.setText("版本:1.0.0");

		jLabel3.setText("http://www.easymin.com");

		jLabel4.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
		jLabel4.setText("CopyRight 2013 成都易米网络科技有限公司");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		139,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jLabel3,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						139,
																						Short.MAX_VALUE)
																				.addComponent(
																						jLabel2,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addComponent(
														jLabel4,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														374, Short.MAX_VALUE)
												.addComponent(
														jLabel5,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel5,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										86,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		44,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(
																		jLabel3))
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														19,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										24,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	// End of variables declaration//GEN-END:variables
}
