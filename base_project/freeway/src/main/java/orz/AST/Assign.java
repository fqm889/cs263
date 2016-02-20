package orz.AST;

import orz.Binding;
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

    public Value interp(Scope s) {
        Value v = e.interp(s);
        Binding.assign(this.s, v, s);
        return v;
    }
}