package orz;

import orz.Type.Value;

/**
 * Created by sicongfeng on 16/3/4.
 */
public class DebugHandler {
    public int level;
    Scope scope;

    DebugHandler(int l) {
        level = l;

    }

    public void update(Scope s, Value res) {

    }

    public void block() {
        if (scope !=null)
            scope.print();
        else {
            System.out.println("null");
        }
        System.out.println("Next?");
        try {
            System.in.read();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}
