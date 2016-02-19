import AST.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.util.LinkedList;
/**
 * Created by sicongfeng on 16/2/5.
 */
public class Parser {

    static List GetWordList(InputStream input){
        List list = new LinkedList();
        int tmp;
        String cur="";
        try {
            while ((tmp = input.read())!=-1){
                char c = (char) tmp;
                if (cur.charAt(0)=='"'&&c!='"'){
                    cur+=c;
                }
                else if (c==' '||c==','){
                    if (cur!="")
                        list.add(cur);
                    cur="";
                }
                else if (c=='['||c==']'||c=='('||c==')'){
                    if (cur!="")
                        list.add(cur);
                    cur="";
                    cur+=c;
                    list.add(cur);
                    cur="";
                }
                else {
                    cur+=c;
                }
            }
        }
        catch (IOException e){
            System.out.println("Error!");
        }
        if (cur!="")
            list.add(cur);
        return list;
    } 

    static Expr getExpr(Iterator ite){
        String str = (String) ite.next();
        if (str=="]"||str==")")
            return null;
        if (str.charAt(0) == '"'){
            return new Str(str);
        }
        else if (str.charAt(0) == '\''){
            return new Char(str.charAt(1));
        }
        else if (str.charAt(0) == '+'||str.charAt(0)=='-'||(str.charAt(0)>='0'&&str.charAt(0)<='9')||str.charAt(0)=='.'){
            int flag=0;
            for (int i=0;i<str.length();i++){
                if (str.charAt(i)=='.')
                    flag=1;
            }
            if (flag==1){
                return new Real(Double.parseDouble(str));
            }
            else {
                return new Int(Integer.parseInt(str));
            }
        }
        else if (str=="["){
            ListArray la = new ListArray();
            Expr tmp = getExpr(ite);
            while (tmp != null){
                la.Add(tmp);
                tmp = getExpr(ite);
            }
            return la;
        }
        else if (str == "("){
            str = (String) ite.next();
            if (str == "def"){
                Symbol sym = new Symbol((String) ite.next());
                Expr x = getExpr(ite);
                str = (String) ite.next();
                return new DEF(sym,(Value)x);
            }
            else if (str == "defn"){
                Symbol sym = new Symbol((String) ite.next());
                ArrayList<Expr> args = new ArrayList<Expr>();
                str = (String) ite.next();
                if (str == "["){
                    str = (String) ite.next();
                    while (str != "]"){
                        args.add(new Symbol(str));
                    }
                }
                Expr e = getExpr(ite);
                str = (String) ite.next();
                return new DEFN(sym,args,e);
            }
            else if (str == "if"){
                Expr e = getExpr(ite);
                Expr t = getExpr(ite);
                Expr f = getExpr(ite);
                if (f!=null)
                    str=(String) ite.next();
                return new IF(e,t,f);
            }
            else if (str == "loop"){
                str = (String) ite.next();
                if (str != "["){
                    System.out.println("Error!");
                }
                ArrayList<Expr> l = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    l.add(tmp);
                    tmp = getExpr(ite);
                }
                Expr e = getExpr(ite);
                str = (String) ite.next();
                if (str != "("){
                    System.out.println("Error!");
                }
                str = (String) ite.next();
                if (str != "recur"){
                    System.out.println("Error!");
                }
                ArrayList<Expr> rec = new ArrayList<Expr>();
                tmp = getExpr(ite);
                while (tmp != null){
                    rec.add(tmp);
                    tmp = getExpr(ite);
                }
                str = (String) ite.next();
                return new LOOP(l,e,rec);
            }
            else if (str == "let"){
                str = (String) ite.next();
                if (str != "["){
                    System.out.println("Error!");
                }
                ArrayList<Expr> l = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    l.add(tmp);
                    tmp = getExpr(ite);
                }
                Expr e = getExpr(ite);
                str = (String) ite.next();
                return new LET(l,e);
            }
            else if (str == "fn"){
                str = (String) ite.next();
                if (str != "["){
                    System.out.println("Error!");
                }
                ArrayList<Symbol> args = new ArrayList<Symbol>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    args.add((Symbol) tmp);
                    tmp = getExpr(ite);
                }
                Expr e = getExpr(ite);
                str = (String) ite.next();
                return new Closure(args,e);
            }
            else {
                Symbol fn = new Symbol(str);
                ArrayList<Expr> e = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    e.add(tmp);
                    tmp = getExpr(ite);
                }
                return new CALL(fn,e);
            }
        }
        else {
            return new Symbol(str);
        }
    }

    public static Expr ReadFile(String filename){
        File file = new File(filename);
        InputStream input = null;
        try{
            input = new FileInputStream(file);
        }
        catch (IOException e){
            System.out.println("Error!");
        }
        List wordlist = GetWordList(input);
        Iterator ite = wordlist.iterator();
        Expr program = null;
        Expr cur = null;
        while (ite.hasNext()){
            Expr tmp = getExpr(ite);
            if (program == null){
                program = tmp;
                cur = tmp;
            }
            else {
                cur.setNext(tmp);
                cur=tmp;
            }
        }
        return program;
    }
}


