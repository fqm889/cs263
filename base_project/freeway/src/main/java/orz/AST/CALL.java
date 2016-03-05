package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Closure;
import orz.Type.Primitive.PrimitiveFunction;
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
    public Value interp(Scope s, DebugHandler dh) {
        Value tmp = fn.interp(s, dh);
        if (tmp instanceof Closure) {
            System.out.println("Inside of CALL.interp "+tmp.toString());
            Closure c = (Closure) tmp;
            Scope ns = new Scope(c.s);
            ArrayList<Symbol> syms = c.fun.argList;
            for (int i = 0; i < e.size(); i++) {
                ns.putValue(syms.get(i).nameS, e.get(i).interp(s, dh));
            }
            Value ret = c.fun.execute.interp(ns, dh);
            dh.update(s, ret);
            dh.block();
            return ret;
        }
        else if (tmp instanceof PrimitiveFunction) {
            PrimitiveFunction f = (PrimitiveFunction) tmp;
            ArrayList<Value> vs = new ArrayList<Value>();
            for (int i = 0; i < e.size(); i++) {
                vs.add(e.get(i).interp(s, dh));
            }
            Value ret = f.apply(vs);
            dh.update(s, ret);
            dh.block();
            return ret;
        }
        else return null;
    }
}

