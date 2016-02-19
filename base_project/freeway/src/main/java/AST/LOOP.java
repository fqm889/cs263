package AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LOOP extends Expr {
    ArrayList<Expr> locals;
    Expr e;
    ArrayList<Expr> recur;

    public LOOP(ArrayList<Expr> l, Expr e, ArrayList<Expr> rec) {
        type = "LOOP";
        locals = l;
        this.e = e;
        recur = rec;
    }

    public ArrayList<Expr> getLocals() {
        return locals;
    }

    public ArrayList<Expr> getRecur() {
        return recur;
    }

    public Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LOOP";
    }
}
