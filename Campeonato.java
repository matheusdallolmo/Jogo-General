import java.util.Scanner;

public class Campeonato {
    Scanner teclado = new Scanner(System.in);
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    public void incluirJogador(){
        String nome;
        String tipoJogador;
        System.out.println("->Informe o nome do novo Jogador: ");
        nome = teclado.nextLine();
        System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
        tipoJogador = teclado.nextLine();
        jogadores[quantJog] = new Jogador(nome, tipoJogador);
        quantJog++;
        System.out.println("Jogador "+nome+" adicionado com sucesso!");
    }

    public void removerJogador(){
        int opcao;
        System.out.println("Qual jogador voce deseja remover: ");
        for(i = 1; i <= quantJog; i++){
            System.out.println(i+" -> "+jogadores[i - 1].getNome());
        }
        opcao = teclado.nextInt();
        System.out.println("O jogador "+jogadores[opcao - 1].getNome()+" foi removido com sucesso!");
        for(i = opcao - 1; i < quantJog; i++){
            jogadores[i] = jogadores[i + 1];
        }
        quantJog--;
    }

    public void iniciarCampeonato(){
        
    }

}
