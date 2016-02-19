package orz.Type;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class VectorValue extends Value {
    public ArrayList<Value> values;

    public VectorValue(ArrayList<Value> values) {
        this.values = values;
    }

    public String toString() {
        return values.toString();
    }
}
