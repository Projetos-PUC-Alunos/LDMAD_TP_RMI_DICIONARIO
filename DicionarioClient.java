import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class DicionarioClient {
    private DicionarioService server;

    public DicionarioClient(String serverURL) {
        try {
            server = (DicionarioService) Naming.lookup(serverURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java DictionaryClient <URL do servidor>");
            System.exit(0);
        }

        String serverURL = args[0];
        DicionarioClient client = new DicionarioClient(serverURL);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Consultar significado");
            System.out.println("2. Adicionar palavra");
            System.out.println("3. Remover palavra");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (option) {
                case 1:
                    System.out.print("Digite a palavra: ");
                    String word = scanner.nextLine();
                    try {
                        String meaning = client.server.lookup(word);
                        client.showMessage(meaning);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.print("Digite a palavra: ");
                    String newWord = scanner.nextLine();
                    System.out.print("Digite o significado: ");
                    String meaning = scanner.nextLine();
                    try {
                        String result = client.server.addWord(newWord, meaning);
                        client.showMessage(result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Digite a palavra: ");
                    String removeWord = scanner.nextLine();
                    try {
                        String result = client.server.removeWord(removeWord);
                        client.showMessage(result);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
