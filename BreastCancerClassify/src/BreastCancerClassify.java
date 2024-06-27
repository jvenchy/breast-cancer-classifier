/**
 * Name:
 * Josh Shergill
 * Period 5
 * 
 * BreastCancerClassify contains the core implementation of the 
 * kNearestNeighbors algorithm to classify cell clumps as malignant
 * or benign. 
 */
public class BreastCancerClassify {
	
	public static final Integer K = 5;
    public static final Integer BENIGN = 2;
    public static final Integer MALIGNANT = 4;
	
	/**
	 * calculateDistance computes the distance between the two data
	 * parameters. 
	 */
	public static double calculateDistance(int[] first, int[] second)
	{
		//variable for summing squared differences
		double sum = 0.0;
		
		//iterates through the two points 
		for (int i = 1; i < first.length - 1; i++)
		{
			//variable finds difference between two points and resets 
			//each iteration as we move to next coordinate
			double diff = 0.0;
			diff = first[i] - second[i];
			
			//squaring the difference and adding it to total summation 
			sum += diff*diff;
		}
		
		//return double for the square root of the total summation
		return Math.sqrt(sum);
	}
	
	/**
	 * getAllDistances creates an array of doubles with the distances
	 * to each training instance. The double[] returned should have the 
	 * same number of instances as trainData. 
	 */
	public static double[] getAllDistances(int[][] trainData, int[] testInstance)
	{
		//create new array of trainData length
		double[] allDistances = new double[trainData.length];
		
		//iterates through trainData setting allDistances to corresponding values
		for (int i = 0; i < trainData.length; i++)
		{
			//calls calculateDistance method and sets it to array index
			allDistances[i] = calculateDistance(testInstance, trainData[i]);
		}
		
		//return array
		return allDistances;
	}
	
	/**
	 * findKClosestEntries finds and returns the indexes of the 
	 * K closest distances in allDistances. Return an array of size K, 
	 * that is filled with the indexes of the closest distances (not
	 * the distances themselves). 
	 * 
	 * Be careful! This method can be tricky.
	 */
	public static int[] findKClosestEntries(double[] allDistances)
	{
		//create new array of K length
		int[] kClosestIndexes = new int [K];
		
		//duplicating allDistances array to be able to modify its values
		double[] distClone = allDistances;
		
		//sets kClosestIndexes to K iterated minimum values in distClone
		for (int i = 0; i < K; i++)
		{
			//variables
			int minDistIndex = 0;
			double minDist = Double.MAX_VALUE;
			
			//finding minimum value in distClone
			for (int j = 0; j < allDistances.length; j++)
			{
				if (distClone[j] < minDist)
				{
					minDist = distClone[j];
					
					//tracking the index of minimum value
					minDistIndex = j;
				}
			}
			
			//add index holding smallest distance in distClone to kClosestIndexes
			kClosestIndexes[i] = minDistIndex;
			
			//erase current minimum value in distClone to be able to find next lowest during next iteration
			distClone[minDistIndex] = Double.MAX_VALUE;
			
		}
		
		//return array
		return kClosestIndexes;
	}
	
	/**
	 * classify makes a decision as to whether an instance of testing 
	 * data is BENIGN or MALIGNANT. The function makes this decision based
	 * on the K closest train data instances (whose indexes are stored in 
	 * kClosestIndexes). If more than half of the closest instances are 
	 * malignant, classify the growth as malignant. Otherwise classify
	 * as benign.
	 * 
	 * Return one of the global integer constants defined in this function. 
	 */
	public static int classify(int[][] trainData, int[] kClosestIndexes)
	{
		//counter variables
		int benignCount = 0;
		int maligCount = 0;
		
		//iterates through kClosestIndexes and adds to counter based on what label is for a trainData point
		for (int i = 0; i < kClosestIndexes.length; i++)
		{
			//benign counter if label corresponds to benign
			if (trainData[kClosestIndexes[i]][trainData[i].length - 1] == BENIGN)
			{
				benignCount ++;
			}
			
			//malignant counter if label corresponds to malignant
			if (trainData[kClosestIndexes[i]][trainData[i].length - 1] == MALIGNANT)
			{
				maligCount ++;
			}
		}
		
		//decides whether to return constant for malignant or benign based on which count is higher
		if (maligCount > benignCount)
		{
			return MALIGNANT;
		}
		return BENIGN;
	}
	
	/**
	 * kNearestNeighbors classifies all the data instances in testData as 
	 * BENIGN or MALIGNANT using the helper functions you wrote and the kNN 
	 * algorithm.
	 * 
	 * For each instance of your test data, use your helper methods to find the
	 * K closest points, and classify your result based on that!
	 * @param trainData: all training instances
	 * @param testData: all testing instances
	 * @return: int array of classifications (BENIGN or MALIGNANT)
	 */
	public static int[] kNearestNeighbors(int[][] trainData, int[][] testData){
		
		//create new array of testData length
		int[] myResults = new int[testData.length];
		
		//iterates through testData and set myResults to malignant or benign accordingly
		for (int i = 0; i < testData.length; i++)
		{
			//add to myResults by calling classify(), which needs array kClosestIndexes from calling 
			//findKClosestEntries(), which needs array allDistances from calling getAllDistances, which 
			//needs trainData and a test instance from testData
			myResults[i] = classify(trainData, findKClosestEntries(getAllDistances(trainData, testData[i])));
		}
		
		//return array
		return myResults;
	}

	/**
	 * getAccuracy returns a String representing the classification accuracy.
	 *
	 * The return String should be rounded to two decimal places followed by the % symbol.
	 * Examples:
	 * If 4 out of 5 outcomes were correctly predicted, the returned String should be: "80.00%"
	 * If 3 out of 9 outcomes were correctly predicted, the returned String should be: "33.33%"
	 * If 6 out of 9 outcomes were correctly predicted, the returned String should be: "66.67%"
	 * Look up Java's String Formatter to learn how to round a double to two-decimal places.
	 *
	 * This method should work for any data set, given that the classification outcome is always
	 * listed in the last column of the data set.
	 * @param: myResults: The predicted classifications produced by your KNN model
	 * @param: testData: The original data that contains the true classifications for the test data
	 */
	public static String getAccuracy(int[] myResults, int[][] testData) {
		
		//variables for percentage and counter
		double percentSuccess = 0.0;
		double rightCount = 0.0;
		
		//iterates through myResults and adds to counter if there's a match in testData at the same point
		for(int i = 0; i < myResults.length; i++)
		{
			if(myResults[i] == testData[i][testData[i].length - 1])
			{
				rightCount ++;
			}
		}
		
		//calculates percentage correct by dividing counter by total results and multiplying by 100
		percentSuccess = (rightCount/(myResults.length)) * 100;
		
		//returns string with percentage rounded to two decimal places and "%" symbol
		return String.format("%,.2f", percentSuccess) + "%";
		
	}
	
	
	//DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {

		int[][] trainData = InputHandler.populateData("./datasets/train_data.csv");
		int[][] testData = InputHandler.populateData("./datasets/test_data.csv");
		
		//Display the distances between instances of the train data. 
		//Points in the upper left corner (both benign) or in the bottom
		//right (both malignant) should be darker. 
		Grapher.createGraph(trainData);

		int[] myResults = kNearestNeighbors(trainData, testData);

		System.out.println("Model Accuracy: " + getAccuracy(myResults, testData));
	}

}
