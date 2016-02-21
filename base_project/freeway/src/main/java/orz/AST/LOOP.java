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
    public ArrayList<Symbol> syms;
    public ArrayList<RECUR> rec;

    public LOOP(int row, int col, ArrayList<Expr> l, Expr e) {
        super(row, col);
        type = "LOOP";
        locals = l;
        this.e = e;
        int len = l.size()/2;
        //System.out.println(len);
        //System.out.println(locals.size());
        syms = new ArrayList<Symbol>();
        //System.out.println(syms.size());
        for (int i = 0; i < len; i++) {
            syms.add(i, (Symbol)(locals.get(2*i)));
        }
        rec = new ArrayList<RECUR>();
        findRec(e, rec);
        for (RECUR r: rec) {
            r.entrance = this;
        }
    }

    public void findRec(Expr expr, ArrayList<RECUR> recs) {
        if (expr == null)
            return;
        if (expr instanceof RECUR) {
            recs.add((RECUR) expr);
        }
        else if (expr instanceof LET) {
            findRec(((LET) expr).e, recs);
        }
        else if (expr instanceof IF) {
            findRec(((IF) expr).F, recs);
            findRec(((IF) expr).T, recs);
        }
    }

    @Override
    public String toString() {
        return "LOOP"+locals.toString()+e.toString();
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
