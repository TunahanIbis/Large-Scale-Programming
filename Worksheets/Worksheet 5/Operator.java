
public class Operator {
	public static double sum(int[] arr, int numThreads) throws InterruptedException {

		// Create variables for specific operations
		int size = arr.length;
		int elementsPerThread = size / numThreads;
		SumThread[] threads = new SumThread[numThreads];

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			// Operate the SumThread class with the array, min and max values
			threads[i] = new SumThread(arr, i * elementsPerThread, Math.min((i + 1) * elementsPerThread, size));
			threads[i].start(); // Start the thread
		}

		double sum = 0;

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			threads[i].join(); // Make thread join
			sum += threads[i].getAnswer(); // Get the answer
		}
		return sum;
	}

	// Thread for finding the minimum element
	public static class MinThread extends OperationThread {

		public MinThread(int[] arr, int min, int max) {
			super(arr, min, max);
		}

		// Operation method to get the answers if there is no error
		@Override
		public double operation(int x) {
			if (this.getAnswer() == 0 || x < this.getAnswer()) {
				return x;
			}
			return this.getAnswer(); // Get the answer
		}
	}

	// The min method that overrides the MinThreads
	public static double min(int[] arr, int numThreads) throws InterruptedException {

		int size = arr.length;
		int elementsPerThread = size / numThreads;

		MinThread[] threads = new MinThread[numThreads]; // Update the variable

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			int start = i * elementsPerThread; // Calculate the start index to tell method to start
			int end = Math.min((i + 1) * elementsPerThread, size); // Calculate the end index to tell method to stop

			threads[i] = new MinThread(arr, start, end); // Connect the MinThread to the main thread named "threads"
			threads[i].start(); // Start the thread
		}

		// Initialize the minVal with the maximum possible value
		double minVal = Double.MAX_VALUE;

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			threads[i].join(); // Make the thread join
			if (threads[i].getAnswer() < minVal) {
				minVal = threads[i].getAnswer(); // Update the minVal if the thread is less than the current minimum
													// value
			}
		}

		return minVal;
	}

	// Thread for finding the maximum element
	public static class MaxThread extends OperationThread {
		public MaxThread(int[] arr, int min, int max) {
			super(arr, min, max);
		}
	
		// Operation method to get the answers if there is no error
		@Override
		public double operation(int x) {
			return Math.max(this.getAnswer(), x);
		}
	}

	// The min method that overrides the MaxThread
	public static double max(int[] arr, int numThreads) throws InterruptedException {
		int size = arr.length;
		int elementsPerThread = size / numThreads;
		MaxThread[] threads = new MaxThread[numThreads];

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			// Initialize the i indexed thread in the threads array
			threads[i] = new MaxThread(arr, i * elementsPerThread,
					i == numThreads - 1 ? size : (i + 1) * elementsPerThread);
			threads[i].start();
		}

		// Initialize the maxVal with the minimum possible value
		double maxVal = Integer.MIN_VALUE;

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			threads[i].join(); // Make the thread join
			maxVal = Math.max(maxVal, threads[i].getAnswer());
		}
		return maxVal;
	}

	// Thread for multiplying the elements in the array
	public static class MultiplyThread extends OperationThread {
		public MultiplyThread(int[] arr, int min, int max) {
			super(arr, min, max);
			this.setAnswer(1); // Initialize the answer with 1 for multiplication
		}

		// Operation method to get the answers if there is no error
		@Override
		public double operation(int x) {
			return this.getAnswer() * x;
		}
	}

	// The multiply method
	public static double multiply(int[] arr, int numThreads) throws InterruptedException {
		int size = arr.length;
		int elementsPerThread = size / numThreads;

		MultiplyThread[] threads = new MultiplyThread[numThreads]; // Update the variable

		// The loop for numThreads
		for (int i = 0; i < numThreads; i++) {
			int start = i * elementsPerThread; // Calculate the start index to tell method to start
			int end = Math.min((i + 1) * elementsPerThread, size); // Calculate the end index to tell method to stop

			threads[i] = new MultiplyThread(arr, start, end); // Connect the MultiplyThread to the main thread named
																// "threads"
			threads[i].start(); // Start the thread
		}

		double result = 1; // Initialize the result with the value 1 to avoid getting 0 as an output

		for (int i = 0; i < numThreads; i++) {
			threads[i].join(); // Make the thread join
			result *= threads[i].getAnswer(); // Get the answer
		}

		return result; // Get the result
	}

	// Method to calculate the average
	public static double average(int[] arr, int numThreads) throws InterruptedException {
		// Use the existing sum method to get the sum of elements
		double sum = sum(arr, numThreads);

		// Calculate the average
		int size = arr.length;
		return sum / size;
	}

	// Main Method
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13 };
		try {
			System.out.println("Result for the Operator.sum is: " + Operator.sum(arr, 3));
			System.out.println("Result for the Operator.min is: " + Operator.min(arr, 3));
			System.out.println("Result for the Operator.max is: " + Operator.max(arr, 3));
			System.out.println("Result for the Operator.average is: " + Operator.average(arr, 3));
			System.out.println(
					"Result (in a scientific format) for the Operator.multiply is: " + Operator.multiply(arr, 3));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
