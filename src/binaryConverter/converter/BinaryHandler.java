package binaryConverter.converter;

import javax.swing.JTextArea;

public class BinaryHandler implements Runnable{
	private Object input;
	private JTextArea out;
	
	public BinaryHandler(String binary, JTextArea out) {
		input = binary;
		this.out = out;
	}
	
	public BinaryHandler(double dec, JTextArea out) {
		input = dec;
		this.out = out;
	}
	
	public String ToBin(double dec) {
		//12
		
		String result = "";
		String decStr = String.valueOf(dec);
		double inputSize = decStr.length();
		
		int[] binary = new int[]{128, 64, 32, 16, 8, 4, 2, 1};
		int[] binResult = new int[8]; //128 64 32 16 8 4 2 1
		
		for (int i = 0; i < binary.length; i++) {
			double testValue = binary[i];
			int subtractions = 0;
			for (double j = dec; j >= testValue; j -= testValue) {
				subtractions++;
				if (subtractions >= 1) {
					break;
				}
			}
			binResult[i] = subtractions <= 0 ? 0 : subtractions;
			System.out.println("dec = " + dec + " :: " + testValue + ": " + subtractions);
			dec -= testValue * subtractions;
		}
		
		
		for (int i : binResult) {
			result += (i);
		}
		return result;
	}
	
	public double ToDec(String bin) {
		//1100 -> 4st
		double result = 0;
		int[] binary = new int[]{128, 64, 32, 16, 8, 4, 2, 1};
		int startIndex = binary.length-bin.length();
		
		int index = 0;
		for (int i = startIndex; i < 8; i++) {
			int binValue = binary[i];
			int binStrValue = Integer.parseInt(String.valueOf(bin.charAt(index++)));
			if (binStrValue >= 2) {
				return 0;
			}
//			double numb = Double.parseDouble(String.valueOf(bin.charAt(index)))+1;
			System.out.print("(" + binValue + "*" + (binStrValue) + ")"  + " + ");
			result +=  binValue * (binStrValue);
		}
		System.out.println();
		return result;
	}

	@Override
	public void run() {
		if (input.getClass().getTypeName() == Double.class.getTypeName()) {
			out.setText(ToBin((Double) input));
		}else if (input.getClass().getTypeName() == String.class.getTypeName()) {
			out.setText(String.valueOf(ToDec((String) input)));
		}
	}
}
