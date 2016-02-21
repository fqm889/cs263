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
        String filename="/Users/sicongfeng/Documents/Code/GAE/cs263/gae/cs263/base_project/freeway/src/main/java/orz/test.clj";
        Intepreter i = new Intepreter(filename);
        Value v = i.interp(filename);
        System.out.println(v.toString());
    }
}


