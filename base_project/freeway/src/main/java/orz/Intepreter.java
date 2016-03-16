package orz; /**
 * Created by sicongfeng on 16/1/28.
 */
import orz.AST.Expr;
import orz.Type.Value;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class Intepreter {
    String file;
    Server server;
    public Intepreter(String filename) {
        this.file = filename;
        server = new Server();
    }

    public Value interp(String file) {
        DebugHandler dh = new DebugHandler(0,server); // new DebugHandler(0, new HTTPServer());
        server.debugHandler = dh;
        Thread t = new Thread(server);
        t.start();
        Expr prog = Parser.ReadFile(file);
        Value result;
        Scope s = Scope.initScope();
        result = prog.interp(s, dh);
        while (prog.getNext() != null) {
            prog=prog.getNext();
            //System.out.println(prog.toString());
            result = prog.interp(s, dh);
        }
        return result;
    }

    public static void main(String[] args) {
        String filename="/Users/sicongfeng/Documents/Code/GAE/cs263/gae/cs263/base_project/freeway/src/main/java/orz/test.clj";
        String exeprogram="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    filename));
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuilder.append(content);
                stringBuilder.append("</br>");
            }
            exeprogram = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String u=URLEncoder.encode("http://localhost:8080/datastore?program=" + exeprogram, "UTF-8");
        //String url = "http://cs263proj.appspot.com/datastore";
        String url = "http://localhost:8080/datastore";
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addParameter("program", exeprogram);
        postMethod.addParameter("port", "9000");
        try {
            httpClient.executeMethod(postMethod);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            String ur=URLEncoder.encode( exeprogram, "UTF-8");
            //ur = "http://localhost:8080/datastore?program="+ur;
            ur = "http://cs263proj.appspot.com/datastore?program="+ur;
            URL url = new URL(ur);
            Object obj = url.getContent();
        }
        catch (Exception e){
            //System.out.println(e.toString());
        }*/
        System.out.print(exeprogram);
        Intepreter i = new Intepreter(filename);
        Value v = i.interp(filename);
        System.out.println(v.toString());
    }
}


