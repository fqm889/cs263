package orz.AST;

import orz.Scope;
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
    public Value interp(Scope s) {
        return null;
    }
}
