import com.Data;
import com.Tools;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.Main.getAccuracy;
import static org.junit.Assert.assertEquals;

/**
 * Created by melkir on 15-Nov-15.
 */
public class TestKNN {

    @Test
    public void testAccuracyPrediction() {
        ArrayList<Data> testSet = new ArrayList<>();
        testSet.add(new Data(new float[]{1, 1, 1}, "a"));
        testSet.add(new Data(new float[]{2, 2, 2}, "a"));
        testSet.add(new Data(new float[]{3, 3, 3}, "b"));
        ArrayList<String> prediction = new ArrayList<>();
        for (int i = 0; i < 3; i++) prediction.add("a");
        double accuracy = getAccuracy(testSet, prediction);
        assertEquals(66.66, accuracy, 0.01);
    }

    @Test
    public void printDataToString() {
        Data data = new Data(new float[]{1, 1, 1}, "c");
        String output = "Data{" + "classname='" + data.getClassname() + '\'' +
                ", values=" + Arrays.toString(data.getValues()) + '}';
        assertEquals(output, data.toString());
    }

}
