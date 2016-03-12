package orz.AST;

import orz.DebugHandler;
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
    public Value interp(Scope s, DebugHandler dh) {
        Scope ns = new Scope(s);
        for (int i = 0; i < locals.size(); i+=2) {
            Symbol sym = (Symbol)(locals.get(i));
            Value val = locals.get(i+1).interp(ns, dh);
            ns.putValue(sym.nameS, val);
        }
        dh.update(ns, null);
        dh.block(this.row,this.col);
        return e.interp(ns, dh);
    }
}
