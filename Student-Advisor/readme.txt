Online Advising Simulation using RPC & Message Queuing

Developed by
Name: Aniket Gade
UTA ID: 1001505046

1. Overview
Simulate an online advising system using RMI.  Students request clearance for a course from the advisor. At some other time the advisor approves or disapproves the request and then
the student is notified of the advisor’s decision. The student process, advisor process and notification process communicate through the message queuing server.

2. Development of Project
(i)   Java used as the programming language.
(ii)  Netbeans IDE used for programming 
(iii) RMI used for communication between processes.
(iv)  Port number used:- 5099.
(v)   One Array is used to store the data from Student and Advisor process.

3. Implementation
(i)    Student Process works correctly.
(ii)   Advisor Process works correctly.
(iii)  Server process works correctly.
(iv)   Notification Process works correctly.
(v)    MQS stores and retrieves the messages correctly
(vi)   MQS deletes message after it is retrieved
(vii)  Messages in MQS are persistent
(viii) Messages are stored in a single data structure - (Array named requests)
(ix)   Student, Advisor and Notification process work independently of each other
(x)    Comments in code

4. Software Requirements
(i)   Windows OS
(ii)  Netbeans IDE.
(iii) Java jdk v7 and above.

5. Steps to Run the code
In the Zip file, I have included the entire Netbeans project.
(i)   Open the project in Netbeans.
(ii)  Run the MQServer.java. The console will display "----------------------MQServer----------------------" when it is ready to accept connections.
(iii) Now you can run any one of the 3 processes i.e Student.java or Advisor.java or Notification.java.
(iv)  In the Student process, user will have to enter the name of the student and the name of the subject. You can enter multiple entries, but one at a time.
      After every entry is taken, the process will ask if you want to continue. Press 'y' or 'Y' to cotinue else 'n' to stop.
(v)   In the advisor process, every 3 seconds, it will ask the MQS to send any requests from the Student process. If no requests are available, it will display	
      "No Messages Found" on the Advisor's Console. This will happen until the advisor process is manually killed. If any requests are available, the advisor
      Process will randomly accept or deny it and return the decision to MQS which will store it in the array as well as a file.
(vi)  In the advisor process, every 7 seconds, it will ask the MQS to send any decisions from the Advisor process. If no requests are available, it will display	
      "No Messages Found" on the Notification Console. This will happen until the Notification process is manually killed. If any decisions are available, the 
      Notification Process will print it on the console.


6. Limitations
(i)  In the Student Process, the name of the student and the name of the subject must be one word respectively.


7. References
(i) Bien, Adam. “Hello Remote Method Invocation (RMI).” www.youtube.com, 7 Dec. 2013, www.youtube.com/watch?v=X-bL0S8b6C4