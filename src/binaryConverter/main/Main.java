package binaryConverter.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import binaryConverter.global.*;
import binaryConverter.converter.*;


public class Main {

	private static WindowHandler window = new WindowHandler(500, 500, "Decimal to Binary converter");
	private static JTextArea textField = new JTextArea("1100");
	
	private static JPanel resultPanel = new JPanel();
	private static JTextArea outputField = new JTextArea();
	
	
	public static void main(String[] args) {
		//Input field
		textField.setFont(new Font("Arial", 0, 24));
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				KeyPressed(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//Output field:
		outputField.setEditable(false);
		outputField.setFont(new Font("Arial", 0, 24));
		resultPanel.add(outputField);
		resultPanel.setLayout(new GridLayout(1,1));		
		
		window.getContentPane().setLayout(new GridLayout(2,1));
		window.getContentPane().add(textField);
		window.getContentPane().add(resultPanel);
		//window.getContentPane().setBackground(Color.black);
		window.Show();
		
		KeyPressed(null);

	}
	
	private static void KeyPressed(KeyEvent e) {
		new Thread(new BinaryHandler(textField.getText(), outputField)).start();
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
