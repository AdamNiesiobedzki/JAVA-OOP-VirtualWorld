import java.util.Random;

public class WilczeJagody extends Roslina{
    private static final int SILA_WILCZE_JAGODY = 0;

    public WilczeJagody(int posX,int posY, Swiat swiat)
    {
        super(swiat,SILA_WILCZE_JAGODY,posX,posY,"WilczeJagody", "wilczejagody.png");
        swiat.dodajOrganizm(this);
    }

    public WilczeJagody(int posX,int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,posX,posY,"WilczeJagody", "wilczejagody.png");
        swiat.dodajOrganizm(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("J");
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new WilczeJagody(posX,posY,swiat);
    }


    @Override
    public boolean czyOdbilAtak(Organizm atakujacy)
    {
        swiat.zabij(atakujacy.getX(), atakujacy.getY());
        return false;
    }
}
