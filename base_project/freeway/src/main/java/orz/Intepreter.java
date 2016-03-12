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
        DebugHandler dh = new DebugHandler(0); // new DebugHandler(0, new HTTPServer());
        Expr prog = Parser.ReadFile(file);
        Value result;
        Scope s = Scope.initScope();
        result = prog.interp(s, dh);
        while (prog.getNext() != null) {
            prog=prog.getNext();
            result = prog.interp(s, dh);
        }
        return result;
    }

    public static void main(String[] args) {
        String filename="/Users/chenjiyu/Desktop/cs263/test/cs263/base_project/freeway/src/main/java/orz/test.clj";
        Intepreter i = new Intepreter(filename);
        Value v = i.interp(filename);
        System.out.println(v.toString());
    }
}


