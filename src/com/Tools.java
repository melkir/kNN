package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by melkir on 15-Nov-15.
 */
public class Tools {

    /**
     * Random function for Java 1.7 or later
     *
     * @param min the min value
     * @param max the max value
     * @return A random value between min and max
     */
    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Read the data file line by line
     *
     * @param name the file name
     * @return An ArrayList that store line by line the data content
     */
    public static ArrayList<String> reader(String name) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert scanner != null;
        ArrayList<String> res = new ArrayList<>();
        while (scanner.hasNextLine())
            res.add(scanner.nextLine());
        scanner.close();
        return res;
    }

    /**
     * Parse a line from the String in order to convert String[] to float[]
     *
     * @param data      the line the contain the float values
     * @param delimiter the delimiter between each values
     * @return An float[] that contain each float values of the line
     */
    public static Data parseLine(String data, String delimiter) {
        String[] content = data.split(delimiter, 5);
        String name = content[4];
        float[] values = new float[4];
        for (int i = 0; i < 4; i++) values[i] = Float.parseFloat(content[i]);
        return new Data(values, name);
    }

    /**
     * Calculate the euclidian distance between two data
     *
     * @param instance1 data set 1
     * @param instance2 data set 2
     * @return the euclidian distance at 2 decimals between instance 1 and instance 2
     */
    public static double euclidianDistance(float[] instance1, float[] instance2) {
        double distance = 0;
        for (int x = 0; x < instance1.length; x++)
            distance += Math.pow(instance1[x] - instance2[x], 2);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        return Double.parseDouble(numberFormat.format(Math.sqrt(distance)));
    }

}
