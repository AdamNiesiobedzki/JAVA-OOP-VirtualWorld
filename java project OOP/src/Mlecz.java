import java.util.Random;

public class Mlecz extends Roslina{

    private static final int SILA_MLECZA = 0;
    private static final int ILOSC_PROB_ROZSIANIA = 3;

    public Mlecz(int posX,int posY, Swiat swiat)
    {
        super(swiat,SILA_MLECZA,posX,posY,"Mlecz", "mlecz.png");
        swiat.dodajOrganizm(this);
    }

    public Mlecz(int posX,int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,posX,posY,"Mlecz", "mlecz.png");
        swiat.dodajOrganizm(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("M");
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new Mlecz(posX,posY,swiat);
    }

    @Override
    public void akcja()
    {
        wykonanoRuch = true;
        Random rand = new Random();
        for(int i = 0; i < ILOSC_PROB_ROZSIANIA; i++)
        {
            int zasiew = rand.nextInt(15);
            if(zasiew < SZANSA_NA_ROZSIANIE)
            {
                this.rozmnazanie();
                return;
            }
        }
    }
}
