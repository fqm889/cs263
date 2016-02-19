package orz.AST;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Symbol extends orz.AST.Expr {
    String nameS;
    public Symbol(String name) {
        type = "Symbol";
        nameS = name;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    @Override
    public String toString() {
        return "Symbol(" + nameS + ")";
    }
}
