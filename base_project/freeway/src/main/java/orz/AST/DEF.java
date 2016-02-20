package orz.AST;

import orz.Scope;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class DEF extends Expr {
    public Symbol sym;
    public Value val;

    public DEF(int row, int col, Symbol s, Value v) {
        super(row, col);
        type = "DEF";
        sym = s;
        val = v;
    }

    @Override
    public String toString() {
        return "DEF " + sym.toString() + " " + val.toString();
    }

    @Override
    public Value interp(Scope s) {
        return null;
    }
}
