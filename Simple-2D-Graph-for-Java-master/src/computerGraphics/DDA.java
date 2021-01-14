package computerGraphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import graph.SimpleGraph;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DDA extends JFrame {

	private JPanel contentPane;
	private JTextField txtx1;
	private JTextField txty1;
	private JTextField txtx2;
	private JTextField txty2;
	//JPanel DDA_panel = new JPanel();
	private JButton btnDrawLine;
	SimpleGraph DDA_panel = new SimpleGraph();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DDA frame = new DDA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DDA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		DDA_panel.centralize();
		
		DDA_panel.setBounds(10, 10, 925, 645);
		contentPane.add(DDA_panel);
		
		txtx1 = new JTextField();
		txtx1.setBounds(985, 59, 96, 19);
		contentPane.add(txtx1);
		txtx1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X1");
		lblNewLabel.setBounds(985, 36, 45, 13);
		contentPane.add(lblNewLabel);
		
		txty1 = new JTextField();
		txty1.setColumns(10);
		txty1.setBounds(985, 158, 96, 19);
		contentPane.add(txty1);
		
		JLabel lblY_1 = new JLabel("Y1");
		lblY_1.setBounds(985, 135, 45, 13);
		contentPane.add(lblY_1);
		
		txtx2 = new JTextField();
		txtx2.setColumns(10);
		txtx2.setBounds(985, 252, 96, 19);
		contentPane.add(txtx2);
		
		JLabel lblX = new JLabel("X2");
		lblX.setBounds(985, 229, 45, 13);
		contentPane.add(lblX);
		
		txty2 = new JTextField();
		txty2.setColumns(10);
		txty2.setBounds(985, 350, 96, 19);
		contentPane.add(txty2);
		
		JLabel lblY = new JLabel("Y2");
		lblY.setBounds(985, 327, 45, 13);
		contentPane.add(lblY);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(1022, 523, 45, 33);
		contentPane.add(spinner);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Solid", "Dotted", "Dashed"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(985, 450, 96, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Thickness");
		lblNewLabel_1.setBounds(1022, 500, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		btnDrawLine = new JButton("DRAW LINE");
		btnDrawLine.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					double x1 = Double.parseDouble(txtx1.getText());
					double y1 = Double.parseDouble(txty1.getText());
					double x2 = Double.parseDouble(txtx2.getText());
					double y2 = Double.parseDouble(txty2.getText());
					String type = (String)comboBox.getSelectedItem();
					int value = (Integer)spinner.getValue();
					
					Graphics g = DDA_panel.getGraphics();
					
					if (type == "Solid") {
						LineDDASolid(x1 , y1 , x2 , y2 , g);
					}
					else if (type == "Dotted") {
						LineDDADotted(x1 , y1 , x2 , y2 , g);
					}
					else if (type == "Dashed"){
						LineDDADashed(x1 , y1 , x2 , y2 , g);
					}
					
			}
				
				//Solid Line
				private void LineDDASolid(double x1, double y1, double x2, double y2, Graphics g) {
					// TODO Auto-generated method stub
					double run = x2 - x1 ;
					double rise = y2 - y1 ;
					double steps ;
					
					if (Math.abs(rise) > Math.abs(run)) {
						steps = Math.abs(rise);
					}
					else
						steps = Math.abs(run);
					
					double xInc = (double)run/(double)steps ;
					double yInc = (double)rise/(double)steps ;
					
					double x = x1 ;
					double y = y1 ;
					
					x1=x;
					y1=y;
					
					for (int i = 1; i<= steps; i++) {
						x += xInc;
						y += yInc;
						
						DDA_panel.addShape(new SimpleGraph.Line(x1, y1, x, y, Color.BLUE));
						x1=x;
						y1=y;
					}
				}
				
				
				//Dotted Line
				
				private void LineDDADotted(double x1, double y1, double x2, double y2, Graphics g) {
					// TODO Auto-generated method stub
					double run = x2 - x1 ;
					double rise = y2 - y1 ;
					double steps ;
					
					if (Math.abs(rise) > Math.abs(run)) {
						steps = Math.abs(rise);
					}
					else
						steps = Math.abs(run);
					
					double xInc = (double)run/(double)steps ;
					double yInc = (double)rise/(double)steps ;
					
					double x = x1 ;
					double y = y1 ;
					
					DDA_panel.addPoint(x, y);
					
					for (int i = 1; i<= steps; i++) {
						x += xInc;
						y += yInc;
						
						DDA_panel.addPoint(x, y);
						
					}
				}
				
				
				
				//Dashed Line
				
				private void LineDDADashed(double x1, double y1, double x2, double y2, Graphics g) {
					// TODO Auto-generated method stub
					double run = x2 - x1 ;
					double rise = y2 - y1 ;
					double steps ;
					
					if (Math.abs(rise) > Math.abs(run)) {
						steps = Math.abs(rise);
					}
					else
						steps = Math.abs(run);
					
					double xInc = (double)run/(double)steps ;
					double yInc = (double)rise/(double)steps ;
					
					double x = x1 ;
					double y = y1 ;
					
					g.drawString("-", (int)x, (int)y);
					
					for (int i = 1; i<= steps; i++) {
						x += xInc;
						y += yInc;
						
						g.drawString("-", (int)x, (int)y);
						
					}
				}
		});
		btnDrawLine.setBounds(985, 603, 96, 27);
		contentPane.add(btnDrawLine);
		
		
	}
}
