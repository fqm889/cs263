package AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Expr {
    String type;
    Expr next;

    public String getType() {
        return type;
    }

    public void setNext(Expr next) {
        this.next = next;
    }

    public Expr getNext() {
        return next;
    }
}
