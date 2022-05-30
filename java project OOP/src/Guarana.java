import java.util.Random;

public class Guarana extends Roslina{

    private static final int SILA_GUARANY = 0;
    private static final int BONUS_SILY = 3;

    public Guarana(int posX,int posY, Swiat swiat)
    {
        super(swiat,SILA_GUARANY,posX,posY,"Guarana", "guarana.png");
        swiat.dodajOrganizm(this);
    }

    public Guarana(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat, sila , posX, posY ,"Guarana", "guarana.png");
        swiat.dodajOrganizm(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("G");
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Guarana(posX,posY,swiat);
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        atakujacy.zwiekszSile(BONUS_SILY);
        return false;
    }
}
