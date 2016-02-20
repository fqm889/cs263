package orz.AST;

import orz.Scope;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class CALL extends Expr {
    public Symbol fn;
    public ArrayList<Expr> e;

    public CALL(int row, int col, Symbol f, ArrayList<Expr> e){
        super(row, col);
        type = "CALL";
        fn = f;
        this.e = e;
    }

    @Override
    public String toString() {
        return "CALL"+fn.toString();
    }

    @Override
    public Value interp(Scope s) {
        return null;
    }
}

