package orz.AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class LET extends orz.AST.Expr {
    ArrayList<orz.AST.Expr> locals;
    orz.AST.Expr e;

    public LET(ArrayList<orz.AST.Expr> l, orz.AST.Expr e) {
        type = "LET";
        locals = l;
        this.e = e;
    }

    public ArrayList<orz.AST.Expr> getLocals() {
        return locals;
    }

    public orz.AST.Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LET";
    }
}
