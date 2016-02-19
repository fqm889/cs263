package orz.AST;

import orz.Expr;
import orz.Symbol;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class DEFN extends Expr {
    Symbol sym;
    Closure cls;
    public DEFN(Symbol s, ArrayList<Expr> args, Expr e) {
        type = "DEFN";
        sym = s;
        cls = new Closure(args, e);
    }

    public Symbol getSym() {
        return sym;
    }

    public Closure getCls() {
        return cls;
    }

    @Override
    public String toString() {
        return "FUNCTION " + sym.toString();
    }
}
