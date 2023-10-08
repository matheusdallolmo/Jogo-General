import java.io.Serializable;

public class Jogador implements Serializable{
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
        jogo = new JogoGeneral();
        if(tipo == 'h' || tipo == 'H')
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

    // Funcao que retorna a pontuacao total do jogador
    public int getTotal(int i){
        return jogo.getTotal(i);
    }

    // Funcao que atualiza as informacoes do jogador
    public void atualizaJogador(String nome, String tipoJogador){
        this.nome = nome;
        this.tipoJogador = tipoJogador;
    }

    // Funcao para imprimir nome e tipo do jogador
    public void imprimirDados(){
        System.out.println("Nome: "+this.nome);
        System.out.println("Tipo do Jogador: "+tipoJogador);
    }

    // Funcao que rola os dados para o jogador
    public void jogarDados(){
        // Se o jogador for humano ele imprime uma mensagem
        if(tipoJogador == "H")
            System.out.println("Rolando dados para "+nome+" (H)");
        // Se o jogador for uma maquina ele imprime outra mensagem
        else
            System.out.println("\nRolando dados para "+nome+" (M)");
        // Imprime os valores obtidos na rolagem
        System.out.print("Valores obtidos: ");
        jogo.rolarDados();
    }

    // Funcao que retorna os pontos feitos na jogada escolhida
    public int getPontos(int i){
        return jogo.getPontuacao(i);
    }

    // Funcao que retorna a pontuacao na jogada
    public int getJogadas(int i){
        return jogo.getJogada(i);
    }

    // Funcao que valida a jogada
    public int validaJogada(int i){
        return jogo.validarJogada(i);
    }

    // Funcao para escolher a jogada da maquina
    public int jogadaMaquina(){
        if ((jogo.getJogada(12) == -1) && (jogo.validarJogada(12) == 50))
            return 12;
        else if ((jogo.getJogada(11) == -1) && (jogo.validarJogada(11) == 40))
            return 11;
        else if ((jogo.getJogada(10) == -1) && (jogo.validarJogada(10) == 30))
            return 10;
        else if ((jogo.getJogada(9) == -1) && (jogo.validarJogada(9) == 25))
            return 9;
        else if ((jogo.getJogada(8) == -1) && (jogo.validarJogada(8) != 0))
            return 8;
        else if ((jogo.getJogada(7) == -1) && (jogo.validarJogada(7) != 0))
            return 7;
        else if ((jogo.getJogada(13) == -1) && (jogo.validarJogada(13) != 0))
            return 13;
        else if ((jogo.getJogada(6) == -1) && (jogo.validarJogada(6) != 0))
            return 6;
        else if ((jogo.getJogada(5) == -1) && (jogo.validarJogada(5) != 0))
            return 5;
        else if ((jogo.getJogada(4) == -1) && (jogo.validarJogada(4) != 0))
            return 4;
        else if ((jogo.getJogada(3) == -1) && (jogo.validarJogada(3) != 0))
            return 3;
        else if ((jogo.getJogada(2) == -1) && (jogo.validarJogada(2) != 0))
            return 2;
        else if ((jogo.getJogada(1) == -1) && (jogo.validarJogada(1) != 0))
            return 1;
        else{
            for(int i=13; i>0; i--){
                if(jogo.getJogada(i) == -1){
                        return i;
                }
            }
            return 0;
        }
    }

    // Funcao para atualizar a pontuacao do jogador
    public void pontuaJogada(int jogada, int pontos){
        jogo.pontuarJogada(jogada, pontos);
    }
}