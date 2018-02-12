/*
The basic code of RMI was based on the code by Bien, Adam. “Hello Remote Method Invocation (RMI).” www.youtube.com, 7 Dec. 2013, www.youtube.com/watch?v=X-bL0S8b6C4 
 */
package student.advisor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Aniket Gade
 */
public class MQServer {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5099); //Creates and exports a Registry instance on the local host that accepts requests port 5099.
        registry.rebind("MQS", new MQSServant()); //Replaces the binding for MQS in this registry with an instance of MQSServant().
    }
}
