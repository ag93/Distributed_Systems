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
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Aniket Gade
 * Notification process uses the Interface MQSService_Notification in order to invoke the method print(), whose code is written in MQSServant
 * Once the MQS returns the decision, the Notification process prints these on the console.
 */
public class Notification {
     public static void main(String[] args ) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException
     {
         System.out.println("----------------------Notification Board----------------------");
         String[] decision= new String[50]; // To contain all the messages from the MQS, which are to be printed on the console
         while(true)
         {
            MQSService_Notification service = (MQSService_Notification) Naming.lookup("rmi://localhost:5099/MQS");//Returns a reference, a stub, for the remote object MQS at the URL
            decision = service.print();//RMI Call to MQS requesting an Array of Strings containing all the decisions
             for (int i = 0; i < decision.length; i++) {//Iterate the whole String Array
                 if(decision[i]!=null){//If the index doen not contain a NULL String
                 System.out.println(decision[i]);}//Print the decison on the console
             }
             System.out.println("No more messages");//Once the iteration is done, Display "No more messages are left to print"
            TimeUnit.SECONDS.sleep(7);//Sleep for 7 seconds before repeating
         }
     }
}
