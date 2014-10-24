package main;

import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

public class GeostepNeuralNetwrok {
//	NeuralNetwork loadedPerceptron;

//	public void start() {
//		loadedPerceptron = NeuralNetwork
//				.createFromFile("src/resources/Geostep.nnet");
//		// DataSet testSet = DataSet.load("src/resources/test.tset");
//		// testSet.addRow(new DataSetRow(new double[]{0.1, 0.2, 0.3, 0.4, 0.55,
//		// 0.65, 0.75, 0.8, 0.9, 0.15, 0.25, 0.37, 0.42}, new double[]{0, 0,
//		// 1}));
////		testNeuralNetwork(loadedPerceptron, testSet);
//	}

//	public void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {
//		for (DataSetRow testElement : testSet.getRows()) {
//			neuralNet.setInput(testElement.getInput());
//			neuralNet.calculate();
//			double[] networkOutput = neuralNet.getOutput();
//			double[] desiredOutput = testElement.getDesiredOutput();
//
//			System.out.println("Input: "
//					+ Arrays.toString(testElement.getInput()));
//			System.out.println("\tOutput: " + Arrays.toString(networkOutput));
//			System.out.println("\tDesired output: "
//					+ Arrays.toString(desiredOutput));
//		}
//	}

	public double getResult(Double[] input) {
		NeuralNetwork geostepNetwork = NeuralNetwork
				.createFromFile("src/resources/Geostep.nnet");
		
		double [] array = new double[input.length];
		for (int i = 0; i < input.length; i++) {
			array[i] = input[i].doubleValue();
		}
		
		geostepNetwork.setInput(array);
		geostepNetwork.calculate();
		double[] networkOutput = geostepNetwork.getOutput();

		//System.out.println("Input: " + Arrays.toString(input));
		//System.out.println("\tOutput: " + Arrays.toString(networkOutput));
		
		return networkOutput[0];
	}
}