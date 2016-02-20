package orz.AST;

import orz.Scope;
import orz.Type.CharValue;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */

public class Char extends Expr {
    public Character c;

    public Char(int row, int col, Character i) {
        super(row, col);
        type = "Char";
        c = i;
    }

    @Override
    public String toString() {
        return c.toString();
    }

    @Override
    public Value interp(Scope s) {
        return new CharValue(c);
    }
}
