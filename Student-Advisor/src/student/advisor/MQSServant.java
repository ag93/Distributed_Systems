/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.advisor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Aniket Gade
 * This file contains all the methods that the clients invoke using RMI.
 */



public class MQSServant extends UnicastRemoteObject implements MQSService,MQSService_Advisor,MQSService_Notification{
    String[] requests = new String[50]; //Array of Strings to Store messages from Student and Advisor 
    int n = 0; //Keeps track of number of entries in the data structure (String[] requests)
    String temp = "";
    
    
    public MQSServant() throws RemoteException
    {
        super(); // Call to the super constructor
        System.out.println("----------------------MQSERVER----------------------");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("Student_Requests.txt"));//Open file to retrieve persistent data sent by student
            String line;
            while ((line = reader.readLine()) != null) {
                requests[n] = line;//Add data in the file to the data structure (requests)
                //System.out.println(n + " " + requests[n]);
                n++;// increment the index to add new data from the file.
            }
            reader.close(); //Close the reader
        }
        catch(Exception e)
        {
            System.out.println("Error in Try Catch of Const" + e);
        }
        
        try
        {
            BufferedReader reader1 = new BufferedReader(new FileReader("Advisor_Decision.txt")); //Open the to retrieve persistent data sent by the advisor
            String line;
            while ((line = reader1.readLine()) != null) {
                requests[n] = line; //Add data in the file to the data structure (requests) at index 'n'
                n++;// increment the index to add new data from the file.
            }
            reader1.close();//Close the reader
            //for (int i = 0; i < requests.length; i++) {
            //    if(requests[i]!=null)
            //    System.out.println(requests[i]);
            //}
        }
        catch(Exception e)
        {
            System.out.println("Error in Try Catch of Const" + e);
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
    
    /*
    This function gets an integer '1', name and subject. It saves this data in the global data structure requests[] and writes it to a file "Student_Requests.txt"
    It returns a string saying "Message sent Successfully" inorder to notify the student.    
    */
    @Override
    public String request(int sentbystudent,String name1, String subject1) throws RemoteException {
            requests[n]=sentbystudent+","+name1+" "+subject1; //Accept the data incoming from the Student
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Student_Requests.txt",true)); //Open a Writer to write into the file and make messages persistent
                writer.newLine();//Go to new line
                writer.write(requests[n]);//Write the data into the file                    
                writer.close();//Close the writer
            } catch (IOException ex) {
                System.out.println("Error in Opening File");
            }
            //System.out.println(requests[n].substring(2));
            n++;//Incerment the index 'n' to write the next message to file.
            return ("Request Sent Successfully!");
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /*
    This function, when invoked, the MQS checks the data structure (requests) for any requests by students
    If any messages are found, it returns a string array containing all the requests.    
    */    
    
    @Override
    public String[] adv_request() throws RemoteException 
    {
        String[] to_advisor = new String[20]; //New String of array to take all the messages intended for the advisor
        int temp_counter=0;//Keeps track of number of messages to sent to the the advisor
        for (int i = 0; i <= requests.length-1; i++) {//Loop through the entire data structure 
            temp = requests[i]; //Take one string into temp string inorder to compare and send
            if(temp != null)//If the string is not null
            {
                if(temp.substring(0, 1).equals("1"))//if the 1st index of the String == 1, it means we need to send it to the advisor
                {
                    to_advisor[temp_counter] = temp;//add it to to_advisor String array
                    requests[i] = null;//delete the copied element from  the main data structure
                    temp_counter++;//increment the number of elements in the String [] to be sent to the advisor
                }
            }
        }
        try
        {
            PrintWriter pw = new PrintWriter("Student_Requests.txt");//delete the file once all the messages are sent to the advisor
            pw.close();//close the printerwriter
        }
        catch(Exception e)
        {
             System.out.println(e);       
        }
        return to_advisor;//return the String Array to the advisor process.
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /*
    This function recieves a String containing the decison from the advisor.
    It stores these messages in the global data structure and makes them persistent by writing them in a file.    
    */     
    @Override
    public void get_decision(String decisions) throws RemoteException
    {
        for (int i = 0; i < requests.length; i++) {//Iterate the main datastructure
            if(requests[i]==null)//If the index does not contain any previous data
            {
                requests[i]=decisions;//Store the decision in the main data structure i.e. requests
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("Advisor_Decision.txt",true));//open file to make the data persistent
                    writer.newLine();//take a new line
                    writer.write(requests[i]);//write it to the file                    
                    writer.close();//close the buffered writer
                } catch (IOException ex) {
                    System.out.println("Error in Opening File");
                }
                break;//Once a message has been written break from the loop to avoid unnecessary writes.
            }
        }
    }
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    

    /*
    This function is invoked by thte Notification Process. Once invoked, it checks for all the decisions made by the advisor
    Once these messages are retrieved and collected in String Array temp, it is returned back to the Notification process for printing. 
    */     
    
    @Override
    public String[] print() throws RemoteException
    {
        String temp1; // Temporary String
        String[] temp= new String[50]; //String Array to hold all the messages to be sent to the Notification Process
        int p=0;// p is a counter that keeps track of number of elements in String[] temp
        for (int i = 0; i <= requests.length-1; i++) {//loop through the data structure
            temp1 = requests[i];//take the element at index i in temp1
            if(temp1 != null)//If it is not null
            {
                if(! temp1.substring(0, 1).equals("1"))//and if its 1st element is NOT 1, it means that the messaege was sent by advisor
                {
                    temp[p] = temp1;//store the advisors decisison in temp[p]
                    p++;//incement the number of elements in p
                    requests[i] = null;// delete the decision from the main data structure
                }                 
            }
        }
        try
        {
            PrintWriter pw1 = new PrintWriter("Advisor_Decision.txt");//delete the file having the sent messages
            pw1.close();//close the printer writer
        }
        catch(Exception e)
        {
             System.out.println(e);       
        }
        return temp;//return all the decision to the notification process
    }
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------