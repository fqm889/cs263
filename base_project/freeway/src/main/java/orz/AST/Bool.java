package orz.AST;

import orz.Expr;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Bool extends Expr {
    Boolean v;
    public Bool(Boolean b) {
        type = "Bool";
        v = b;
    }

    public Boolean getV() {
        return v;
    }

    @Override
    public String toString() {
        return v.toString();
    }
}