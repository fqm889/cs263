package orz.Type.Primitive;

import orz.Type.*;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class AND extends PrimitiveFunction {

    public AND() {
        super("and", 2);
    }

    @Override
    public Value apply(ArrayList<Value> args) {
        Value v1 = args.get(0);
        Value v2 = args.get(1);
        if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
            return new BoolValue(((BoolValue) v1).value && ((BoolValue) v2).value);
        }

        return null;
    }
}
