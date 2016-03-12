# Design of Rest API

### GAE

----------------------------

By running the local intepreter, one can start a local server, which registers on GAE via start API.

Jump to GAE page after start.


----------------------------

/start

POST

session=session_id

Start a new session of co-debugging, which builds a chatroom for debuggers.
 
The host executes the data locally, and shares results with others in this chatroom.

----------------------------

/upload

POST

program=prog_text

Upload code and start the local intepreter.
 
----------------------------

/next

GET

Send message to local server to run one step more of the code.

----------------------------

/update

POST

JSON

Returns the current state to GAE in json.

----------------------------

/chat

POST

JSON

Personal chats.

----------------------------


### Local Server

----------------------------

/next

GET

Run one step of the code.

----------------------------


