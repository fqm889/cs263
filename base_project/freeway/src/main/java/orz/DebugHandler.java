package orz;

import orz.AST.Expr;
import orz.Type.Value;

/**
 * Created by sicongfeng on 16/3/4.
 */
public class DebugHandler {
    public int level;
    Scope scope;
    Value result;

    DebugHandler(int l) {
        level = l;

    }

    public void update(Scope s, Value res) {
        // updates scope and return value of each expression when interpreting
        // will be shown on debug interface
        scope = s;
        result = res;
    }

    public void block() {
        // communication with server and block until the next instruction comes
        //
        System.out.println(scope.toString());
        if (result != null)
            System.out.println(result.toString());
        System.out.println("Next?");
        try{
            System.in.read();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
