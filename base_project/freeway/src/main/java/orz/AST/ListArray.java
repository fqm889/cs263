package orz.AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class ListArray extends orz.AST.Expr {
    ArrayList<orz.AST.Expr> arr;

    ListArray(ArrayList<orz.AST.Expr> a) {
        type = "List";
        arr = a;
    }

    public ListArray() {
        type = "List";
        arr = new ArrayList<orz.AST.Expr>();
    }

    public void Add(orz.AST.Expr e) {
        arr.add(e);
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}
