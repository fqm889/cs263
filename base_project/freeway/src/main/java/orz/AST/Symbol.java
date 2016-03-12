package orz.AST;

import orz.DebugHandler;
import orz.Scope;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Symbol extends Expr {
    public String nameS;

    public Symbol(int row, int col, String name) {
        super(row, col);
        type = "Symbol";
        nameS = name;
    }

    @Override
    public String toString() {
        return "Symbol(" + nameS + ")";
    }

    @Override
    public Value interp(Scope s, DebugHandler dh) {
        Value ret = s.lookupValue(nameS);
        dh.update(s, ret);
        dh.block(this.row,this.col);
        return ret;
    }

    public boolean equals(Symbol s) {
        return nameS.equals(s.nameS);
    }
}
