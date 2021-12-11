package binaryConverter.converter;

import javax.swing.JTextArea;

public class BinaryHandler implements Runnable{
	private static int[] BINNUMBS = new int[]{128, 64, 32, 16, 8, 4, 2, 1};
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
		String result = "";
		
		int[] binResult = new int[8];

		if (String.valueOf(dec).length() <= 0) {
			for (int i : binResult) {
				result += (i);
			}
			return result;
		}
		
		for (int i = 0; i < BINNUMBS.length; i++) {
			double testValue = BINNUMBS[i];
			for (double j = dec; j >= testValue; j -= testValue) {
				if (binResult[i] >= 1) break;
				binResult[i]++;
			}
			//System.out.println("dec = " + dec + " :: " + testValue + ": " + subtractions);
			dec -= testValue * binResult[i];
		}

		for (int i : binResult) {
			result += (i);
		}
		return result;
	}
	
	public double ToDec(String bin) {
		//1100 -> 4st
		double result = 0;
		int startIndex = BINNUMBS.length-bin.length();
		
		int index = 0;
		for (int i = startIndex; i < 8; i++) {
			int binValue = BINNUMBS[i];
			int binStrValue = Integer.parseInt(String.valueOf(bin.charAt(index++)));
			if (binStrValue >= 2) return 0;
			//System.out.print("(" + binValue + "*" + (binStrValue) + ")"  + " + ");
			result +=  binValue * (binStrValue);
		}
		//System.out.println();
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
