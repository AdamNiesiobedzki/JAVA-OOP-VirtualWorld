import java.util.Random;

public class Antylopa extends Zwierze{
    private static final int INICJATYWA_ANTYLOPY = 4;
    private static final int SILA_ANTYLOPY = 4;
    private static final int UCIECZKA = 1;

    public Antylopa(int posX, int posY, Swiat swiat)
    {
        super(swiat,SILA_ANTYLOPY,INICJATYWA_ANTYLOPY,posX,posY,"Antylopa","antylopa.png");
        swiat.dodajOrganizm(this);
    }

    public Antylopa(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,INICJATYWA_ANTYLOPY,posX,posY,"Antylopa","antylopa.png");
        swiat.dodajOrganizm(this);
    }

    public void rysowanie()
    {
        System.out.print("A");
    }

    @Override
    public boolean czyTenSamGatunek(Organizm organizm)
    {
        if (organizm instanceof Antylopa)
            return true;
        else
            return false;
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Antylopa(posX,posY,swiat);
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        Random rand = new Random();
        int obrona = rand.nextInt(2);
        int sukces = 0;
        if(obrona == UCIECZKA)
        {
            if(y-1 >= 0 && swiat.getPolePlanszy(x,y-1) == null)
            {
                y--;
                nextY = y;
                sukces = 1;
            }
            else if(x - 1 >= 0 && swiat.getPolePlanszy(x-1, y) == null)
            {
                x--;
                nextX = x;
                sukces = 1;
            }
            else if (x+1 < swiat.getWidth() && swiat.getPolePlanszy(x+1,y) == null)
            {
                x++;
                nextX = x;
                sukces = 1;
            }
            else if (y+1 < swiat.getHeight() && swiat.getPolePlanszy(x,y+1) == null)
            {
                y++;
                nextY = y;
                sukces = 1;
            }
        }
        if(sukces == 1)
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
        int direction = rand.nextInt(4);
        if(direction == UP)
        {
            if(this.getY() > 1)
                nextY = nextY - 2;
        }
        else if (direction == DOWN)
        {
            if(this.getY() < this.swiat.getHeight() - 2)
                nextY = nextY + 2;
        }
        else if (direction == LEFT)
        {
            if(this.getX() > 1)
                nextX = nextX - 2;
        }
        else if (direction == RIGHT)
        {
            if(this.getX() < this.swiat.getWidth() - 2)
                nextX = nextX + 2;
        }
    }
}