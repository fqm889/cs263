package orz.Type;

import orz.AST.Function;
import orz.Scope;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Closure extends Value {
    public Function fun;
    public Scope s;

    public Closure(Function fun, Scope s) {
        this.fun = fun;
        this.s = s;
    }

    public String toString() {
        return fun.toString();
    }
}
