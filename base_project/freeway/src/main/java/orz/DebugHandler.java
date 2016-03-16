package orz;

import orz.AST.Expr;
import orz.Type.Value;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by sicongfeng on 16/3/4.
 */
public class DebugHandler {
    public int level;
    Scope scope;
    Value result;
    Server server;
    boolean next = false;

    DebugHandler(int l, Server server) {
        level = l;
        this.server = server;
    }

    public void update(Scope s, Value res) {
        // updates scope and return value of each expression when interpreting
        // will be shown on debug interface
        scope = s;
        result = res;
    }

    public void block(int line,int col) {
        // communication with server and block until the next instruction comes
        //
        try{

            String landc=String.format("line=%d&col=%d&heap=",line,col);
            String scopstr=scope.toString();
            System.out.printf("Line: %d Col: %d\n", line,col);
            System.out.println(scopstr);
            scopstr+=",\"line\":\""+String.valueOf(line)+"\",\"col\":\""+String.valueOf(col)+"\",\"result\":\""+result.toString()+"\"}";
            landc+=URLEncoder.encode(scopstr,"UTF-8");

            if (result != null) {
                String res=result.toString();
                System.out.println(res);
                res=URLEncoder.encode(res,"UTF-8");
                landc+="&result=";
                landc+=res;
            }

            //String ur = "http://localhost:8080/enqueue?"+landc;
            String ur = "http://cs263proj.appspot.com/enqueue?"+landc;
            URL url = new URL(ur);
            Object obj = url.getContent();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println("Next?");
        while (!next) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        next = false;
    }
}
