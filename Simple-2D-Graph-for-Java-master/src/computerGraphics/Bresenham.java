package computerGraphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import graph.SimpleGraph;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bresenham extends JFrame {

	private JPanel contentPane;
	private JTextField txtx1;
	private JTextField txty1;
	private JLabel lblY;
	private JTextField txtx2;
	private JLabel lblX;
	private JTextField txty2;
	private JLabel lblY_1;
	private JButton btnDrawLine;
	SimpleGraph BRES_panel = new SimpleGraph();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bresenham frame = new Bresenham();
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
	public Bresenham() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1109, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		BRES_panel.centralize();
		BRES_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		BRES_panel.setBounds(10, 10, 948, 645);
		contentPane.add(BRES_panel);
		
		txtx1 = new JTextField();
		txtx1.setBounds(989, 86, 96, 19);
		contentPane.add(txtx1);
		txtx1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("X1");
		lblNewLabel.setBounds(989, 63, 45, 13);
		contentPane.add(lblNewLabel);
		
		txty1 = new JTextField();
		txty1.setColumns(10);
		txty1.setBounds(989, 181, 96, 19);
		contentPane.add(txty1);
		
		lblY = new JLabel("Y1");
		lblY.setBounds(989, 158, 45, 13);
		contentPane.add(lblY);
		
		txtx2 = new JTextField();
		txtx2.setColumns(10);
		txtx2.setBounds(989, 285, 96, 19);
		contentPane.add(txtx2);
		
		lblX = new JLabel("X2");
		lblX.setBounds(989, 262, 45, 13);
		contentPane.add(lblX);
		
		txty2 = new JTextField();
		txty2.setColumns(10);
		txty2.setBounds(989, 392, 96, 19);
		contentPane.add(txty2);
		
		lblY_1 = new JLabel("Y2");
		lblY_1.setBounds(989, 369, 45, 13);
		contentPane.add(lblY_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Solid", "Dotted", "Dashed"}));
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(989, 444, 96, 21);
		contentPane.add(comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setBounds(1032, 542, 53, 29);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("Thickness");
		lblNewLabel_1.setBounds(1032, 519, 45, 13);
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
				
				Graphics g = BRES_panel.getGraphics();
				
				if (type == "Solid") {
					LineBresSolid(x1 , y1 , x2 , y2 , g);
				}
				else if (type == "Dotted") {
					LineBresDotted(x1 , y1 , x2 , y2 , g);
				}
				else if (type == "Dashed"){
					LineBresDashed(x1 , y1 , x2 , y2 , g);
				}
				
			}

			//Solid Line
			private void LineBresSolid(double x1, double y1, double x2, double y2, Graphics g) {
				// TODO Auto-generated method stub
				
				//dx = run ;
				//dy = rise;
				//xEnd maximum x to terminate loop
				//xy = point to plot
				
				double dx , dy , xEnd , p , x , y ;
				dx = Math.abs(x1 - x2);
				dy = Math.abs(y1 - y2);
				p = (2 * dy) - dx ;
				
				if (x1 >= x2) {
					x = x2;
					y = y2;
					xEnd = x1;
				}
				else
				{
					x = x1;
					y= y1;
					xEnd = x2;
				}


				x1=x;
				y1=y;
				while(x < xEnd) {
					x++;
					if(p < 0) {
						p = p + (2 * dy);
					}
					else
					{
						y++;
						p = p + ((2 * dy) - (2 * dx));
					}
				
					
					BRES_panel.addShape(new SimpleGraph.Line(x1, y1, x, y, Color.BLUE));
					
					x1=x;
					y1=y;
				}
			}
			
			//Dotted Line
			private void LineBresDotted(double x1, double y1, double x2, double y2, Graphics g) {
				// TODO Auto-generated method stub
				
				//dx = run ;
				//dy = rise;
				//xEnd maximum x to terminate loop
				//xy = point to plot
				
				double dx , dy , xEnd , p , x , y ;
				dx = Math.abs(x1 - x2);
				dy = Math.abs(y1 - y2);
				p = (2 * dy) - dx ;
				
				if (x1 >= x2) {
					x = x2;
					y = y2;
					xEnd = x1;
				}
				else
				{
					x = x1;
					y= y1;
					xEnd = x2;
				}

				
				BRES_panel.addPoint(x, y);
				
				while(x < xEnd) {
					x++;
					if(p < 0) {
						p = p + (2 * dy);
					}
					else
					{
						y++;
						p = p + ((2 * dy) - (2 * dx));
					}
					
					BRES_panel.addPoint(x, y);
				}
				
			}
			
			
			//Dashed Line
			private void LineBresDashed(double x1, double y1, double x2, double y2, Graphics g) {
				// TODO Auto-generated method stub
				
				//dx = run ;
				//dy = rise;
				//xEnd maximum x to terminate loop
				//xy = point to plot
				
				double dx , dy , xEnd , p , x , y ;
				dx = Math.abs(x1 - x2);
				dy = Math.abs(y1 - y2);
				p = (2 * dy) - dx ;
				
				if (x1 >= x2) {
					x = x2;
					y = y2;
					xEnd = x1;
				}
				else
				{
					x = x1;
					y= y1;
					xEnd = x2;
				}

				g.drawString("-", (int)x, (int)y);
				while(x < xEnd) {
					x++;
					if(p < 0) {
						p = p + (2 * dy);
					}
					else
					{
						y++;
						p = p + ((2 * dy) - (2 * dx));
					}
				
					g.drawString("-", (int)x, (int)y);
				}
				
			}
			
			
			
		});
		btnDrawLine.setBounds(989, 604, 96, 29);
		contentPane.add(btnDrawLine);
		
		
	}
}
