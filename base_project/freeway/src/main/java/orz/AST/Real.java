package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.RealValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Real extends Expr {
    double d;
    public Real(int row, int col, double i) {
        super(row, col);
        type = "Real";
        d = i;
    }

    @Override
    public String toString() {
        return String.valueOf(d);
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        Value ret = new RealValue(d);
        dh.update(s, ret);
        dh.block(this.row,this.col);
        return ret;
    }
}
