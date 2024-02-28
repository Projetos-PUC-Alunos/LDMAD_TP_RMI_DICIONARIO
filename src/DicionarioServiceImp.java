import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class DicionarioServiceImp extends UnicastRemoteObject implements DicionarioService {
    private Map<String, String> dicionario;

    public DicionarioServiceImp() throws RemoteException {
        super();
        dicionario = new HashMap<>();
    }

    @Override
    public String lookup(String word) throws RemoteException {
        return dicionario.getOrDefault(word, "Palavra não encontrada");
    }

    @Override
    public String addWord(String word, String meaning) throws RemoteException {
        if (dicionario.containsKey(word)) {
            return "Palavra duplicada";
        } else {
            dicionario.put(word, meaning);
            return "Sucesso";
        }
    }

    @Override
    public String removeWord(String word) throws RemoteException {
        if (dicionario.containsKey(word)) {
            dicionario.remove(word);
            return "Sucesso";
        } else {
            return "Palavra não encontrada";
        }
    }
}

