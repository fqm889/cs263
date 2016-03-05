package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public abstract class Expr {
    String type;
    Expr next;
    int row, col;

    public Expr(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public String getType() {
        return type;
    }

    public void setNext(Expr next) {
        this.next = next;
    }

    public Expr getNext() {
        return next;
    }

    public abstract Value interp(Scope s, DebugHandler dh);

}
