package orz.AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Int extends orz.AST.Expr {
    Integer v;
    public Int(Integer i) {
        type = "Int";
        v = i;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return v.toString();
    }
}