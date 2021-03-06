package orz;

import orz.Type.BoolValue;
import orz.Type.Primitive.*;
import orz.Type.Value;
import java.util.Iterator;

import java.util.LinkedHashMap;

/**
 * Created by sicongfeng on 16/2/19.
 */
public class Scope {
    public LinkedHashMap<String, LinkedHashMap<String, Value>> env;
    // {name : {value : v, type : t}}

    public Scope parent;

    public Scope() {
        env = new LinkedHashMap<String,LinkedHashMap<String, Value>>();
        parent = null;
    }

    public Scope(Scope pa) {
        env = new LinkedHashMap<String,LinkedHashMap<String, Value>>();
        parent = pa;
    }

    public void put(String name, String vt, Value obj) {
        LinkedHashMap<String, Value> tmp = env.get(name);
        if (tmp == null) tmp = new LinkedHashMap<String, Value>();
        tmp.put(vt, obj);
        env.put(name, tmp);
    }

    public void putValue(String name, Value v) {
        put(name, "value", v);
    }

    public void putType(String name, Value v) {
        put(name, "type", v);
    }

    public Value lookupLocal(String name, String vt) {
        LinkedHashMap<String, Value> tmp = env.get(name);
        if (tmp == null) {
            return null;
        }
        else {
            return tmp.get(vt);
        }
    }

    public Value lookupLocalValue(String name) {
        Value v = lookupLocal(name, "value");
        return v;
    }

    public Value lookupLocalType(String name) {
        Value v = lookupLocal(name, "type");
        return v;
    }

    public Value lookupValue(String name) {
        Value v = lookupLocalValue(name);
        if (v==null) {
            if (parent==null) return null;
            else return parent.lookupValue(name);
        }
        else return v;
    }

    public Value lookupType(String name) {
        Value v = lookupLocalType(name);
        if (v==null) {
            if (parent==null) return null;
            else return parent.lookupType(name);
        }
        else return v;
    }

    public Scope findScope(String name) {
        Object v = env.get(name);
        if (v!=null) {
            return this;
        }
        else if (parent!=null) {
            return parent.findScope(name);
        }
        else {
            return null;
        }
    }

    public static Scope initScope() {
        Scope init = new Scope();
        init.putValue("+", new ADD());
        init.putValue("-", new SUB());
        init.putValue("*", new MUL());
        init.putValue("/", new DIV());

        init.putValue("<", new LT());
        init.putValue("<=", new LTE());
        init.putValue(">", new GT());
        init.putValue(">=", new GTE());
        init.putValue("==", new EQ());
        init.putValue("and", new AND());
        init.putValue("or", new OR());
        init.putValue("xor", new XOR());
        init.putValue("not", new NOT());

        init.putValue("true", new BoolValue(true));
        init.putValue("false", new BoolValue(false));

        init.putValue("Int", Constants.INT_TYPE);
        init.putValue("Bool", Constants.BOOL_TYPE);
        init.putValue("String", Constants.Str_TYPE);
        init.putValue("Char", Constants.CHAR_TYPE);
        init.putValue("Real", Constants.REAL_TYPE);
        init.putValue("Vector", Constants.VECTOR_TYPE);

        return init;
    }

    @Override
    public String toString() {
        String ans="{\"scope\":[";
        for (Iterator it =  env.keySet().iterator();it.hasNext();)
        {
            String key = (String)it.next();
            ans+="{\"sym\":\""+key+"\",";
            LinkedHashMap<String,Value> tmp = env.get(key);
            for (Iterator it1 = tmp .keySet().iterator();it1.hasNext();){
                String key1=(String)it1.next();
                Value v = tmp.get(key1);
                ans+="\""+key1+"\":\""+v.toString()+"\",";
            }
            ans=ans.substring(0,ans.length()-1);
            ans+="},";
            //System.out.println( key+"="+ map.get(key));
        }
        ans=ans.substring(0,ans.length()-1);
        ans+="]";
        return ans;
    }
}
