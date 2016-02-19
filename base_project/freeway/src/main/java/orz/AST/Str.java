package orz.AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Str extends orz.AST.Expr {
    String s;
    public Str(String i) {
        type = "String";
        s = i;
    }

    public String getS() {
        return s;
    }

    @Override
    public String toString() {
        return "\"" + s + "\"";
    }
}