import java.util.ArrayList;

/**
 * Created by sicongfeng on 16/2/5.
 */
public class Parser {
}



class Expr {
    String type;
    Expr next;

    public String getType() {
        return type;
    }

    public Expr getNext() {
        return next;
    }
}

class Symbol extends Expr{
    String nameS;
    Symbol(String name) {
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

class Value extends Expr {

}

class Int extends Value {
    Integer v;
    Int(Integer i) {
        type = "Int";
        v = i;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return v.toString();
    }
}

class Real extends Value {
    Double d;
    Real(Double i) {
        type = "Real";
        d = i;
    }

    public Double getD() {
        return d;
    }

    @Override
    public String toString() {
        return d.toString();
    }

}

class Char extends Value {
    Character c;
    Char(Character i) {
        type = "Char";
        c = i;
    }

    public Character getC() {
        return c;
    }

    @Override
    public String toString() {
        return c.toString();
    }
}

class Str extends Value {
    String s;
    Str(String i) {
        type = "String";
        s = i;
    }

    public String getS() {
        return s;
    }

    @Override
    public String toString() {
        return "\"" + s + "\"";
    }
}

class ListArray extends Value {
    ArrayList<Expr> arr;

    ListArray(ArrayList<Expr> a) {
        type = "List";
        arr = a;
    }

    ListArray() {
        type = "List";
        arr = new ArrayList<Expr>();
    }

    void Add(Expr e) {
        arr.add(e);
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}

class Closure extends Value {
    ArrayList<Expr> argList;
    Expr execute;
    Closure(ArrayList<Expr> args, Expr e) {
        type = "FN";
        argList = args;
        execute = e;
    }

    public ArrayList<Expr> getArgList() {
        return argList;
    }

    public Expr getExecute() {
        return execute;
    }
}

class DEF extends Expr {
    Symbol sym;
    Value val;
    DEF(Symbol s, Value v) {
        type = "DEF";
        sym = s;
        val = v;
    }

    public Symbol getSym() {
        return sym;
    }

    public Value getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "DEF " + sym.toString() + " " + val.toString();
    }
}

class DEFN extends Expr {
    Symbol sym;
    Closure cls;
    DEFN(Symbol s, ArrayList<Expr> args, Expr e) {
        type = "DEFN";
        sym = s;
        cls = new Closure(args, e);
    }

    public Symbol getSym() {
        return sym;
    }

    public Closure getCls() {
        return cls;
    }

    @Override
    public String toString() {
        return "FUNCTION " + sym.toString();
    }
}

class LOOP extends Expr {
    ArrayList<Expr> locals;
    Expr e;
    ArrayList<Expr> recur;

    LOOP(ArrayList<Expr> l, Expr e, ArrayList<Expr> rec) {
        type = "LOOP";
        locals = l;
        this.e = e;
        recur = rec;
    }

    public ArrayList<Expr> getLocals() {
        return locals;
    }

    public ArrayList<Expr> getRecur() {
        return recur;
    }

    public Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LOOP";
    }
}

class LET extends Expr {
    ArrayList<Expr> locals;
    Expr e;

    LET(ArrayList<Expr> l, Expr e) {
        type = "LET";
        locals = l;
        this.e = e;
    }

    public ArrayList<Expr> getLocals() {
        return locals;
    }

    public Expr getE() {
        return e;
    }

    @Override
    public String toString() {
        return "LET";
    }
}

class IF extends Expr {
    Expr pred;
    Expr T;
    Expr F;

    IF(Expr p, Expr t, Expr f) {
        type = "IF";
        pred = p;
        T = t;
        F = f;
    }

    public Expr getPred() {
        return pred;
    }

    public Expr getT() {
        return T;
    }

    public Expr getF() {
        return F;
    }
}
