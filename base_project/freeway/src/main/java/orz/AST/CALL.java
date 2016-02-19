package orz.AST;

import orz.Expr;
import orz.Symbol;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class CALL extends Expr {
    Symbol fn;
    ArrayList<Expr> e;

    public CALL(Symbol f, ArrayList<Expr> e){
        type = "CALL";
        fn = f;
        this.e = e;
    }

    public Symbol getFn() {
        return fn;
    }

    public ArrayList<Expr> getE() {
        return e;
    }
}

