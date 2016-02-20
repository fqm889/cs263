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
        Scope ns = new Scope(s);
        for (int i = 0; i < locals.size(); i+=2) {
            Symbol sym = (Symbol)(locals.get(i));
            Value val = locals.get(i+1).interp(ns);
            ns.putValue(sym.nameS, val);
        }
        return e.interp(ns);
    }
}
