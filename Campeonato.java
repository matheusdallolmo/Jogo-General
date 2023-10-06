import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.io.File;

public class Campeonato {
    private Scanner teclado = new Scanner(System.in);
    private File arquivo = new File("JogoGeneral.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    public void incluirJogador(){ //Funcao para incluir jogador
        if(quantJog != 10){ // Caso o limite de jogadores seja atingido, imprime uma mensagem de limite de jogadores e roda o menu novamente
            String nome;
            char tipoJogador;
            
            System.out.println("->Informe o nome do novo Jogador: ");
            nome = teclado.nextLine();
            
            do{
                System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
                tipoJogador = teclado.next().charAt(0);
                teclado.nextLine();
                if(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M) ou (h,m)\n");

            }while(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M'); //Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto maiusculo quanto minusculo)

            jogadores[quantJog] = new Jogador(nome, tipoJogador);
            quantJog++;
            System.out.println("Jogador "+nome+" adicionado com sucesso!\n");
            
            
        }else
            System.out.println("Limite de jogadores alcancado! Inicie o campeonato.\n");
    }

    public void removerJogador(){ //Funcao para remover jogador
        int opcao = 0;
        System.out.println("Qual jogador voce deseja remover: ");
        for(i = 1; i <= quantJog; i++){ //For para mostrar os jogadores incluidos no campeonato
            System.out.println(i+" -> "+jogadores[i - 1].getNome());
        }
        do {
            System.out.print("Opcao: ");
            opcao = teclado.nextInt();
            if(opcao < 1 || opcao > quantJog)
                System.out.println("Opcao incorreta, selecione uma opcao de jogador valido para remover!");
        } while (opcao < 1 || opcao > quantJog); //Do while para selecionar uma opcao de jogador valida para remover

        
        System.out.println("O jogador "+jogadores[opcao - 1].getNome()+" foi removido com sucesso!\n");
        for(i = opcao - 1; i < quantJog; i++){ //For para remover o jogador
            jogadores[i] = jogadores[i + 1];
        }
        quantJog--; //ajuste do tamanho do vetor de jogadores
    }


    public void iniciarCampeonato(){
        int aux, pontos;

        if(jogadores[i] == null) //Imprime uma mensagem avisando a necessidade de pelo menos um jogador para jogar general
            System.out.println("Eh necessario pelo menos 1 jogador para que o campeonato se inicie. Inclua um jogador!");

        for(int j=1; j<=13; j++){ // For criado para zerar as jogadas e o total, tornando possivel iniciar um novo campeonato varias vezes ate que escolha a opcao de sair
           for(i=0; i<quantJog; i++){
                jogadores[i].pontuaJogada(j, -1);
                jogadores[i].getTotal(j-1);
            } 
        }

        for(int j = 0; j < 13; j++){    //For das jogadas
            for(i = 0; i < quantJog; i++){ //For dos jogadores
                jogadores[i].jogarDados();
                
                if(jogadores[i].getTipoJog() == "H"){// Separa o jogador humano da maquina por conta que o humano escolhe e a maquina nao
                    int escolha;
                    System.out.println("\n-> "+jogadores[i].getNome()+", para qual jogada deseja marcar [1 - 13]?");
                    System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");            
                    for(int k = 0; k < 13; k++){// For para imprimir todos os resultados que o jogador ja possui
                        aux = jogadores[i].getPontos(k);
                        if(aux >= 0)
                            System.out.printf("%s",aux+"\t");
                        else
                            System.out.printf("%s","-\t");
                    }
                    do{
                        System.out.print("\nOpcao: ");        
                        escolha = teclado.nextInt();
                        if(escolha<1 || escolha>13)
                            System.out.println("Escolha incorreta, selecione uma escolha valida: [1-13]");

                    }while(escolha < 1 || escolha > 13); // Do while para que a pessoa selecione apenas jogadas validas, entre 1 e 13
                    
                    if(jogadores[i].getJogadas(escolha) == -1) //If else para validar a jogada, sendo necessario a entrada de escolha ser valida, ou seja, nao ter sido selecionada anteriormente
                        pontos = jogadores[i].validaJogada(escolha);
                    else{
                        while(jogadores[i].getJogadas(escolha) != -1){
                            System.out.println("Jogada invalida, jogue novamente!");
        
                            escolha = teclado.nextInt();
                        }
                        pontos = jogadores[i].validaJogada(escolha);
                    }
        
                    if(pontos == 0)
                        System.out.println("Voce zerou a jogada!!\n");
                    else
                        System.out.println("Voce fez "+pontos+" na jogada "+escolha + "\n");
                        jogadores[i].pontuaJogada(escolha, pontos);
                };
                if(jogadores[i].getTipoJog() == "M"){ //Separando jogador humano de jogador maquina

                    int escolhaMaq = jogadores[i].jogadaMaquina(); // Funcao que calcula a melhor jogada para a maquina(jogadaMaquina)
                    System.out.println("\nJogada escolhida por "+jogadores[i].getNome()+" (M) [1-13]: " + escolhaMaq);

                    pontos = jogadores[i].validaJogada(escolhaMaq);
                    if(pontos == 0)
                        System.out.println("Maquina zerou a jogada!!\n");
                    else
                        System.out.println("Maquina fez "+pontos+" na jogada "+ escolhaMaq);
                        jogadores[i].pontuaJogada(escolhaMaq, pontos);

                    System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");            
                    for(int k = 0; k < 13; k++){// For para imprimir todos os resultados que o jogador ja possui
                        aux = jogadores[i].getPontos(k);
                        if(aux >= 0)
                            System.out.printf("%s",aux+"\t");
                        else
                            System.out.printf("%s","-\t");
                    }
                    System.out.println();
                }
            }
        }
    }

   
    public void mostrarTabela(){ // Funcao para mostrar a tabela, com as devidas formatacoes
        System.out.println("-- Cartela de Resultados --\n");

        System.out.printf("%s", "\t");

        for(i=0; i<quantJog; i++)
            System.out.printf("%10s", jogadores[i].getNome() + "(" + jogadores[i].getTipoJog() + ")\t");

        System.out.println();

        for(i=1; i<=13; i++){
            if(i<=6)
                System.out.printf("%s",  i + "\t");
            else if(i == 7)
                System.out.printf("%s",  i + "(T)\t");
            else if(i == 8)
                System.out.printf("%s",  i + "(Q)\t");
            else if(i == 9)
                System.out.printf("%s",  i + "(F)\t");
            else if(i == 10)
                System.out.printf("%s",  i + "(S+)\t");
            else if(i == 11)
                System.out.printf("%s",  i + "(S-)\t");
            else if(i == 12)
                System.out.printf("%s",  i + "(G)\t");
            else if(i == 13)
                System.out.printf("%s",  i + "(X)\t");

            for(int j=0; j<quantJog; j++){
                System.out.printf("%10s",  jogadores[j].getJogada(i) + "\t");
            }
            System.out.println();
        }
        for(i=0; i<quantJog; i++)
            System.out.print("-----------------");

        System.out.print("\nTotal\t" );

        for(i=0; i<quantJog; i++){
            System.out.printf("%10s", jogadores[i].getTotal(i) + "\t");
        }
        System.out.println();
    }

    public void gravarEmArquivo(){ //Funcao para gravar em arquivo
        try {
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(jogadores);
            oos.flush();
            oos.close();
            fout.close();

            System.out.println("Arquivo gravado com sucesso!");
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

    public void lerDoArquivo(){ //Funcao para ler do arquivo
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

            jogadores = jogadoresArq;

            quantJog = 0; i=0;
            while(jogadores[i] != null){
                quantJog++;
                i++;
            }
        
            System.out.println("Arquivo lido com sucesso!\n");

            } catch (Exception ex) {
                System.err.println("erro: " + ex.toString());
            }
    }

}