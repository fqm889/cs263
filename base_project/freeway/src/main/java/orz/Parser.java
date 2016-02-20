package orz;

import orz.AST.*;
import orz.Type.Value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.util.LinkedList;
import java.lang.String;
/**
 * Created by sicongfeng on 16/2/5.
 */
public class Parser {

    static List<String> GetWordList(InputStream input){
        List<String> list = new LinkedList();
        int tmp;
        String cur="";
        try {
            while ((tmp = input.read())!=-1){
                char c = (char) tmp;
                if (cur.charAt(0)=='"'&&c!='"'){
                    cur+=c;
                }
                else if (c==' '||c==','){
                    if (!cur.equals(""))
                        list.add(cur);
                    cur="";
                }
                else if (c=='['||c==']'||c=='('||c==')'){
                    if (!cur.equals(""))
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
        if (!cur.equals(""))
            list.add(cur);
        return list;
    } 

    static Expr getExpr(Iterator ite){
        String str = (String) ite.next();
        if (str.equals("]")||str.equals(")"))
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
        else if (str.equals("[")){
            ListArray la = new ListArray();
            Expr tmp = getExpr(ite);
            while (tmp != null){
                la.Add(tmp);
                tmp = getExpr(ite);
            }
            return la;
        }
        else if (str.equals("(")){
            str = (String) ite.next();
            if (str.equals("def")){
                Symbol sym = new Symbol((String) ite.next());
                Expr x = getExpr(ite);
                str = (String) ite.next();
                return new DEF(sym,(Value)x);
            }
            else if (str.equals("defn")){
                Symbol sym = new Symbol((String) ite.next());
                ArrayList<Symbol> args = new ArrayList<Symbol>();
                str = (String) ite.next();
                if (str.equals("[")){
                    str = (String) ite.next();
                    while (!str.equals("]")){
                        args.add(new Symbol(str));
                    }
                }
                Expr e = getExpr(ite);
                str = (String) ite.next();
                return new DEFN(sym,args,e);
            }
            else if (str.equals("if")){
                Expr e = getExpr(ite);
                Expr t = getExpr(ite);
                Expr f = getExpr(ite);
                if (f!=null)
                    str=(String) ite.next();
                return new IF(e,t,f);
            }
            else if (str.equals("loop")){
                str = (String) ite.next();
                if (str.equals("[")){
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
                if (!str.equals("(")){
                    System.out.println("Error!");
                }
                str = (String) ite.next();
                if (!str.equals("recur")){
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
            else if (str.equals("let")){
                str = (String) ite.next();
                if (str.equals("[")){
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
            else if (str.equals("fn")){
                str = (String) ite.next();
                if (!str.equals("[")){
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
                return new Function(args,e);
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


