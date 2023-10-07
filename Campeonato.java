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

    //Funcao para incluir jogador
    public void incluirJogador(){ 
        // Caso o limite de jogadores seja atingido, imprime uma mensagem de limite de jogadores e roda o menu novamente
        if(quantJog != 10){ 
            String nome;
            char tipoJogador;
            
            // Solicitar nome do jogador
            System.out.println("->Informe o nome do novo Jogador: ");
            nome = teclado.nextLine();
            
            // Solicitar tipo do jogador
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

    //Funcao para remover jogador
    public void removerJogador(){ 
        int opcao = 0;
        System.out.println("Qual jogador voce deseja remover: ");
        //For para mostrar a lista de jogadores incluidos no campeonato
        for(i = 1; i <= quantJog; i++){ 
            System.out.println(i+" -> "+jogadores[i - 1].getNome());
        }
        do{
            System.out.print("Opcao: ");
            opcao = teclado.nextInt();
            if(opcao < 1 || opcao > quantJog)
                System.out.println("Opcao incorreta, selecione uma opcao de jogador valido para remover!");
        }while (opcao < 1 || opcao > quantJog); //Do while para selecionar uma opcao de jogador valida para remover

        
        System.out.println("O jogador "+jogadores[opcao - 1].getNome()+" foi removido com sucesso!\n");
        //For para remover o jogador
        for(i = opcao - 1; i < quantJog; i++){ 
            jogadores[i] = jogadores[i + 1];
        }
        // Ajuste do tamanho do vetor de jogadores
        quantJog--; 
    }


    // Funcao para iniciar o campeonato com os jogadores adicionados
    public void iniciarCampeonato(){
        int aux, pontos;

        // Funcao que zera os pontos para mais rodadas poderem ser realizadas
        for(int i = 0; i<quantJog; i++){
            jogadores[i].zerarPontos();
        }

        //Imprime uma mensagem avisando a necessidade de pelo menos um jogador para jogar general
        if(jogadores[i] == null) 
            System.out.println("Eh necessario pelo menos 1 jogador para que o campeonato se inicie. Inclua um jogador!");

        // For criado para zerar as jogadas e o total, tornando possivel iniciar um novo campeonato varias vezes ate que escolha a opcao de sair
        for(int j=1; j<=13; j++){ 
           for(i=0; i<quantJog; i++){
                jogadores[i].pontuaJogada(j, -1);
                jogadores[i].getTotal(j-1);
            } 
        }

        for(int j = 0; j < 13; j++){  //For das jogadas
            for(i = 0; i < quantJog; i++){  //For dos jogadores
                jogadores[i].jogarDados();
                
                if(jogadores[i].getTipoJog() == "H"){// Separa o jogador humano da maquina por conta que o humano escolhe e a maquina nao
                    int escolha;
                    System.out.println("\n-> "+jogadores[i].getNome()+", para qual jogada deseja marcar [1 - 13]?");
                    System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");  
                    // For para imprimir todos os resultados que o jogador ja possui          
                    for(int k = 0; k < 13; k++){
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
                    
                    //If else para validar a jogada, sendo necessario a entrada de escolha ser valida, ou seja, nao ter sido selecionada anteriormente
                    if(jogadores[i].getJogadas(escolha) == -1) 
                        pontos = jogadores[i].validaJogada(escolha);
                    else{
                        while(jogadores[i].getJogadas(escolha) != -1){
                            System.out.println("Jogada invalida, jogue novamente!");
        
                            escolha = teclado.nextInt();
                        }
                        pontos = jogadores[i].validaJogada(escolha);
                    }
        
                    // Mensagem para informar que a rodada foi zerada
                    if(pontos == 0)
                        System.out.println("Voce zerou a jogada!!\n");
                    // Mensagem que informa a quantidade de pontos feitos na rodada
                    else
                        System.out.println("Voce fez "+pontos+" na jogada "+escolha + "\n");
                        jogadores[i].pontuaJogada(escolha, pontos);
                };
                //Separando jogador humano de jogador maquina
                if(jogadores[i].getTipoJog() == "M"){

                    // Funcao que calcula a melhor jogada para a maquina(jogadaMaquina)
                    int escolhaMaq = jogadores[i].jogadaMaquina(); 
                    System.out.println("\nJogada escolhida por "+jogadores[i].getNome()+" (M) [1-13]: " + escolhaMaq);

                    pontos = jogadores[i].validaJogada(escolhaMaq);
                    // Mensagem que informa que a maquina zerou a jogada
                    if(pontos == 0)
                        System.out.println("Maquina zerou a jogada!!\n");
                    // Mensagem que informa quantos pontos a maquina marcou na rodada
                    else
                        System.out.println("Maquina fez "+pontos+" na jogada "+ escolhaMaq);
                        jogadores[i].pontuaJogada(escolhaMaq, pontos);

                    System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n"); 
                    // For para imprimir todos os resultados que o jogador ja possui           
                    for(int k = 0; k < 13; k++){                        
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

   
    // Funcao para mostrar a tabela, com as devidas formatacoes
    public void mostrarTabela(){ 
        System.out.println("-- Cartela de Resultados --\n");

        System.out.printf("%s", "\t");

        // Mostrar nomes dos jogadores
        for(i=0; i<quantJog; i++)
            System.out.printf("%10s", jogadores[i].getNome() + "(" + jogadores[i].getTipoJog() + ")\t");

        System.out.println();

        // Mostrar resultados
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

    //Funcao para gravar em arquivo
    public void gravarEmArquivo(){ 
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

    //Funcao para ler do arquivo
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

            jogadores = jogadoresArq;

            // atulizar a quantidade de jogadores que foram lidos
            quantJog = 0; i=0;
            while(jogadores[i] != null){
                quantJog++;
                i++;
            }
        
            // Informar que o arquivo foi lido com sucesso
            System.out.println("Arquivo lido com sucesso!\n");

            } catch (Exception ex) {
                System.err.println("erro: " + ex.toString());
            }
    }

}