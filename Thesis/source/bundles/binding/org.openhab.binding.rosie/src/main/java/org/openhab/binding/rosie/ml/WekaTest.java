package org.openhab.binding.rosie.ml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;

public class WekaTest {


	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		try {
			FileReader fReader = new FileReader(filename);
			inputReader = new BufferedReader(fReader);
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename + " "
					+ ex.getMessage());
		}

		return inputReader;
	}

	public static void main(String[] args) throws Exception {
		// Declare a nominal attribute along with its values
		FastVector fvNominalVal = new FastVector(2);
		fvNominalVal.addElement("OPEN");
		fvNominalVal.addElement("CLOSED");
		Attribute movement = new Attribute("normal", fvNominalVal);
		FastVector fvNominalValOpen = new FastVector(2);
		fvNominalValOpen.addElement("YES");
		fvNominalValOpen.addElement("NO");
		Attribute action = new Attribute("action",fvNominalValOpen);

		// Declare the feature vector
		int sparceInstances = 2;
		FastVector fvWekaAttributes = new FastVector(sparceInstances);
		fvWekaAttributes.addElement(action);
		fvWekaAttributes.addElement(movement);


		// Create an empty training set
		Instances isTrainingSet = new Instances("Rel", fvWekaAttributes, 20);

		// Now, let’s fill the training set with one instance:

		// Create the instance for 1
		Instance iExample;
		for (int i = 0; i < 20; i++) {
			iExample = new SparseInstance(sparceInstances);
			iExample.setValue(new Attribute("CLOSED"),0);
			iExample.setValue(new Attribute("YES"),1);
			// add the instance
			isTrainingSet.add(iExample);
		}
		
		isTrainingSet.setClassIndex(isTrainingSet.numAttributes() - 1);
		// do not use first and second
		Instance first = isTrainingSet.instance(0);	
		
		
		
		Instance test = new SparseInstance(sparceInstances);
		test.setValue(new Attribute("OPEN"),0);
		test.setValue(new Attribute("YES"),1);
		test.setDataset(isTrainingSet);
		


		Classifier ibk = new IBk();
		ibk.buildClassifier(isTrainingSet);
		System.out.println(ibk.getCapabilities().toString());
		
        // Test the model
        Evaluation eTest = new Evaluation(isTrainingSet);
        eTest.evaluateModel(ibk, isTrainingSet);
         
        // Print the result à la Weka explorer:
        String strSummary = eTest.toSummaryString();
        System.out.println(strSummary);
         
        // Get the confusion matrix
        double[][] cmMatrix = eTest.confusionMatrix();
        for(int row_i=0; row_i<cmMatrix.length; row_i++){
            for(int col_i=0; col_i<cmMatrix.length; col_i++){
                System.out.print(cmMatrix[row_i][col_i]);
                System.out.print("|");
            }
            System.out.println();
        }
		
		
		
		
		
		
		
		
	}

}
