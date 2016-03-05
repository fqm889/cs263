package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.BoolValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Bool extends Expr {
    public boolean v;
    public Bool(int row, int col, boolean b) {
        super(row, col);
        type = "Bool";
        v = b;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        dh.block();
        return new BoolValue(v);
    }
}