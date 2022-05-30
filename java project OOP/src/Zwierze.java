import java.util.Random;

public abstract class Zwierze extends Organizm {
    protected static final int LEFT = 0;
    protected static final int RIGHT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;
    private static final int WYGRANA = 1;
    private static final int PRZEGRANA = 2;
    private static final int ROZMNAZANIE = 3;
    private static final int REMIS = 4;

    public Zwierze(Swiat swiat, int sila, int inicjatywa, int posX, int posY, String nazwa,String sciezka)
    {
        super(swiat,sila,inicjatywa,posX,posY,nazwa,sciezka);
    }

    @Override
    public void akcja()
    {
        this.wykonanoRuch = true;
        Random rand = new Random();
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

    public int kolizja(Organizm organizm)
    {
        if(this.czyTenSamGatunek(organizm))
        {
            if(this.getWiek() > 1 && organizm.getWiek() > 1)
                return ROZMNAZANIE;
            else return REMIS;
        }
        else if(this.getSila() >= organizm.getSila())
        {
            if(organizm.czyOdbilAtak(this))
            {
                return REMIS;
            }
            else return WYGRANA;
        }
        else
        {
            if(this.czyOdbilAtak(organizm)) return REMIS;
            else return PRZEGRANA;
        }
    }

    public boolean czyTenSamGatunek(Organizm organizm)
    {
        return false;
    }
}
