import java.util.Scanner;

public class Campeonato {
    Scanner teclado = new Scanner(System.in);
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    public void incluirJogador(){
        String nome;
        String tipoJogador;
        teclado.nextLine();
        System.out.println("->Informe o nome do novo Jogador: ");
        nome = teclado.nextLine();
        teclado.nextLine();
        System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
        tipoJogador = teclado.nextLine();
        jogadores[quantJog].atualizaJogador(nome, tipoJogador);
        quantJog++;
        System.out.println("Jogador "+nome+" adicionado com sucesso!");
    }

    public void removerJogador(){
        int opcao;
        System.out.println("Qual jogador voce deseja remover: ");
        for(i = 1; i <= quantJog; i++){
            System.out.println(i+" -> "+jogadores[i].getNome());
        }
        opcao = teclado.nextInt();
        System.out.println("O jogador "+jogadores[opcao].getNome()+" foi removido com sucesso!");
        for(i = opcao; i < quantJog; i++){
            jogadores[i] = jogadores[i + 1];
        }
    }

}
