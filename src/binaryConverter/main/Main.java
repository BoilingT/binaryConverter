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
	private static JTextArea inputField = new JTextArea("0");
	
	private static JPanel resultPanel = new JPanel();
	private static JTextArea outputField = new JTextArea("NULL");
	
	public static void main(String[] args) {
		//Input field
		inputField.setFont(new Font("Arial", 0, 24));
		
		inputField.addKeyListener(decListener);
		outputField.addKeyListener(binListener);
		
		//Output field:
		outputField.setFont(new Font("Arial", 0, 24));
		resultPanel.add(outputField);
		resultPanel.setLayout(new GridLayout(1,1));		
		
		window.getContentPane().setLayout(new GridLayout(1,2));
		window.getContentPane().add(inputField);
		window.getContentPane().add(resultPanel);
		window.Show();
	}
	
	private static void printDecToBin(double dec) {
		if (dec != 0) {
			new Thread(new BinaryHandler(dec, outputField)).start();			
		}
	}
	
	private static void printBinToDec(String bin) {
		if (bin.length() > 0) {
			new Thread(new BinaryHandler(bin, inputField)).start();						
		}
	}
	
	private static KeyListener decListener = new KeyListener(){
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (inputField.getText().length() > 0) {
				try {
					printDecToBin(Double.parseDouble(inputField.getText().split("\\D+")[0]));									
				} catch (Exception ex) {
					return;
				}
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {}
	};
	
	private static KeyListener binListener = new KeyListener(){
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (outputField.getText().length() > 0) {
				try {
					String bin = outputField.getText().split("\\D+")[0];
					printBinToDec(bin);									
				} catch (Exception ex) {
					return;
				}
			}
			System.out.println();
		}
		
		@Override
		public void keyPressed(KeyEvent e) {}
	};
}
