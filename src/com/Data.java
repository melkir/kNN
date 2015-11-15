package com;

import java.util.Arrays;

/**
 * Created by melkir on 15-Nov-15.
 */
public class Data {
    private float[] values = new float[4];
    private final String classname;

    public Data(float[] values, String classname) {
        this.values = values;
        this.classname = classname;
    }


    public float[] getValues() {
        return values;
    }

    public String getClassname() {
        return classname;
    }

    @Override
    public String toString() {
        return "Data{" +
                "classname='" + classname + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
