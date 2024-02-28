import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DicionarioService extends Remote {
    String lookup(String word) throws RemoteException;
    String addWord(String word, String meaning) throws RemoteException;
    String removeWord(String word) throws RemoteException;
}
