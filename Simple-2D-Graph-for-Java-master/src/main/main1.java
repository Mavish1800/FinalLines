package main;

import javax.swing.JOptionPane;

import computerGraphics.Bresenham;
import computerGraphics.DDA;

public class main1{
    public static void main(String[] args) {
    	int choice;
    	do {
    	String boxChoice=JOptionPane.showInputDialog("Enter number 1 to draw line using DDA Algorithm.\nEnter number 2 to draw line using Bresenham Algorithm. ");

    	choice=Integer.parseInt(boxChoice);
    	
    	if(choice==1) {
    		
    		new DDA().setVisible(true);
    	}
    	else if(choice==2) {
    		
    		new Bresenham().setVisible(true);
    	}
    	else {
    		
    		System.out.print("Enter number 1 or number 2 only!!!");
    		
    	}
    	}while((choice < 1) || (choice > 2));
    		
        
    }
}
