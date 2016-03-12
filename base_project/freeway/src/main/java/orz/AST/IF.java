package orz.AST;

import orz.DebugHandler;
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
    public Value interp(Scope s, DebugHandler dh) {
        Value v = pred.interp(s, dh);
        dh.update(s, v);
        dh.block(this.row,this.col);
        //System.out.println(v.toString());
        if (((BoolValue)v).value) {
            return T.interp(s, dh);
        }
        else {
            return F.interp(s, dh);
        }
    }

    @Override
    public String toString(){
        return "IF"+pred.toString()+T.toString()+F.toString();
    }
}
