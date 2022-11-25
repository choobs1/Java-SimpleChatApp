**Multi-Threaded Simple Chatting System**

A java application which uses Java threads to handle connecting to multiple clients through Tcp/IP protocol provided with Java Sockets. The App has two main files: ChatServer.java and ChatClient.java. The ChatServer creates a client thread to handle client inputs, it uses the runnable interface for this. Similarly the ChatClient uses a ServerThread to handle data transfer between the server and itself.

The server creates a new Thread each time it has a new device connected to it and adds it to its ArrayList.

The app has the functionality to allow multiple users to talk to each other. To start the app, one must start the server first:

You may do so by entering the following command into the command line after you are in the directory of the files. It will then start the chat server on a default port: 14001:

`java ChatServer`

If you chose to start it on a different port you must do as follows:

`java ChatServer -csp [PORT NUMBER]`

Next we start the chat client by inputting the command:

`java ChatClient`

To use different ip use argument `-cca [IP ADDRESS ]` and to use a different port use `-ccp [PORT]`. You may use both arguments together as such:

`java ChatClient -cca [IP] -ccp [Port]`

If one wishes to shut down the client while using they can type the EXIT command into the chat and the client should close.

Note: Server keeps running until closed on its own.

-> Furthermore, a basic bot has been made which replies with *hmm* when the user types *Bot Sup.*

**NOTE:** You must compile the .java files into .class files for them to work.
