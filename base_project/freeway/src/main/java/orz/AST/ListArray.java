package orz.AST;

import orz.Scope;
import orz.Type.Value;
import orz.Type.VectorValue;

import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class ListArray extends Expr {
    public ArrayList<Expr> arr;

    ListArray(int row, int col, ArrayList<Expr> a) {
        super(row, col);
        type = "List";
        arr = a;
    }

    public ListArray(int row, int col) {
        super(row, col);
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

    public static ArrayList<Value> interpList(ArrayList<Expr> a, Scope s) {
        ArrayList<Value> res = new ArrayList<Value>(a.size());
        for (int i = 0; i < a.size(); i++) {
            res.set(i, a.get(i).interp(s));
        }
        return res;
    }

    @Override
    public Value interp(Scope s) {
        return new VectorValue(interpList(arr, s));
    }
}
