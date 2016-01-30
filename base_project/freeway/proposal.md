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