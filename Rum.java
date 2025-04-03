
import java.util.Scanner;

public class Rum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLTree<Integer> avlTree = new AVLTree<>();

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1)Inserir-elemento");
            System.out.println("2)Remover-elemento");
            System.out.println("3)Exibir árvore em ordem");
            System.out.println("4)Exibir árvore em nível");
            System.out.println("5)Exit.....");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Valor a ser inserido: ");
                    int valorInserir = scanner.nextInt();
                    avlTree.insert(valorInserir);
                    System.out.println("inserido!");
                    break;
                case 2:
                    System.out.print("Valor a ser removido: ");
                    int valorRemover = scanner.nextInt();
                    avlTree.RemoveAVLNode(valorRemover);
                    System.out.println("Removido!");
                    break;
                case 3:
                    System.out.println("Árvore-em-ordem:");
                    avlTree.passeioPorOrdem();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Árvore-em-nível:");
                    avlTree.passeioPorNivel();
                    break;
                case 5:
                    System.out.println("Exit...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}