package AST;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class ListArray extends Value {
    ArrayList<Expr> arr;

    ListArray(ArrayList<Expr> a) {
        type = "List";
        arr = a;
    }

    public ListArray() {
        type = "List";
        arr = new ArrayList<Expr>();
    }

    public void Add(Expr e) {
        arr.add(e);
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}
