package orz.AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LOOP extends orz.AST.Expr {
    ArrayList<orz.AST.Expr> locals;
    orz.AST.Expr e;
    ArrayList<orz.AST.Expr> recur;

    public LOOP(ArrayList<orz.AST.Expr> l, orz.AST.Expr e, ArrayList<orz.AST.Expr> rec) {
        type = "LOOP";
        locals = l;
        this.e = e;
        recur = rec;
    }

    public ArrayList<orz.AST.Expr> getLocals() {
        return locals;
    }

    public ArrayList<orz.AST.Expr> getRecur() {
        return recur;
    }

    public orz.AST.Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LOOP";
    }
}
