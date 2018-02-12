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
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Aniket Gade
 * Advisor process uses the Interface MQSService_Advisor in order to invoke the two methods : 1) adv_request() 2) get_decision(String decisions)
 * The adv_request() retrieves all the messages from the MQS.
 * The get_decision() puts the decisions made by the advisor into the MQS.
 */
public class Advisor {
     public static void main(String[] args ) throws RemoteException, MalformedURLException, NotBoundException, InterruptedException
    {
        System.out.println("----------------------Advisor----------------------");  
        MQSService_Advisor service1 = (MQSService_Advisor) Naming.lookup("rmi://localhost:5099/MQS"); //Returns a reference, a stub, for the remote object MQS at the URL
        String[] requests = new String[20]; //To store the incoming messages from the MQS
        String decision = null;//To Store the Decision
        int flag=0;//If there are no decisions to be made flag will be set to 0
        boolean random; //Used to give a random decision
        Random rand = new Random(); //Random Number Generator
        while(true)
        {
            requests = service1.adv_request();//RMI Call to MQS requesting an Array of Strings containing all the requests
            for (int i = 0; i < requests.length; i++) { //Loop throught the Array of Strings
                if(requests[i]!=null){ //If the index is not NULL
                    flag = 1; //As request is found, Set Flag to 1
                    random = rand.nextBoolean(); //Generate a random boolean 
                    if(random == true)  //If random is true, accept the request
                    {
                        decision = requests[i].substring(2)+" Accepted"; //Copy the message to the string
                        service1.get_decision(decision); //Send the Decision to MQS
                        System.out.println(decision);
                        flag = 0;//As request is sent, set flag to 0
                    }
                    else //random is false, hence decline the request
                    {
                        decision = requests[i].substring(2)+" Denied"; //Copy the message to the string
                        service1.get_decision(decision); //Send the Decision to MQS
                        System.out.println(decision);
                        flag = 0;//As request is sent, set flag to 0
                    }
                }
            }
            if (flag != 1){ // If flag is 1, it means that there are no messages to accept/deny
            System.out.println("NO MESSAGES FOUND");} //Print the message on the console
            TimeUnit.SECONDS.sleep(3); //Sleep for 3 Seconds
        }
    }
}