package orz.AST;

import orz.Symbol;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Function extends orz.AST.Expr {
    ArrayList<Symbol> argList;
    orz.AST.Expr execute;

    public Function(ArrayList<Symbol> args, orz.AST.Expr e) {
        type = "FN";
        argList = args;
        execute = e;
    }

    public ArrayList<Symbol> getArgList() {
        return argList;
    }

    public void setArgList(ArrayList<Symbol> argList) {
        this.argList = argList;
    }

    public orz.AST.Expr getExecute() {
        return execute;
    }

    public void setExecute(orz.AST.Expr execute) {
        this.execute = execute;
    }

}
