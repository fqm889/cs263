package orz; /**
 * Created by sicongfeng on 16/1/28.
 */
import orz.AST.Expr;
import orz.Type.Value;

public class Intepreter {
    public Intepreter(String filename) {
        Expr prog = Parser.ReadFile(filename);

    }

    Value valueOf(Expr e) {
        return null;
    }
}


