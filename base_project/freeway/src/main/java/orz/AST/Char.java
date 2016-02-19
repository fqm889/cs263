package orz.AST;

import orz.Expr;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Char extends Expr {
    Character c;
    public Char(Character i) {
        type = "Char";
        c = i;
    }

    public Character getC() {
        return c;
    }

    @Override
    public String toString() {
        return c.toString();
    }
}
