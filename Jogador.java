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

    public int getTotal(int i){
        return jogo.getTotal(i);
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

    public int getPontos(int i){
        return jogo.getPontuacao(i);
    }

    public int getJogadas(int i){
        return jogo.getJogada(i);
    }

    public int validaJogada(int i){
        return jogo.validarJogada(i);
    }

    public void pontuaJogada(int jogada, int pontos){
        jogo.pontuarJogada(jogada, pontos);
    }
}
