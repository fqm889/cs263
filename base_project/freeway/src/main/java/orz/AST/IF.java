package orz.AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class IF extends orz.AST.Expr {
    orz.AST.Expr pred;
    orz.AST.Expr T;
    orz.AST.Expr F;

    public IF(orz.AST.Expr p, orz.AST.Expr t, orz.AST.Expr f) {
        type = "IF";
        pred = p;
        T = t;
        F = f;
    }

    public orz.AST.Expr getPred() {
        return pred;
    }

    public orz.AST.Expr getT() {
        return T;
    }

    public orz.AST.Expr getF() {
        return F;
    }
}
