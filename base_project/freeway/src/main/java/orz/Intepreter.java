package orz; /**
 * Created by sicongfeng on 16/1/28.
 */
import orz.AST.Expr;
import orz.Type.Value;

public class Intepreter {
    String file;
    public Intepreter(String filename) {
        this.file = filename;
    }

    public Value interp(String file) {
        Expr prog = Parser.ReadFile(file);
        return prog.interp(Scope.initScope());
    }

    public static void main(String[] args) {
        Intepreter i = new Intepreter(args[0]);
        i.interp(args[0]);
    }
}


