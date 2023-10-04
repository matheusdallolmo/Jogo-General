import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.File;

public class Campeonato {
    Scanner teclado = new Scanner(System.in);
    File arquivo = new File("agenda.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    public void incluirJogador(){
        String nome;
        int tipoJogador;
        System.out.println("->Informe o nome do novo Jogador: ");
        nome = teclado.nextLine();
        System.out.println("Informe o tipo do jogador [1 -> Humano ou 2 -> Maquina]:");
        tipoJogador = teclado.nextInt();
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
        for(int j = 0; j < 10; j++){    
            for(i = 0; i < quantJog; i++){
                jogadores[i].jogarDados();
                jogadores[i].escolherJogada();
                
            }
        }
    }

    public void gravarEmArquivo(){
        try {
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(jogadores);
            oos.flush();
            oos.close();
            fout.close();
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

    public void lerDoArquivo(){
        try {
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);
            /*
            * Lendo os objetos de um arquivo e fazendo a
            * coercao de tipos
            */

            Jogador[] jogadoresArq = (Jogador[]) oin.readObject();
            oin.close();
            fin.close();

            // Uma forma de diferente do for para percorrer vetores
            for (Jogador j : jogadoresArq) {
                j.imprimirDados();
            }
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

}
