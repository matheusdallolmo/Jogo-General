public class Jogador {
    private String nome = new String();
    private String tipoJogador;
    private JogoGeneral jogo = new JogoGeneral();

    public Jogador(){
        nome = "sem nome";
        tipoJogador = " ";
    }

    public String getNome(){
        return nome;
    }

    public void atualizaJogador(String nome, String tipoJogador){
        this.nome = nome;
        this.tipoJogador = tipoJogador;
    }

    public void jogarDados(){
        if(tipoJogador == "H")
            System.out.println("Rolando dados para"+nome+" (H)");
        else
            System.out.println("Rolando dados para"+nome+" (M)");
        System.out.println("Valore obtidos: ");
        jogo.rolarDados();
    }

    public void escolherJogada(){
        int aux;
        if(tipoJogador == "H"){
            System.out.println("-> "+nome+", para qual jogada deseja marcar [1 - 13]?");
            System.out.println("1 2 3 4 5 6 7(T) 8(Q) 9(F) 10(S+) 11(S-) 12(G) 13(X)\n");
            for(int i = 0; i < 13; i++){
                aux = jogo.getPontuacao(i);
                if(aux >= 0)
                    System.out.print(aux );
                else
                    System.out.print("- ");
            }
        };

    }
}
