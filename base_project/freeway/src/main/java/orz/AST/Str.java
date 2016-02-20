package orz.AST;

import orz.Scope;
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
    public Value interp(Scope s) {
        return null;
    }
}
