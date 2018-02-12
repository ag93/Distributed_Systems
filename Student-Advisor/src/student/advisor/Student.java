/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.advisor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author Aniket Gade
 * The Student process uses the Interface MQSService in order to invoke the method request() with parameters 1, student name , subject; whose code is written in MQSServant
 * The MQS will return a "Message sent" message, that will be printed on the console of student process. 
 */
public class Student {
    
     public static void main(String[] args ) throws RemoteException, MalformedURLException, NotBoundException
    {
        Scanner scan = new Scanner(System.in); // Object of scanner class 
        String ind = "y";//Used in while loop (to let user decide whether to continue or not)
        do
        {
        System.out.println("Enter Name :: ");
        String name = scan.next(); //// Take the name of the Student
        System.out.println("\nEnter Subject :: ");
        String subject = scan.next();// Take the name of the Subject
        MQSService service = (MQSService) Naming.lookup("rmi://localhost:5099/MQS"); //Returns a reference, a stub, for the remote object MQS at the URL
        System.out.println("\n" + service.request(1,name,subject));// Send the message to the MQS using RMI.
        System.out.println("Send another request? (y/n)");// Ask user if he/she wants to continue
        ind = scan.next();
        }while(ind.equals("y")  || ind.equals("Y")); //continue if user says Y or y.
    }
}
