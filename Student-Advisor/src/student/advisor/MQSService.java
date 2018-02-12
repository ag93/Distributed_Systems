/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.advisor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aniket Gade
 * This interface lets the Student process invoke the method request written in the MQSServant
 */
public interface MQSService extends Remote{
    public String request (int sentbystudent, String name, String subject) throws RemoteException;// This function passes the name, subject to the MQS and retrns a String.
}
