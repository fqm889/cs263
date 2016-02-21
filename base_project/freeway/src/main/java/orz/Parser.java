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

    static List<Token> GetWordList(InputStream input){
        List<Token> list = new LinkedList();
        int tmp,line=0,col=0,curline=-1,curcol=0;
        String cur="";
        try {
            while ((tmp = input.read())!=-1){
                char c = (char) tmp;
                if (!cur.equals("")&&cur.charAt(0)=='"'&&c!='"'){
                    cur+=c;
                }
                else if (c==' '||c==','||c=='\n'){
                    if (!cur.equals(""))
                        list.add(new Token(cur,curline,curcol));
                    cur="";
                    curline=-1;
                }
                else if (c!='_'&&!(c>='0'&&c<='9')&&!(c>='a'&&c<='z')&&!(c>='A'&&c<='Z')){
                    if (!cur.equals(""))
                        list.add(new Token(cur,curline,curcol));
                    cur="";
                    cur+=c;
                    list.add(new Token(cur,line,col));
                    cur="";
                    curline=-1;
                }
                else {
                    if (curline==-1){
                        curline=line;
                        curcol=col;
                    }
                    cur+=c;
                }
                if (c=='\n'){
                    line++;
                    col=0;
                }
                else {
                    col++;
                }
            }
        }
        catch (IOException e){
            System.out.println("Error!");
        }
        if (!cur.equals(""))
            list.add(new Token(cur,curline,curcol));
        return list;
    } 

    static Expr getExpr(Iterator ite){
        Token tok = (Token) ite.next();
        String str = tok.sym;
        if (str == null)
            return null;
        int row = tok.row;
        int col = tok.col;
        if (str.equals("]")||str.equals(")"))
            return null;
        if (str.charAt(0) == '"'){
            return new Str(row,col,str);
        }
        else if (str.charAt(0) == '\''){
            return new Char(row,col,str.charAt(1));
        }
        else if (str.charAt(0) == '+'||str.charAt(0)=='-'||(str.charAt(0)>='0'&&str.charAt(0)<='9')||str.charAt(0)=='.'){
            int flag=0;
            for (int i=0;i<str.length();i++){
                if (str.charAt(i)=='.')
                    flag=1;
            }
            if (flag==1){
                return new Real(row,col,Double.parseDouble(str));
            }
            else {
                return new Int(row,col,Integer.parseInt(str));
            }
        }
        else if (str.equals("[")){
            ListArray la = new ListArray(row,col);
            Expr tmp = getExpr(ite);
            while (tmp != null){
                la.Add(tmp);
                tmp = getExpr(ite);
            }
            return la;
        }
        else if (str.equals("(")){
            tok = (Token) ite.next();
            str = tok.sym;
            if (str.equals("def")){
                Token tmptok = (Token) ite.next();
                Symbol sym = new Symbol(tmptok.row,tmptok.col,tmptok.sym);
                Expr x = getExpr(ite);
                tok = (Token) ite.next();
                return new DEF(row,col,sym,x);
            }
            else if (str.equals("defn")){
                tok = (Token) ite.next();
                Symbol sym = new Symbol(tok.row,tok.col,tok.sym);
                ArrayList<Symbol> args = new ArrayList<Symbol>();
                tok = (Token) ite.next();
                str = tok.sym;
                if (str.equals("[")){
                    tok = (Token) ite.next();
                    str = tok.sym;
                    while (!str.equals("]")){
                        args.add(new Symbol(tok.row,tok.col,str));
                    }
                }
                Expr e = getExpr(ite);
                tok = (Token) ite.next();
                return new DEFN(row,col,sym,args,e);
            }
            else if (str.equals("if")){
                Expr e = getExpr(ite);
                Expr t = getExpr(ite);
                Expr f = getExpr(ite);
                if (f!=null)
                    tok=(Token) ite.next();
                return new IF(row,col,e,t,f);
            }
            else if (str.equals("loop")){
                tok = (Token) ite.next();
                str = tok.sym;
                if (!str.equals("[")){
                    System.out.println("Error!");
                }
                ArrayList<Expr> l = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    l.add(tmp);
                    //System.out.println(tmp.toString());
                    tmp = getExpr(ite);
                }
                //System.out.println(l.size());
                Expr e = getExpr(ite);
                tok = (Token) ite.next();
                /*str = (String) ite.next();
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
                str = (String) ite.next();*/
                return new LOOP(row,col,l,e);
            }
            else if (str.equals("let")){
                tok = (Token) ite.next();
                str = tok.sym;
                if (!str.equals("[")){
                    System.out.println("Error!");
                }
                ArrayList<Expr> l = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    l.add(tmp);
                    tmp = getExpr(ite);
                }
                Expr e = getExpr(ite);
                tok = (Token) ite.next();
                return new LET(row,col,l,e);
            }
            else if (str.equals("fn")){
                tok = (Token) ite.next();
                str = tok.sym;
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
                tok = (Token) ite.next();
                return new Function(row,col,args,e);
            }
            else if (str.equals("recur")){
                ArrayList<Expr> l = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    l.add(tmp);
                    tmp =getExpr(ite);
                }
                return new RECUR(row,col,l);
            }
            else {
                Symbol fn = new Symbol(tok.row,tok.col,str);
                ArrayList<Expr> e = new ArrayList<Expr>();
                Expr tmp = getExpr(ite);
                while (tmp != null){
                    e.add(tmp);
                    tmp = getExpr(ite);
                }
                return new CALL(row,col,fn,e);
            }
        }
        else {
            return new Symbol(row,col,str);
        }
    }

    public static Expr ReadFile(String filename){
        File file = new File(filename);
        if (file == null){
            System.out.println("Fail to open file!");
        }
        InputStream input = null;
        try{
            input = new FileInputStream(file);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
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

    public  static  void main(String[] args){
        String filename="/Users/chenjiyu/Desktop/cs263/gae/cs263project/base_project/freeway/src/main/java/orz/test.clj";
        System.out.println(Parser.ReadFile(filename).toString());
    }
}

class Token{
    public String sym;
    public int row;
    public int col;

    public Token(String s, int r, int c){
        this.sym =s;
        this.row=r;
        this.col=c;
    }
}
