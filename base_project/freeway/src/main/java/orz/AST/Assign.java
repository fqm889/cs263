package orz.AST;

import orz.Binding;
import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Assign extends Expr {
    public Symbol s;
    public Expr e;

    public Assign(int row, int col, Symbol s, Expr e) {
        super(row, col);
        this.s = s;
        this.e = e;
    }

    public Value interp(Scope s, DebugHandler dh) {
        Value v = e.interp(s, dh);
        Binding.assign(this.s, v, s);
        dh.update(s, v);
        dh.block();
        return v;
    }
}