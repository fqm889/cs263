package orz.AST;

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
    public Value interp(Scope s) {
        Scope ns = new Scope(s);
        ArrayList<Symbol> syms = entrance.syms;
        for (int i = 0; i < e.size(); i++) {
            ns.putValue(syms.get(i).nameS, e.get(i).interp(s));
        }
        return entrance.e.interp(ns);
    }
}
