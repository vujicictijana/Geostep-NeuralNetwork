package main;
import org.neuroph.core.NeuralNetwork;

public class GeostepNeuralNetwork {

	@SuppressWarnings("rawtypes")
	NeuralNetwork geostepNetwork = NeuralNetwork
	.createFromFile("src/resources/Geostep.nnet");
	
	public double getResult(Double[] input) {
		
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