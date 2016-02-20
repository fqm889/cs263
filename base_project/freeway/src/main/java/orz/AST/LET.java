package orz.AST;

import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LET extends Expr {
    public ArrayList<Expr> locals;
    public Expr e;

    public LET(int row, int col, ArrayList<Expr> l, Expr e) {
        super(row, col);
        type = "LET";
        locals = l;
        this.e = e;
    }

    @Override
    public String toString() {
        return "LET";
    }

    @Override
    public Value interp(Scope s) {
        return null;
    }
}
