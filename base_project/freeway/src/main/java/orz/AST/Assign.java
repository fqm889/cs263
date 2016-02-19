package orz.AST;

import orz.Expr;
import orz.Symbol;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Assign extends Expr {
    Symbol s;
    Expr e;

    public Assign(Symbol s, Expr e) {
        this.s = s;
        this.e = e;
    }

    public Symbol getS() {
        return s;
    }

    public Expr getE() {
        return e;
    }

    public void setS(Symbol s) {
        this.s = s;
    }

    public void setE(Expr e) {
        this.e = e;
    }
}