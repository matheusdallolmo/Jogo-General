import java.io.Serializable;
import java.util.Random;

public class Dado implements Serializable{
    private int sideUp;

    public Dado(){
        sideUp = 1; 
    }
    public int getSideUp(){
        return sideUp;
    }
    public void roll(){
        Random x = new Random();
        sideUp = x.nextInt(6) + 1;
    }
}