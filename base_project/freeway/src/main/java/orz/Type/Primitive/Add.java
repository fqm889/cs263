package orz.Type.Primitive;

import orz.Type.*;

import java.util.ArrayList;
import orz.AST.Expr;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Add extends PrimitiveFunction {
    public Add() {
        super("+", 2);
    }

    @Override
    public Value apply(ArrayList<Value> args) {
        Value v1 = args.get(0);
        Value v2 = args.get(1);

        if (v1 instanceof IntValue && v2 instanceof IntValue) {
            return new IntValue(((IntValue) v1).value + ((IntValue) v2).value);
        }
        if (v1 instanceof RealValue && v2 instanceof RealValue) {
            return new RealValue(((RealValue) v1).value + ((RealValue) v2).value);
        }
        if (v1 instanceof RealValue && v2 instanceof IntValue) {
            return new RealValue(((RealValue) v1).value + ((IntValue) v2).value);
        }
        if (v1 instanceof IntValue && v2 instanceof RealValue) {
            return new RealValue(((IntValue) v1).value + ((RealValue) v2).value);
        }

        return null;
    }
}

