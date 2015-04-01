package main;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
//		ReadClues rc = new ReadClues("142670");
//
//		ArrayList<Clue> a = rc.returnAllClues();
//		for (Clue clue : a) {
//			System.out.println(clue.getLat());
//		}
		GeostepNeuralNetwork g = new GeostepNeuralNetwork();

		double[] input = {0.0,1.0,4.0,1.0,1.0,366.0};
		
		long startTime = System.nanoTime();
		g.getResultTest(input);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("\nTrajanje:" + (duration/1000.00) + " microseconds");
		
	

	}

}