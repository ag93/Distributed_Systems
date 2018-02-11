A Thesaurus Client and Server.

Developed by
Name: Aniket Gade
UTA ID: 1001505046

1. Overview
The client process will have a simple GUI interface and will allow the user to select a word in a block of text and have the system send a query to a server to look up the word 
in a thesaurus file and return a list of alternative words from the server. The client process will connect to the server and send the word that the user has selected in an 
input text box. The server will return a string that will contain alternative words that the user might use instead of the input word.
The server process also has a GUI that displays the incoming word entered by the client.

2. Development of Project
(i)   Java used as the programming language.
(ii)  Netbeans IDE used for programming 
(iii) Socket programming used for client server.
(iv)  The server runs on port number 19999.
(v)   Excel Spreadsheet is used for storing the words.

3. Implementation
(i)    Client and server processes successfully connect.
(ii)   Client process and GUI work as specified.
(iii)  Server process works correctly.
(iv)   Server GUI shows incoming client messages.
(v)    Server works correctly with messages from multiple clients.
(vi)   Availabilty of test data.
(vii)  Server maintains connections after each response.
(viii) Comments and headers for all functions/methods in code.
(ix)   Made the server Multithreaded (Extra Credit)

4. Software Requirements
(i)   Windows OS
(ii)  Netbeans IDE.
(iii) Java jdk v7 and above.
(iv)  Apache POI JAR Files.

5. Steps to Run the code
In the Zip file, I have included the entire Netbeans project including the test data (as "Test.xlsx") and the required JAR Files.
(i)   Open the project in Netbeans.
(ii)  Run the Server.java. You will see the Server GUI.
(iii) Then run the Client.java and/or Client1.java. Both of them have their own GUI. You can enter one of the following words and get their alternative words from the server: big,small or due.
(iv)  Once a word is sent from the client side, it will appear on the Server GUI.
(v)   After recieveing the alternative words, to exit and close the client connection, hit "Exit" on the client GUI.
(vi)  To close the server and refuse any further requests from the clients, hit "Exit" on the server GUI.

6. Testing
(i)   Enter any one of the three words in the Client GUI.
	(a) Write any word from the test data and press the button 'Send', this will send the word to the server and then the alternative words will be displayed on the client GUI.
	(b) If any word other than big,small or due is entered, it will result in a blank text field.
(ii)  Once the Synonyms are displayed word can be replaced by a new word.
(iii) The selected word is also displayed in the server GUI.

7. Test Data
An Excel SpreadSheet is used to store the test data with the following entries:
-------------------------------------------------------------------------------------
Word  |  Alternatives								     |
-------------------------------------------------------------------------------------
big   |  colossal, considerable, enormous, fat, full gigantic, hefty, huge	     |
small |  crampled, limited, meager, miniature, modest, narrow, poor, short	     |
due   | expected, outstanding, overdue, owed, payable, scheduled, collectable, IOU   |
--------------------------------------------------------------------------------------

8. Limitations
(i)  This application is case sensitive. That is, it returs alternatives only for words "big, small, due" but not "Big, Small, Due"
(ii) This application displays an empty text field if any word other than the 3 mentioned above is entered.

9. References
This application was made by referring to and modifying the codes from the following sources:
(i)   “Java Socket Programming Examples.” Javanetexamples, cs.lmu.edu/~ray/notes/javanetexamples/.    (Used for Basic Client Server Implementation).
(ii)  Stl, Murali. “Read Write From Excel Sheet.” YouTube, YouTube, 3 June 2014, www.youtube.com/watch?v=ZGGU4aunris.	(Used for Excel Read Process)
(iii) Gliderman's Channel. “Java Ep32 - Networking Pt2 - Multithreaded Server.” YouTube, YouTube, 19 May 2016, www.youtube.com/watch?v=RQ2v0CSV4tY.
