import javax.swing.*;

public class Czlowiek extends Zwierze{
    private static final int INICJATYWA_CZLOWIEKA = 4;
    private static final int SILA_CZLOWIEKA = 5;
    private static final int DODATKOWA_SILA = 10;
    private static final int CZAS_ODNOWIENIA = 10;
    private int skillCD;

    public Czlowiek(int posX, int posY, Swiat swiat)
    {
        super(swiat, SILA_CZLOWIEKA, INICJATYWA_CZLOWIEKA, posX, posY ,"Czlowiek", "czlowiek.png");
        this.skillCD = 0;
        swiat.dodajOrganizm(this);
        swiat.przypiszCzlowieka(this);
    }

    public Czlowiek(int posX, int posY,int sila,int cooldown, Swiat swiat)
    {
        super(swiat, sila, INICJATYWA_CZLOWIEKA, posX, posY ,"Czlowiek", "czlowiek.png");
        this.skillCD = cooldown;
        swiat.dodajOrganizm(this);
        swiat.przypiszCzlowieka(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("C");
    }

    @Override
    public void akcja()
    {
        if(skillCD > 0)
        {
            skillCD--;
            sila--;
        }
        if(swiat.getNextMove() == UP)
        {
            if(this.getY() > 0)
                nextY--;
        }
        else if (swiat.getNextMove() == DOWN)
        {
            if(this.getY() < this.swiat.getHeight() - 1)
                nextY++;
        }
        else if (swiat.getNextMove() == LEFT)
        {
            if(this.getX() > 0)
                nextX--;
        }
        else if (swiat.getNextMove() == RIGHT)
        {
            if(this.getX() < this.swiat.getWidth() - 1)
                nextX++;
        }
    }

    public void umiejetnosc()
    {
        if (skillCD == 0)
        {
            this.zwiekszSile(DODATKOWA_SILA);
            skillCD = CZAS_ODNOWIENIA;
        }
    }

    public int getCooldown()
    {
        return skillCD;
    }
}
