import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;



public class Swiat {
    private static final int BRAK_KOLIZJI = 0;
    private static final int PRZEGRANA = 2;
    private static final int WYGRANA = 1;
    private static final int ROZMNAZANIE = 3;
    private static final int REMIS = 4;
    private final static ImageIcon pustePole = new ImageIcon("puste.png");;
    private final static ImageIcon antylopaPNG = new ImageIcon("antylopa.png");
    private final static ImageIcon barszczPNG = new ImageIcon("barszcz.png");
    private final static ImageIcon guaranaPNG = new ImageIcon("guarana.png");
    private final static ImageIcon lisPNG = new ImageIcon("lis.png");
    private final static ImageIcon mleczPNG = new ImageIcon("mlecz.png");
    private final static ImageIcon owcaPNG = new ImageIcon("owca.png");
    private final static ImageIcon trawaPNG = new ImageIcon("trawa.png");
    private final static ImageIcon jagodyPNG = new ImageIcon("wilczejagody.png");
    private final static ImageIcon wilkPNG = new ImageIcon("wilk.png");
    private final static ImageIcon zolwPNG = new ImageIcon("zolw.png");
    private int width;
    private int height;
    private int tura;
    private int populacja;
    private String organizmDoDodania;
    private Organizm[][] plansza;
    private Organizm[] organizmy;
    private Czlowiek czlowiek;
    private int nextMove;
    private MyFrame myFrame;
    private JPanel mapa;
    private JPanel statystyki;
    private JPanel pasekDolny;
    private JButton turaDisplay;
    private JButton silaDisplay;
    private JButton cooldownDisplay;
    private JButton addAntylopa;
    private JButton addBarszcz;
    private JButton addWilk;
    private JButton addLis;
    private JButton addGuarana;
    private JButton addJagody;
    private JButton addZolw;
    private JButton addMlecz;
    private JButton addOwca;
    private JButton addTrawa;
    private JButton zapiszButton;
    private JButton[][] planszaWizualizacja;


    public Swiat(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.tura = 0;
        this.populacja = 0;
        this.czlowiek = null;
        this.nextMove = 0;
        organizmDoDodania = "antylopa";
        if (width <= 0 || height <= 0)
        {
            System.out.println("niewlasciwe parametry");
            return;
        }
        else
        {
            myFrame = new MyFrame(width,height,this);
            Border border = BorderFactory.createLineBorder(Color.black,1);
            mapa = new JPanel(new GridLayout(width,height));
            mapa.setBounds(0,0,900,900);
            statystyki = new JPanel(new GridLayout(14,1));
            statystyki.setBounds(900,0,200,900);
            pasekDolny = new JPanel();
            pasekDolny.setBounds(0,900,1100,100);
            planszaWizualizacja = new JButton[width][height];
            plansza = new Organizm[width][height];
            organizmy = new Organizm[width*height];
            for(int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    final int posY = y;
                    final int posX = x;
                    final Swiat world = this;
                    planszaWizualizacja[y][x] = new JButton();
                    planszaWizualizacja[y][x].setFocusable(false);
                    planszaWizualizacja[y][x].setBackground(Color.gray);
                    planszaWizualizacja[y][x].setBorder(border);
                    planszaWizualizacja[y][x].addActionListener(e -> nowyOrganizm(posX,posY,world));
                    mapa.add(planszaWizualizacja[y][x]);
                }
            }
            myFrame.add(mapa);
            myFrame.add(statystyki);
            myFrame.add(pasekDolny);
            for (int i = 0; i < width*height; i++)
                organizmy[i] = null;
        }
        createHUD();
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getPopulacja()
    {
        return populacja;
    }

    public void przypiszCzlowieka(Czlowiek organizm)
    {
        this.czlowiek = organizm;
    }

    public void rysuj()
    {
        for (int y = 0; y < this.getHeight(); y++)
        {
            for (int x = 0; x < this.getHeight(); x++)
            {
                if(plansza[y][x] == null)
                {
                    System.out.print(" ");
                }
                else plansza[y][x].rysowanie();
            }
            System.out.println();
        }
    }

    public void dodajOrganizm(Organizm nowyOrganizm)
    {
        organizmy[this.getPopulacja()] = nowyOrganizm;
        populacja++;
    }

    public void nowyOrganizm(int posX,int posY, Swiat swiat)
    {
        if (this.organizmDoDodania.equals("antylopa")) new Antylopa(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("barszcz")) new BarszczSosnowskiego(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("guarana")) new Guarana(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("lis")) new Lis(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("mlecz")) new Mlecz(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("owca")) new Owca(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("trawa")) new Trawa(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("jagody")) new WilczeJagody(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("wilk")) new Wilk(posX,posY,swiat);
        else if(this.organizmDoDodania.equals("zolw")) new Zolw(posX,posY,swiat);
    }

    public void aktualizujPlansze()
    {
        for(int y = 0; y < this.getHeight(); y++)
        {
            for (int x = 0; x < this.getWidth(); x++)
            {
                plansza[y][x] = null;
                planszaWizualizacja[y][x].setIcon(pustePole); ;
            }
        }
        for(int i = 0; i < populacja; i++)
        {
            if (organizmy[i] != null)
            {
                int posX = organizmy[i].getX();
                int posY = organizmy[i].getY();
                plansza[posY][posX] = organizmy[i];
                planszaWizualizacja[posY][posX].setIcon(organizmy[i].grafika);
            }
        }
    }

    public void wykonajTure()
    {

            for(int i = 0; i < this.getPopulacja(); i++)
            {
                if(organizmy[i] != null && organizmy[i].getWiek() != 0)
                {
                    organizmy[i].akcja();
                    int result = BRAK_KOLIZJI;
                    for(int j = 0; j<this.getPopulacja(); j++)
                    {
                        if(organizmy[i] != null && organizmy[j] != null && j!=i)
                        {
                            if(organizmy[i].getNextX() == organizmy[j].getNextX()
                                    && organizmy[i].getNextY() == organizmy[j].getNextY())
                            {
                                result = organizmy[i].kolizja(organizmy[j]);
                                if (result == WYGRANA)
                                {
                                    organizmy[j] = null;
                                    if (organizmy[i]!=null)organizmy[i].przemiesc();
                                }
                                else if(result == PRZEGRANA)
                                {
                                    organizmy[i] = null;
                                }
                                else if(result == ROZMNAZANIE)
                                {
                                    organizmy[i].rozmnazanie();
                                    organizmy[i].zostanWMiejscu();
                                }
                                else if(result == REMIS)
                                {
                                    organizmy[i].zostanWMiejscu();
                                }
                            }
                        }
                    }
                    if (result == BRAK_KOLIZJI) organizmy[i].przemiesc();
                    this.aktualizujPlansze();
                }
            }
            tura++;
            this.sortujOrganizmy();
            this.zliczOrganizmy();
            this.aktualizujPlansze();
            this.postarzOrganizmy();
            cooldownDisplay.setText("cooldown: " + czlowiek.getCooldown());
            silaDisplay.setText("sila czlowieka: " + czlowiek.getSila());
            turaDisplay.setText("obecna tura: " + this.tura);
    }

    public void setNextMove(char move)
    {
        if (move == 'a')
        {
            this.nextMove = 0;
            this.wykonajTure();
        }
        else if (move == 'w')
        {
            this.nextMove = 2;
            this.wykonajTure();
        }
        else if (move == 'd')
        {
            this.nextMove = 1;
            this.wykonajTure();
        }
        else if (move == 's')
        {
            this.nextMove = 3;
            this.wykonajTure();
        }
        else if (move == 'q')
        {
            this.czlowiek.umiejetnosc();
        }
        else if (move == 'e')
        {
            this.save();
        }
    }

    public int getNextMove()
    {
        return nextMove;
    }

    public void postarzOrganizmy()
    {
        for(int i = 0; i < populacja; i++)
        {
            organizmy[i].postarz();
        }
    }

    public void sortujOrganizmy()
    {
        Organizm tmp;
        int zamiana;
        while(true)
        {
            zamiana = 0;
            for(int i = 1; i < populacja; i++)
            {
                if(organizmy[i]!=null)
                {
                    if(organizmy[i-1]==null)
                    {
                        tmp = organizmy[i];
                        organizmy[i] = organizmy[i-1];
                        organizmy[i-1] = tmp;
                        zamiana = 1;
                    }
                    else if(organizmy[i].getInicjatwa() > organizmy[i-1].getInicjatwa())
                    {
                        tmp = organizmy[i];
                        organizmy[i] = organizmy[i-1];
                        organizmy[i-1] = tmp;
                        zamiana = 1;
                    }
                    else if(organizmy[i].getInicjatwa() == organizmy[i-1].getInicjatwa()
                    && organizmy[i].getWiek() > organizmy[i-1].getWiek())
                    {
                        tmp = organizmy[i];
                        organizmy[i] = organizmy[i-1];
                        organizmy[i-1] = tmp;
                        zamiana = 1;
                    }
                }
            }
            if (zamiana == 0) break;
        }
    }

    public void zliczOrganizmy()
    {
        this.populacja = 0;
        for(int i = 0; i < width*height; i++)
        {
            if(organizmy[i]!= null)
                populacja++;
        }
    }

    public Organizm getPolePlanszy(int x, int y)
    {
        return plansza[y][x];
    }

    public void zabij(int zabijX, int zabijY)
    {
        for (int i = 0; i < populacja; i++)
        {
            if(organizmy[i]!=null && organizmy[i].getX() == zabijX && organizmy[i].getY() == zabijY
            && organizmy[i] instanceof Zwierze)
            {
                organizmy[i] = null;
                break;
            }
        }
    }

    public void save()
    {
        File file = new File("save.txt");
        try {
            file.mkdirs();
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(this.getTura());
            for(int i = 0; i < populacja; i++)
            {
                pw.println(organizmy[i].getNazwa());
                pw.println(organizmy[i].getX());
                pw.println(organizmy[i].getY());
                pw.println(organizmy[i].getSila());
                if (organizmy[i] instanceof Czlowiek)
                {
                    pw.println(((Czlowiek) organizmy[i]).getCooldown());
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getTura()
    {
        return this.tura;
    }

    public void setTura(int liczba)
    {
        this.tura = liczba;
    }

    public void createHUD()
    {
        turaDisplay = new JButton();
        turaDisplay.setBackground(Color.cyan);
        turaDisplay.setFocusable(false);
        statystyki.add(turaDisplay);

        silaDisplay = new JButton();
        silaDisplay.setBackground(Color.ORANGE);
        statystyki.add(silaDisplay);
        silaDisplay.setFocusable(false);

        cooldownDisplay = new JButton();
        cooldownDisplay.setBackground(Color.yellow);
        cooldownDisplay.setFocusable(false);
        statystyki.add(cooldownDisplay);

        addAntylopa = new JButton();
        addAntylopa.setFocusable(false);
        addAntylopa.setIcon(antylopaPNG);
        addAntylopa.addActionListener(e -> organizmDoDodania = "antylopa");
        statystyki.add(addAntylopa);

        addBarszcz = new JButton();
        addBarszcz.setFocusable(false);
        addBarszcz.setIcon(barszczPNG);
        addBarszcz.addActionListener(e -> organizmDoDodania = "barszcz");
        statystyki.add(addBarszcz);

        addGuarana = new JButton();
        addGuarana.setFocusable(false);
        addGuarana.setIcon(guaranaPNG);
        addGuarana.addActionListener(e -> organizmDoDodania = "guarana");
        statystyki.add(addGuarana);

        addLis= new JButton();
        addLis.setFocusable(false);
        addLis.setIcon(lisPNG);
        addLis.addActionListener(e -> organizmDoDodania = "lis");
        statystyki.add(addLis);

        addMlecz = new JButton();
        addMlecz.setFocusable(false);
        addMlecz.setIcon(mleczPNG);
        addMlecz.addActionListener(e -> organizmDoDodania = "mlecz");
        statystyki.add(addMlecz);

        addOwca = new JButton();
        addOwca.setFocusable(false);
        addOwca.setIcon(owcaPNG);
        addOwca.addActionListener(e -> organizmDoDodania = "owca");
        statystyki.add(addOwca);

        addTrawa = new JButton();
        addTrawa.setFocusable(false);
        addTrawa.setIcon(trawaPNG);
        addTrawa.addActionListener(e -> organizmDoDodania = "trawa");
        statystyki.add(addTrawa);

        addJagody = new JButton();
        addJagody.setFocusable(false);
        addJagody.setIcon(jagodyPNG);
        addJagody.addActionListener(e -> organizmDoDodania = "jagody");
        statystyki.add(addJagody);

        addWilk = new JButton();
        addWilk.setFocusable(false);
        addWilk.setIcon(wilkPNG);
        addWilk.addActionListener(e -> organizmDoDodania = "wilk");
        statystyki.add(addWilk);

        addZolw = new JButton();
        addZolw.setFocusable(false);
        addZolw.setIcon(zolwPNG);
        addZolw.addActionListener(e -> organizmDoDodania = "zolw");
        statystyki.add(addZolw);

        zapiszButton = new JButton();
        zapiszButton.setFocusable(false);
        zapiszButton.setText("zapisz");
        zapiszButton.addActionListener(e -> save());
        statystyki.add(zapiszButton);
    }
}
