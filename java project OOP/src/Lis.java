import javax.swing.*;
import java.util.Random;

public class Lis extends Zwierze{
    private static final int INICJATYWA_LISA = 7;
    private static final int SILA_LISA = 3;

    public Lis(int posX, int posY, Swiat swiat)
    {
        super(swiat,SILA_LISA,INICJATYWA_LISA,posX,posY,"Lis", "lis.png");
        swiat.dodajOrganizm(this);
    }

    public Lis(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,INICJATYWA_LISA,posX,posY,"Lis", "lis.png");
        swiat.dodajOrganizm(this);
    }

    public void rysowanie()
    {
        System.out.print("L");
    }

    @Override
    public boolean czyTenSamGatunek(Organizm organizm)
    {
        if (organizm instanceof Lis)
            return true;
        else
            return false;
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Lis(posX,posY,swiat);
    }

    @Override
    public void akcja()
    {
        int sprawdzX;
        int sprawdzY;
        this.wykonanoRuch = true;
        Random rand = new Random();
        int direction = rand.nextInt(4);
        if (direction == UP)
        {
            if (y > 0)
            {
                sprawdzX = nextX;
                sprawdzY = nextY - 1;
                if (this.czyBezpieczne(sprawdzX, sprawdzY, getSila())) nextY--;
            }
        }
        else if (direction == DOWN)
        {
            if (this.swiat.getHeight() - 1 > y)
            {
                sprawdzY = nextY + 1;
                sprawdzX = nextX;
                if (this.czyBezpieczne(sprawdzX, sprawdzY, getSila())) nextY++;
            }
        }
        else if (direction == LEFT)
        {
            if (x > 0)
            {
                sprawdzX = nextX - 1;
                sprawdzY = nextY;
                if (this.czyBezpieczne(sprawdzX, sprawdzY, getSila())) nextX--;
            }
        }
        else if (direction == RIGHT)
        {
            if (this.swiat.getWidth() - 1 > x)
            {
                sprawdzX = nextX + 1;
                sprawdzY = nextY;
                if (this.czyBezpieczne(sprawdzX, sprawdzY, this.getSila())) nextX++;
            }
        }
    }

    public boolean czyBezpieczne(int posX, int posY,int sila)
    {
        Organizm temp = swiat.getPolePlanszy(posX,posY);
        if(temp == null) return true;
        else if(temp.getSila() <= sila) return true;
        else return false;
    }
}