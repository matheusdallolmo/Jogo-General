import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.File;

public class Campeonato {
    private Scanner teclado = new Scanner(System.in);
    private File arquivo = new File("agenda.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    public void incluirJogador(){
        String nome;
        int tipoJogador;
        System.out.println("->Informe o nome do novo Jogador: ");
        nome = teclado.nextLine();
        System.out.println("Informe o tipo do jogador [1 -> Humano ou 2 -> Maquina]:");
        tipoJogador = teclado.nextInt();
        teclado.nextLine();
        jogadores[quantJog] = new Jogador(nome, tipoJogador);
        quantJog++;
        System.out.println("Jogador "+nome+" adicionado com sucesso!\n");
    }

    public void removerJogador(){
        int opcao;
        System.out.println("Qual jogador voce deseja remover: ");
        for(i = 1; i <= quantJog; i++){
            System.out.println(i+" -> "+jogadores[i - 1].getNome());
        }
        System.out.print("Opcao: ");
        opcao = teclado.nextInt();
        System.out.println("O jogador "+jogadores[opcao - 1].getNome()+" foi removido com sucesso!\n");
        for(i = opcao - 1; i < quantJog; i++){
            jogadores[i] = jogadores[i + 1];
        }
        quantJog--;
    }

    public void iniciarCampeonato(){
        int aux, pontos;
        for(int j = 0; j < 13; j++){    
            for(i = 0; i < quantJog; i++){
                jogadores[i].jogarDados();
                
                if(jogadores[i].getTipoJog() == "H"){// Separa o jogador humano da maquina por conta que o humano escolhe e a maquina nao
                    System.out.println("\n-> "+jogadores[i].getNome()+", para qual jogada deseja marcar [1 - 13]?");
                    System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");            
                    for(int k = 0; k < 13; k++){// For para imprimir todos os resultados que o jogador ja possui
                        aux = jogadores[i].getPontos(k);
                        if(aux >= 0)
                            System.out.printf("%s",aux+"\t");
                        else
                            System.out.printf("%s","-\t");
                    }
                    System.out.print("\nOpcao: ");
                    
                    int escolha = teclado.nextInt();
                    if(jogadores[i].getJogadas(escolha) == -1)
                        pontos = jogadores[i].validaJogada(escolha);
                    else{
                        while(jogadores[i].getJogadas(escolha) != -1){
                            System.out.println("Jogada invalida, jogue novamente!");
        
                            escolha = teclado.nextInt();
                        }
                        pontos = jogadores[i].validaJogada(escolha);
                    }
        
                    if(pontos == 0)
                        System.out.println("Você zerou a jogada!!");
                    else
                        System.out.println("Você fez "+pontos+" na jogada "+escolha + "\n");
                        jogadores[i].pontuaJogada(escolha, pontos);
                };
                if(jogadores[i].getTipoJog() == "M"){// Funcao que calcula a melhor jogada para a maquina
        
                    System.out.println("Jogada escolhida por "+jogadores[i].getNome()+" (M)");
                    for(int i = 0; i < 13; i++){// For para imprimir todos os resultados que o jogador ja possui
                        aux = jogadores[i].getPontos(i);
                        if(aux >= 0)
                            System.out.print(aux );
                        else
                            System.out.print("- ");
                    }
                }
            }
        }
    }

    public void mostrarTabela(){
        System.out.println("-- Cartela de Resultados --\n");

        for(i=0; i<quantJog; i++)
            System.out.printf("%15s", "    " + jogadores[i].getNome() + "(" + jogadores[i].getTipoJog() + ")");

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
