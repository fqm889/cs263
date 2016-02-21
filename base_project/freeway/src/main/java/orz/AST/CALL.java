package orz.AST;

import orz.Scope;
import orz.Type.Closure;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class CALL extends Expr {
    public Expr fn;
    public ArrayList<Expr> e;

    public CALL(int row, int col, Symbol f, ArrayList<Expr> e){
        super(row, col);
        type = "CALL";
        fn = f;
        this.e = e;
    }

    @Override
    public String toString() {
        return "CALL"+fn.toString()+e.toString();
    }

    @Override
    public Value interp(Scope s) {
        Value tmp = fn.interp(s);
        Closure c;
        if (tmp instanceof Closure)
            c = (Closure) tmp;
        else return null;

        Scope ns = new Scope(c.s);
        ArrayList<Symbol> syms = c.fun.argList;
        for (int i = 0; i < e.size(); i++) {
            ns.putValue(syms.get(i).nameS, e.get(i).interp(s));
        }
        return c.fun.execute.interp(ns);
    }
}

