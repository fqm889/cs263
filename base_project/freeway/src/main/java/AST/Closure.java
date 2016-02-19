package AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Closure extends Value {
    ArrayList<Symbol> argList;
    Expr execute;
    public Closure(ArrayList<Symbol> args, Expr e) {
        type = "FN";
        argList = args;
        execute = e;
    }

    public ArrayList<Symbol> getArgList() {
        return argList;
    }

    public Expr getExecute() {
        return execute;
    }
}
