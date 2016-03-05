package orz;

import orz.AST.*;
import orz.Type.*;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Binding {

    public static void define(Symbol s, Value v, Scope env) {
        String name = s.nameS;
        Value vtmp = env.lookupLocalValue(name);
        if (vtmp==null) {
            env.putValue(name, v);
        }
    }

    public static void assign(Symbol s, Value v, Scope env) {
        String name = s.nameS;
        Scope sp = env.findScope(name);
        if (sp!=null) sp.putValue(name, v);
    }
}
