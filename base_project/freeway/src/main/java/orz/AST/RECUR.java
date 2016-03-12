package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/20.
 */
public class RECUR extends Expr {
    public ArrayList<Expr> e;
    public LOOP entrance;

    public RECUR(int row, int col, ArrayList<Expr> es) {
        super(row, col);
        e = es;
    }

    public RECUR(int row, int col, ArrayList<Expr> es, LOOP entr) {
        super(row, col);
        e = es;
        entrance = entr;
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        Scope ns = new Scope(s);
        ArrayList<Symbol> syms = entrance.syms;
        for (int i = 0; i < e.size(); i++) {
            ns.putValue(syms.get(i).nameS, e.get(i).interp(s, dh));
        }
        dh.update(ns, null);
        dh.block(this.row,this.col);
        return entrance.e.interp(ns, dh);
    }

    @Override
    public String toString() {
        return "RECUR"+e.toString();
    }

}
