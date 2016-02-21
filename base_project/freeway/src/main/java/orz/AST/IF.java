package orz.AST;

import orz.Scope;
import orz.Type.BoolValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class IF extends Expr {
    public Expr pred;
    public Expr T;
    public Expr F;

    public IF(int row, int col, Expr p, Expr t, Expr f) {
        super(row, col);
        type = "IF";
        pred = p;
        T = t;
        F = f;
    }

    @Override
    public Value interp(Scope s) {
        Value v = pred.interp(s);
        if (((BoolValue)v).value) {
            return T.interp(s);
        }
        else {
            return F.interp(s);
        }
    }

    @Override
    public String toString(){
        return "IF"+pred.toString()+T.toString()+F.toString();
    }
}
