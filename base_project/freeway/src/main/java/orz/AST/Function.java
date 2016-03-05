package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Closure;
import orz.Type.Value;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Function extends Expr {
    public ArrayList<Symbol> argList;
    public Expr execute;

    public Function(int row, int col, ArrayList<Symbol> args, Expr e) {
        super(row, col);
        type = "FN";
        argList = args;
        execute = e;
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        return new Closure(this, s);
    }

    @Override
    public String toString() {
        return type + "[" + argList.toString() + "]" + "(" + execute.toString() + ")";
    }
}
