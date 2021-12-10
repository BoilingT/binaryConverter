package binaryConverter.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import binaryConverter.global.*;
import binaryConverter.converter.*;


public class Main {

	private static WindowHandler window = new WindowHandler(500, 300, "Decimal to Binary converter");
	private static JSpinner spinner = new JSpinner();
	
	private static JPanel resultPanel = new JPanel();
	private static JLabel outputField = new JLabel();
	
	
	public static void main(String[] args) {
		//Input field
		spinner.setFont(new Font("Arial", 0, 24));
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				changeEvent(e);
			}
		});
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().addKeyListener(new KeyListener(){
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				changeEvent(null);
				System.out.println("dsa");
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Output field:
		outputField.setFont(new Font("Arial", 0, 24));
		resultPanel.add(outputField);
		resultPanel.setLayout(new GridLayout(1,1));		
		
		window.getContentPane().setLayout(new GridLayout(1,2));
		window.getContentPane().add(spinner);
		window.getContentPane().add(resultPanel);
		//window.getContentPane().setBackground(Color.black);
		window.Show();
		
		changeEvent(null);

	}
	
	private static void changeEvent(ChangeEvent e) {
		JTextField field = ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField();
		new Thread(new BinaryHandler(Double.parseDouble(field.getText()), outputField)).start();
		//printText(BinaryHandler.DecToBin(Double.parseDouble(textField.getText())));
		//printText(String.valueOf(BinaryHandler.BinToDec(textField.getText())));
	}
	
	private static void appendText(String str) {
		outputField.setText(outputField.getText() + str);
	}
	
	private static void printText(String str) {
		outputField.setText(str);
	}

}
