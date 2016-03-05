package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/21.
 */
public class BLOCK extends Expr {
    public ArrayList<Expr> exprs;

    public BLOCK(int row, int col, ArrayList<Expr> es) {
        super(row, col);
        exprs = es;
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        Value v = null;
        for (Expr e : exprs) {
            v = e.interp(s, dh);
        }
        return v;
    }
}
