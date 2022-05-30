public class Wilk extends Zwierze{
    private static final int INICJATYWA_WILKA = 5;
    private static final int SILA_WILKA = 9;

    public Wilk(int posX, int posY, Swiat swiat)
    {
        super(swiat,SILA_WILKA,INICJATYWA_WILKA,posX,posY,"Wilk", "wilk.png");
        swiat.dodajOrganizm(this);
    }

    public Wilk(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,INICJATYWA_WILKA,posX,posY,"Wilk", "wilk.png");
        swiat.dodajOrganizm(this);
    }

    public void rysowanie()
    {
        System.out.print("W");
    }

    @Override
    public boolean czyTenSamGatunek(Organizm organizm)
    {
        if (organizm instanceof Wilk)
            return true;
        else
            return false;
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Wilk(posX,posY,swiat);
    }
}
