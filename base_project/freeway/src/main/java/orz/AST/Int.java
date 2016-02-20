package orz.AST;

import orz.Scope;
import orz.Type.IntValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Int extends Expr {
    public int v;
    public Int(int row, int col, int i) {
        super(row, col);
        type = "Int";
        v = i;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

    @Override
    public Value interp(Scope s) {
        return new IntValue(v);
    }
}
