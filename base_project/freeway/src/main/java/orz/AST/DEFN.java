package orz.AST;

import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */

public class DEFN extends Expr {
    public Symbol sym;
    public Function cls;

    public DEFN(int row, int col, Symbol s, ArrayList<Symbol> args, Expr e) {
        super(row, col);
        type = "DEFN";
        sym = s;
        cls = new Function(row, col, args, e);
    }

    @Override
    public String toString() {
        return "FUNCTION " + sym.toString();
    }

    @Override
    public Value interp(Scope s) {
        return null;
    }
}
