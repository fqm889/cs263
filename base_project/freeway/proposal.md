# proposal

## Topic

Our team is building an online IDE for the purpose of collaborative debugging.

### Interpreter

The owner of the IDE owns a local intepreter which inteprets the lisp-1 language. The local intepreter sends
the status of local variable and continuation stack to Google App Engine, which then dispatch it to different 
client, debugging the piece of code over the Internet.

### Lisp in Small Piece

The Lisp we are going to implement in this proposal is a simplified Lisp-1, which shares some grammer of Clojure.

Here are the grammer we are going to implement.


----------------------------------------
|   Text   |  Token/Ast    |   Value   |
|----------|---------------|-----------|
|   a      |  Symbol("a")  |   "a"     |
|   1      |  Int(1)       |    1      |
|   1.5    |  Double(1.5)  |    1.5    |
|   'c'    |  Char('c')    |    'c'    |
|  "str"   |  String("str")|    "str"  |
|  true    |  Bool(true)   |   true    |
|  fn      |  Closure      |   "fn"    |     
----------------------------------------


``` clojure

(def a 1)

(defn func [a] (+ a 1))

(fn [a] (+ a 2))

(if (= a 1) (+ a 2) a)

(loop [a 1] (println a) (recur a (+ a 1))

 ```
 
 All Lists are implemented with ArrayList. 
 
 The intepreter executes the statement, sending the states and results to Google App Engine in JSON.
 
 The intepreter takes in requests from clients through the GAE and executes the code statement by statement. After executing one statement, 
 current status is sent to GAE to be sent to all clients. The current status will be shown on webpage of clients as debug information, thus enabling everyone 
 to watch and debug at the same time. 


 #######################################################

Client:

The webpage has the following functions:

1, Get the source code from the database.
2, Remotely view and track the states of the interpreter as the debug goes.
3, Send instructions to the remote interpreter.

We will use JSON to communicate with the servlet.

When a user first open the webpage, it should send a request to the servlet for the source code and the current state. If the interpreter is currently running, the servlet should return the source code from the database and the state.

At the page, all the clients can send next step or stop to the interpreter through the servlet. The result will be send to all clients.

#######################################################

Servlet:

The servlet has the following functions:

1, Keep track of all the clients that is currently track the debug process.
2, Take the result from the interpreter and send to all the client. 
3, Store the source code to the database.
4, Put the latest debug state in memcache.

The servlet take two kinds of request from the interpreter:
1, StoreCode. Which is to store the code in the database indicates the current code the interpreter is running.
2, PushState. Which is to push the current state of the debug process to all the client.

The servlet take two kinds of request from the client:
1, StartWatching. Which is to start watching the current debug process. Servlet should return the source code and current state.
2, NextStep. Which is a instruction to the interpreter.
3, StopDebug. Also a instruction to the interpreter.

#######################################################