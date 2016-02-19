package orz.Type.Primitive;

import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */

public abstract class PrimitiveFunction extends Value {
    public String name;
    public int arity;

    public PrimitiveFunction(String name, int arity) {
        this.name = name;
        this.arity = arity;
    }

    public abstract Value apply(ArrayList<Value> args);

    public String toString() {
        return name;
    }

}