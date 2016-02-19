package AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class IF extends Expr {
    Expr pred;
    Expr T;
    Expr F;

    public IF(Expr p, Expr t, Expr f) {
        type = "IF";
        pred = p;
        T = t;
        F = f;
    }

    public Expr getPred() {
        return pred;
    }

    public Expr getT() {
        return T;
    }

    public Expr getF() {
        return F;
    }
}
