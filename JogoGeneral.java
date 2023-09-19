public class JogoGeneral {
    private Dado[] dados = new Dado[5];
    private int[] jogadas = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}; 

    public void rolarDados(){
        for(int i=0; i<5; i++){
            dados[i].roll();
        }
    }

    // Funcao que valida se a jogada e possivel e calcula os pontos marcados
    // Retorna 0 se a jogada nao for possivel ou os pontos marcados se ela for possivel
    public int validarJogada(int jogada){
        int i, quant = 0;
        // Verifica e calcula os pontos para a Jogada de 1's
        if(jogada == 1){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 1)
                    quant += 1;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 2's
        else if(jogada == 2){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 2)
                    quant += 2;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 3's
        else if(jogada == 3){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 3)
                    quant += 3;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 4's
        else if(jogada == 4){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 4)
                    quant += 4;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 5's
        else if(jogada == 5){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 5)
                    quant += 5;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 6's
        else if(jogada == 6){
            for(i = 0; i < 5; i++)
                if(dados[i].getSideUp() == 6)
                    quant += 6;
            return quant;
        }
        // Verifica e calcula os pontos para a Trinca(T)
        else if(jogada == 7){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++)
                    if(dados[i].getSideUp() == dados[j].getSideUp()){
                        quant += 1;
                        if(quant >= 3){
                            quant = 0;
                            for(int k = 0; k < 5; k++)
                                quant += dados[k].getSideUp();
                            return quant;
                        }
                            
                    }
            }
            return 0;
        }
        // Verifica e calcula os pontos para a Quadra(Q)
        else if(jogada == 7){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++)
                    if(dados[i].getSideUp() == dados[j].getSideUp()){
                        quant += 1;
                        if(quant >= 4){
                            for(int k = 0; k < 5; k++)
                                if(dados[i].getSideUp() == dados[k].getSideUp())
                                    quant += dados[i].getSideUp();
                            return quant;
                        }
                            
                    }
            }
            return 0;
        }
        // Verifica e calcula os pontos para Full-Hand(F)/Full-House(F)
        else if(jogada == 8){
            
        }

        return -1;
    }

    public void pontuarJogada(int jogada, int pontos){
        jogadas[jogada - 1] = pontos;
    }
}
