import java.util.Scanner;

public class usaCampeonato{
    public static void main(String[] args){
        Campeonato camp = new Campeonato();
        Scanner scanner = new Scanner(System.in);
        char opcao;

        // Menu Interativo
        do{
            System.out.println("\n..:: Menu Interativo ::..");
            System.out.println("a -> Incluir Jogador");
            System.out.println("b -> Remover Jogador");
            System.out.println("c -> Iniciar Campeonato");
            System.out.println("d -> Mostrar a Cartela de Resultados");
            System.out.println("e -> Gravar o Campeonato em arquivo");
            System.out.println("f -> Ler Campeonato de arquivo");
            System.out.println("g -> Sair\n");

            System.out.println("Escolha uma opcao: ");
            opcao = scanner.next().charAt(0);

            // Switch que roda a funcao escolhida atraves do menu interativo
            switch(opcao){
                case 'a':
                    camp.incluirJogador();
                break;
                case 'b':
                    camp.removerJogador();
                break;
                case 'c':
                    camp.iniciarCampeonato();
                break;
                case 'd':
                    camp.mostrarTabela();
                break;
                case 'e':
                    camp.gravarEmArquivo();
                break;
                case 'f':
                    camp.lerDoArquivo();
                break;
                case 'g':
                break;

                default:
                    System.out.println("Opcao invalida. Tente novamente" ) ;
            }

        }while(opcao != 'g');
        scanner.close();
    }
}
