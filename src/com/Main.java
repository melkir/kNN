package com;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Source : http://machinelearningmastery.com/tutorial-to-implement-k-nearest-neighbors-in-python-from-scratch/
 */
public class Main {

    /**
     * Load the data set from the file and create the training set and test set.
     *
     * @param filename    the name of the file
     * @param split       the ratio between training set and test set, 66 training for 34 test is a good value
     * @param trainingSet the train set
     * @param testSet     the test set
     */
    public static void loadDataset(String filename, int split, ArrayList<Data> trainingSet, ArrayList<Data> testSet) {
        ArrayList<String> lines = Tools.reader(filename);
        Data data;
        for (int x = 0; x < lines.size() - 1; x++) {
            data = Tools.parseLine(lines.get(x), ",");
            if (Tools.random(0, 100) < split) trainingSet.add(data);
            else testSet.add(data);
        }
    }

    /**
     * Collect the k most similar instance for a given unseen instance
     *
     * @param trainingSet  the data used for training
     * @param testInstance the data to compare
     * @param k            the number of the most similar neighbors to return
     * @return k most similar neighbors from the training set for a given test instance with the euclidian distance
     */
    public static ArrayList<Data> getNeighbors(ArrayList<Data> trainingSet, float[] testInstance, int k) {
        Map<Data, Double> distances = new HashMap<>();

        for (Data data : trainingSet) {
            double dist = Tools.euclidianDistance(testInstance, data.getValues());
            distances.put(data, dist);
        }

        List<Data> sortedNeighbors = distances.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());

        ArrayList<Data> res = new ArrayList<>(sortedNeighbors.size());
        res.addAll(sortedNeighbors);

        return res;
    }

    /**
     * Devise a predicted response based on those neighbors
     *
     * @param neighbors the list of neighbors
     * @return the majority voted response from a number of neighbors
     */
    public static String getResponse(ArrayList<Data> neighbors) {
        Map<String, Integer> classVote = new HashMap<>();
        for (Data data : neighbors) {
            String response = data.getClassname();
            if (classVote.containsKey(response))
                classVote.put(response, classVote.get(response) + 1);
            else classVote.put(response, 1);
        }

        List<String> sortedClassVote = classVote.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // last value is the class vote score
        return sortedClassVote.get(sortedClassVote.size() - 1);
    }

    /**
     * Evaluate the accuracy of predictions
     *
     * @param testSet    the data to test
     * @param prediction the real class of data
     * @return the accuracy of the prediction
     */
    public static double getAccuracy(ArrayList<Data> testSet, ArrayList<String> prediction) {
        int correct = 0;
        for (int i = 0; i < testSet.size(); i++)
            if (testSet.get(i).getClassname().equals(prediction.get(i))) ++correct;
        return ((double) correct / (double) testSet.size() * 100.0);
    }

    public static void main(String[] args) {
        // prepare data
        ArrayList<Data> trainingSet = new ArrayList<>();
        ArrayList<Data> testSet = new ArrayList<>();
        int split = 66;
        loadDataset("iris.data", split, trainingSet, testSet);
        System.out.println("Train set: " + trainingSet.size());
        System.out.println("Test set: " + testSet.size());

        // generate prediction
        ArrayList<String> prediction = new ArrayList<>();
        int k = 3;
        for (Data data : testSet) {
            ArrayList<Data> neighbours = getNeighbors(trainingSet, data.getValues(), k);
            String result = getResponse(neighbours);
            prediction.add(result);
            System.out.println("> predicted=" + result + ", actual=" + data.getClassname());
        }

        double accuracy = getAccuracy(testSet, prediction);
        System.out.println(accuracy + "%");

    }

}
