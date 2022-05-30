import java.util.Random;

public class Zolw extends Zwierze{
    private static final int INICJATYWA_ZOLWIA = 1;
    private static final int SILA_ZOLWIA = 2;
    private static final int PANCERZ = 5;

    public Zolw(int posX, int posY, Swiat swiat)
    {
        super(swiat,SILA_ZOLWIA,INICJATYWA_ZOLWIA,posX,posY,"Zolw", "zolw.png");
        swiat.dodajOrganizm(this);
    }

    public Zolw(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,INICJATYWA_ZOLWIA,posX,posY,"Zolw", "zolw.png");
        swiat.dodajOrganizm(this);
    }

    public void rysowanie()
    {
        System.out.print("Z");
    }

    @Override
    public boolean czyTenSamGatunek(Organizm organizm)
    {
        if (organizm instanceof Zolw)
            return true;
        else
            return false;
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Zolw(posX,posY,swiat);
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        if (atakujacy.getSila() < PANCERZ)
        {
            return true;
        }
        else return false;
    }

    @Override
    public void akcja()
    {
        this.wykonanoRuch = true;
        Random rand = new Random();
        int czyRusza = rand.nextInt(4);
        if(czyRusza == 1)
        {
            int direction = rand.nextInt(4);
            if(direction == UP)
            {
                if(this.getY() > 0)
                    nextY--;
            }
            else if (direction == DOWN)
            {
                if(this.getY() < this.swiat.getHeight() - 1)
                    nextY++;
            }
            else if (direction == LEFT)
            {
                if(this.getX() > 0)
                    nextX--;
            }
            else if (direction == RIGHT)
            {
                if(this.getX() < this.swiat.getWidth() - 1)
                    nextX++;
            }
        }
    }
}