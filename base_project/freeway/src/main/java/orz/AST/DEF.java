package orz.AST;

import orz.Binding;
import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class DEF extends Expr {
    public Symbol sym;
    public Expr val;

    public DEF(int row, int col, Symbol s, Expr v) {
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
    public Value interp(Scope s, DebugHandler dh) {
        Value v = val.interp(s, dh);
        Binding.define(sym, v, s);
        return v;
    }
}
