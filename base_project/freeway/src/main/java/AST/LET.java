package AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LET extends Expr {
    ArrayList<Expr> locals;
    Expr e;

    public LET(ArrayList<Expr> l, Expr e) {
        type = "LET";
        locals = l;
        this.e = e;
    }

    public ArrayList<Expr> getLocals() {
        return locals;
    }

    public Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LET";
    }
}
