import java.util.Random;

public abstract class Roslina extends Organizm{
    protected static final int SZANSA_NA_ROZSIANIE = 1;
    private static final int INICJATYWA_ROSLINY = 0;

    public Roslina(Swiat swiat,int sila,int posX, int posY, String nazwa, String sciezka)
    {
        super(swiat,sila,INICJATYWA_ROSLINY,posX,posY,nazwa, sciezka);
    }

    @Override
    public void akcja()
    {
        wykonanoRuch = true;
        Random rand = new Random();
        int zasiew = rand.nextInt(15);
        if(zasiew < SZANSA_NA_ROZSIANIE)
        {
            this.rozmnazanie();
        }
    }
}
