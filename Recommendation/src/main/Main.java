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

		g.getResult(new Double[] { 0.0, 0.166666667, 0.166666667, 0.666666667,
				1.0, 0.095238095 });

	}

}