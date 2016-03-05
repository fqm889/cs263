package orz.AST;

import orz.DebugHandler;
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
    public Value interp(Scope s, DebugHandler dh) {
        Value ret = new IntValue(v);
        dh.update(s, ret);
        dh.block();
        return ret;
    }
}
