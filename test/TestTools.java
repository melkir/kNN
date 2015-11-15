import com.Data;
import org.junit.Test;

import static com.Tools.euclidianDistance;
import static org.junit.Assert.assertEquals;

/**
 * Created by melkir on 15-Nov-15.
 */
public class TestTools {

    @Test
    public void testEuclidianDistance() {
        Data data1 = new Data(new float[]{2, 2, 2}, "a");
        Data data2 = new Data(new float[]{4, 4, 4}, "b");
        double distance = euclidianDistance(data1.getValues(), data2.getValues());
        assertEquals(3.46, distance, 0.01);
    }

}
