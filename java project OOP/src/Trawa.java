public class Trawa extends Roslina{

    private static final int SILA_TRAWY = 0;

    public Trawa(int posX,int posY, Swiat swiat)
    {
        super(swiat,SILA_TRAWY,posX,posY,"Trawa", "trawa.png");
        swiat.dodajOrganizm(this);
    }

    public Trawa(int posX,int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,posX,posY,"Trawa", "trawa.png");
        swiat.dodajOrganizm(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("T");
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Trawa(posX,posY,swiat);
    }
}
