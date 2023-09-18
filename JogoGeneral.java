public class JogoGeneral {
    private Dado[] dados = new Dado[5];
    private int[] jogadas = new int[13]; 

    public void rolarDados(){
        for(int i=0; i<5; i++){
            dados[i].roll();
        }
    }

    public void validarJogada(int x){
        int i;
        int soma = 0;
        switch (x):
            case 1:
                for(i=0; i<6; i++){
                    if (dados[i] == 1)
                        soma++;
                }
                jogadas[0] = soma;
            break;
            case 2:
                for(i=0; i<6; i++){
                    if (dados[i] == 2)
                        soma += dados[i];
                }
                jogadas[1] = soma;
            break;
            case 3:
                for(i=0; i<6; i++){
                    if (dados[i] == 3)
                        soma += dados[i];
                }
                jogadas[2] = soma;
            break;
            case 4:
                for(i=0; i<6; i++){
                    if (dados[i] == 4)
                        soma += dados[i];
                }
                jogadas[3] = soma;
            break;
            case 5:
                for(i=0; i<6; i++){
                    if (dados[i] == 5)
                        soma += dados[i];
                }
                jogadas[4] = soma;
            break;
            case 6:
                for(i=0; i<6; i++){
                    if (dados[i] == 6)
                        soma += dados[i];
                }
                jogadas[5] = soma;
            break;

            case 7:
                int[] cont = new int[6];

                for(i=0; i<6; i++){
                    if (dados[i] == 1)
                        cont[0]++;
                }
            break;
            case 8:
                
            break;
            case 9:
                
            break;
            case 10:
                
            break;
            case 11:
                
            break;
            case 12:
                
            break;
            case 13:
                
            break;

    }

}
