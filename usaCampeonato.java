import java.util.Scanner;

public class usaCampeonato{
    public static void main(String[] args){
        Campeonato camp = new Campeonato();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do{
            System.out.println("..:: Menu Interativo ::..");
            System.out.println("1 -> Incluir Jogador");
            System.out.println("2 -> Remover Jogador");
            System.out.println("3 -> Iniciar Campeonato");
            System.out.println("4 -> Mostrar a Cartela de Resultados");
            System.out.println("5 -> Gravar o Campeonato em arquivo");
            System.out.println("6 -> Ler Campeonato de arquivo");
            System.out.println("7 -> Sair");

            opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    camp.incluirJogador();
                break;
                case 2:
                    camp.removerJogador();
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                default:
            }

        }while(opcao != 7);
        scanner.close();
    }
}
