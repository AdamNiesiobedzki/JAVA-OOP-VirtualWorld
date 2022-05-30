import java.util.Random;

public class BarszczSosnowskiego extends Roslina{
    private static final int SILA_BARSZCZ_SOSNOWSKIEGO = 10;

    public BarszczSosnowskiego(int posX,int posY, Swiat swiat)
    {
        super(swiat,SILA_BARSZCZ_SOSNOWSKIEGO,posX,posY,"BarszczSosnowskiego","barszcz.png");
        swiat.dodajOrganizm(this);
    }

    public BarszczSosnowskiego(int posX, int posY,int sila, Swiat swiat)
    {
        super(swiat,sila,posX,posY,"BarszczSosnowskiego","barszcz.png");
        swiat.dodajOrganizm(this);
    }

    @Override
    public void rysowanie()
    {
        System.out.print("B");
    }

    @Override
    public void utworzDziecko(int posX, int posY, Swiat swiat)
    {
        new BarszczSosnowskiego(posX,posY,swiat);
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
        this.zabijDookola();
    }

    public void zabijDookola()
    {
        for(int i = this.getY() - 1; i <= this.getY() + 1; i++)
        {
            for (int l = this.getX() - 1; l <= this.getX() + 1; l++)
            {
                if(l >= 0 && l < this.swiat.getWidth()
                && i >= 0 && i < this.swiat.getHeight())
                {
                    swiat.zabij(l,i);
                }
            }
        }
    }
}