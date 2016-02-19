package orz.AST;

import orz.Expr;
import orz.Symbol;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class DEF extends Expr {
    Symbol sym;
    Value val;
    public DEF(Symbol s, Value v) {
        type = "DEF";
        sym = s;
        val = v;
    }

    public Symbol getSym() {
        return sym;
    }

    public Value getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "DEF " + sym.toString() + " " + val.toString();
    }
}
