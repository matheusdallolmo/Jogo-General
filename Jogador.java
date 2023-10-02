import java.util.Scanner;

public class Jogador {
    private String nome = new String();
    private String tipoJogador;
    private JogoGeneral jogo = new JogoGeneral();

    // Contrutor padrao para Jogador
    public Jogador(){
        nome = "sem nome";
        tipoJogador = " ";
    }

    // Construtor carregado para jogador
    public Jogador(String nome, int tipo){
        this.nome = nome;
        if(tipo == 1)
            tipoJogador = "H";
        else
            tipoJogador = "M";
    }

    // Funcao que retorna o nome do Jogador
    public String getNome(){
        return nome;
    }

    // Funcao que retorna o tipo do jogador (Humano ou maquina)
    public String getTipoJog(){
        return tipoJogador;
    }

    // Funcao que retorna a jogada
    public int getJogada(int i){
        return jogo.getJogada(i);
    }

    // Funcao que atualiza as informacoes do jogador
    public void atualizaJogador(String nome, String tipoJogador){
        this.nome = nome;
        this.tipoJogador = tipoJogador;
    }

    public void imprimirDados(){
        System.out.println("Nome: "+this.nome);
        System.out.println("Tipo do Jogador: "+tipoJogador);
    }

    // Funcao que rola os dados para o jogador
    public void jogarDados(){
        if(tipoJogador == "H")// Se o jogador for humano ele imprime uma mensagem
            System.out.println("Rolando dados para "+nome+" (H)");
        else// Se o jogador for uma maquina ele imprime outra mensagem
            System.out.println("Rolando dados para"+nome+" (M)");
        System.out.print("Valores obtidos: ");// Imprime os valores obtidos na rolagem
        jogo.rolarDados();
    }

    // Funcao que permite que o jogador escolha qual jogada ele deseja usar
    public void escolherJogada(){
        Scanner sc = new Scanner(System.in);
        int aux,  pontos;
        if(tipoJogador == "H"){// Separa o jogador humano da maquina por conta que o humano escolhe e a maquina nao
            System.out.println("\n-> "+nome+", para qual jogada deseja marcar [1 - 13]?");
            System.out.println("1 2 3 4 5 6 7(T) 8(Q) 9(F) 10(S+) 11(S-) 12(G) 13(X)\n");
            for(int i = 0; i < 13; i++){// For para imprimir todos os resultados que o jogador ja possui
                aux = jogo.getPontuacao(i);
                if(aux >= 0)
                    System.out.print(aux );
                else
                    System.out.print("- ");
            }
            System.out.print("\nOpcao: ");
            
            int escolha = sc.nextInt();
            pontos = jogo.validarJogada(escolha);

            if(pontos == 0)
                System.out.println("Você zerou a jogada!!");
            else
                System.out.println("Você fez "+pontos+" na jogada "+escolha + "\n");
                jogo.pontuarJogada(escolha, pontos);
        };
        if(tipoJogador == "M"){// Funcao que calcula a melhor jogada para a maquina

            System.out.println("Jogada escolhida por "+nome+" (M)");
            for(int i = 0; i < 13; i++){// For para imprimir todos os resultados que o jogador ja possui
                aux = jogo.getPontuacao(i);
                if(aux >= 0)
                    System.out.print(aux );
                else
                    System.out.print("- ");
            }
        };
    }
}
