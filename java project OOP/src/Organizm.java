import javax.swing.*;

public abstract class Organizm {
    protected int sila;
    protected int inicjatywa;
    protected int wiek;
    protected int x,y;
    protected String nazwa;
    protected int nextX,nextY;
    protected boolean wykonanoRuch;
    protected ImageIcon grafika;
    protected Swiat swiat;

    public Organizm(Swiat swiat, int sila, int inicjatywa, int posX, int posY, String nazwa, String sciezka)
    {
        this.swiat = swiat;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.x = posX;
        this.nextX = posX;
        this.y = posY;
        this.nextY = posY;
        this.nazwa = nazwa;
        this.wiek = 0;
        this.wykonanoRuch = false;
        this.grafika = new ImageIcon(sciezka);
    }

    public int getSila()
    {
        return this.sila;
    }

    public int getInicjatwa()
    {
        return this.inicjatywa;
    }

    public int getWiek()
    {
        return this.wiek;
    }

    public int getX()
    {
        return this.x;
    }

    public int getNextX()
    {
        return this.nextX;
    }

    public int getY()
    {
        return this.y;
    }

    public int getNextY()
    {
        return this.nextY;
    }

    public String getNazwa()
    {
        return this.nazwa;
    }

    public boolean czyWykonanoRuch()
    {
        return wykonanoRuch;
    }

    public void postarz()
    {
        wiek++;
        wykonanoRuch = false;
    }

    public void zwiekszSile(int bonus)
    {
        sila = sila + bonus;
    }

    public void rysowanie()
    {
        System.out.print("x");
    }

    public int kolizja(Organizm organizm)
    {
        return 0;
    }

    public void przemiesc()
    {
        this.x = this.nextX;
        this.y = this.nextY;
    }

    public void zostanWMiejscu()
    {
        this.nextX = this.x;
        this.nextY = this.y;
    }

    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        return false;
    }

    public boolean czyCzlowiek()
    {
        return false;
    }

    public void rozmnazanie()
    {
        if(y-1 >= 0 && swiat.getPolePlanszy(x, y-1) == null)
        {
            this.utworzDziecko(x,y-1,swiat);
        }
        else if (x - 1 >= 0 && swiat.getPolePlanszy(x-1,y) == null)
        {
            this.utworzDziecko(x-1,y,swiat);
        }
        else if (x+1 < swiat.getWidth() && swiat.getPolePlanszy(x+1,y) == null)
        {
            this.utworzDziecko(x+1,y,swiat);
        }
        else if (y+1 < swiat.getHeight() && swiat.getPolePlanszy(x,y+1) == null)
        {
            this.utworzDziecko(x,y+1,swiat);
        }
    }

    public void utworzDziecko(int posX, int posY, Swiat swiat) {}

    public void akcja() {}
}
