package orz.Type.Primitive;

import orz.Type.*;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class NOT extends PrimitiveFunction {

    public NOT() {
        super("not", 1);
    }

    @Override
    public Value apply(ArrayList<Value> args) {
        Value v1 = args.get(0);
        if (v1 instanceof BoolValue) {
            return new BoolValue(!((BoolValue) v1).value);
        }

        return null;
    }
}
