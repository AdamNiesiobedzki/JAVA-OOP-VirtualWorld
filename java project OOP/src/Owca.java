public class Owca extends Zwierze{
    private static final int INICJATYWA_OWCY = 4;
    private static final int SILA_OWCY = 4;

    public Owca(int posX, int posY, Swiat swiat)
    {
        super(swiat,SILA_OWCY,INICJATYWA_OWCY,posX,posY,"Owca", "owca.png");
        swiat.dodajOrganizm(this);
    }

    public Owca(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,INICJATYWA_OWCY,posX,posY,"Owca", "owca.png");
        swiat.dodajOrganizm(this);
    }

    public void rysowanie()
    {
        System.out.print("O");
    }

    @Override
    public boolean czyTenSamGatunek(Organizm organizm)
    {
        if (organizm instanceof Owca)
            return true;
        else
            return false;
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Owca(posX,posY,swiat);
    }
}
