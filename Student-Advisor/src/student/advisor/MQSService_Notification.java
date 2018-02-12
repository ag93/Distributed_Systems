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
 *  This interface lets the Notification process invoke the print method written in the MQSServant
 */
public interface MQSService_Notification extends Remote {
    public String[] print() throws RemoteException;//This method retrieves all the decision that the advisor made, from the MQS and returns it to Notification Process for Printing.
}
