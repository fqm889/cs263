package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.StrValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Str extends Expr {
    public String s;
    public Str(int row, int col, String i) {
        super(row, col);
        type = "String";
        s = i;
    }

    @Override
    public String toString() {
        return "\"" + s + "\"";
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        Value ret = new StrValue(this.s);
        dh.update(s, ret);
        dh.block(this.row,this.col);
        return ret;
    }
}
