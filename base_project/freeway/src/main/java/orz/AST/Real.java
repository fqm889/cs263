package orz.AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Real extends orz.AST.Expr {
    Double d;
    public Real(Double i) {
        type = "Real";
        d = i;
    }

    public Double getD() {
        return d;
    }

    @Override
    public String toString() {
        return d.toString();
    }

}
