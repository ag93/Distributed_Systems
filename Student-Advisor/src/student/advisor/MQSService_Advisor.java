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
 * This interface lets the Student process invoke two methods: adv_request and get_decision  written in the MQSServant
 */
public interface MQSService_Advisor extends Remote{
    public String[] adv_request () throws RemoteException; //This method retrieves the messages by the student from the MQS and gives it to the advisior for processing.
    public void get_decision (String decisions) throws RemoteException; //This method sends the advisor decision to the MQS for storing into the data structure and the file.
}
