package orz.AST;

import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LOOP extends Expr {
    public ArrayList<Expr> locals;
    public Expr e;
    public ArrayList<Expr> recur;

    public LOOP(int row, int col, ArrayList<Expr> l, Expr e, ArrayList<Expr> rec) {
        super(row, col);
        type = "LOOP";
        locals = l;
        this.e = e;
        recur = rec;
    }

    @Override
    public String toString() {
        return "LOOP";
    }

    @Override
    public Value interp(Scope s) {
        return null;
    }
}
