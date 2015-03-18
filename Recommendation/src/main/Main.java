package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
//		WineNeuralNetwrok wineNeuralNetwrok = new WineNeuralNetwrok();
//		wineNeuralNetwrok.start();
//		ArrayList<String> x = new ArrayList<String>();
//		x.add("142670");
//		x.add("social");
//		x.add("bussines");
//		x.add("travel");
//		x.add("irrelevant");
//		x.add("social");
//		x.add("bussines");
//		x.add("travel");
//		x.add("irrelevant");
//		String[] xA = {"142670","social","bussines","travel","irrelevant","social","bussines","travel","irrelevant"};
//		ReadClues rc = new ReadClues("580098");
////		rc.printAllClues();
//		System.out.println(rc.returnCategoriesCount(xA));
		
//		GeostepNeuralNetwrok geostepNeuralNetwrok = new GeostepNeuralNetwrok();
//		geostepNeuralNetwrok.start();
	//	System.out.println(
		GeostepNeuralNetwork g  = new GeostepNeuralNetwork();
		
		g.getResult(new Double[]{0.0,0.166666667,0.166666667,0.666666667,1.0,0.095238095});
		
	}

	
}