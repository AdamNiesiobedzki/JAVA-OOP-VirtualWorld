import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.Scanner;


public class Main {
private static final int PRESET = 1;
private static final int LOAD = 2;

    public static void main(String[] args)
    {
        System.out.println("1 - Zacznij gre");
        System.out.println("2 - Wczytaj gre");
        Scanner scan = new Scanner(System.in);
        int wybor = scan.nextInt();
        Swiat swiat = new Swiat(20,20);
        if (wybor == PRESET)
        {
            Czlowiek hania = new Czlowiek(10,10,swiat);
            Wilk sendi = new Wilk(10,1,swiat);
            Wilk bibi = new Wilk (16,1,swiat);
            Wilk drops = new Wilk (15,15,swiat);
            Lis rys = new Lis (11,13,swiat);
            Lis lisek = new Lis (15,10,swiat);
            BarszczSosnowskiego barszczyk = new BarszczSosnowskiego(1,1,swiat);
            Trawa trawa = new Trawa (12,12,swiat);
            Mlecz mlecz = new Mlecz (4,2,swiat);
            Zolw lodowy = new Zolw (2,5,swiat);
            Zolw zolwik = new Zolw (3,4,swiat);
            Antylopa antylopa = new Antylopa(1,18,swiat);
            Antylopa antylopa2 = new Antylopa(18,7,swiat);
            Guarana guarana = new Guarana(7,10,swiat);
            Guarana guarana1 = new Guarana(19,19,swiat);
            WilczeJagody jagody = new WilczeJagody(19,7,swiat);
        }
        else if (wybor == LOAD)
        {
            InputStream ins = null; // raw byte-stream
            Reader r = null; // cooked reader
            BufferedReader br = null; // buffered for readLine()
            try {
                String s;
                ins = new FileInputStream("save.txt");
                r = new InputStreamReader(ins, "UTF-8");
                br = new BufferedReader(r);
                swiat.setTura(Integer.parseInt(br.readLine()));
                while ((s = br.readLine()) != null) {
                    int x = Integer.parseInt(br.readLine());
                    int y = Integer.parseInt(br.readLine());
                    int sila = Integer.parseInt(br.readLine());
                    if(s.equals("Antylopa")) new Antylopa(x,y,sila,swiat);
                    else if(s.equals("BarszczSosnowskiego")) new BarszczSosnowskiego(x,y,sila,swiat);
                    else if(s.equals("Guarana")) new Guarana(x,y,sila,swiat);
                    else if(s.equals("Lis")) new Lis(x,y,sila,swiat);
                    else if(s.equals("Mlecz")) new Mlecz(x,y,sila,swiat);
                    else if(s.equals("Owca")) new Owca(x,y,sila,swiat);
                    else if(s.equals("Trawa")) new Trawa(x,y,sila,swiat);
                    else if(s.equals("WilczeJagody")) new WilczeJagody(x,y,sila,swiat);
                    else if(s.equals("Wilk")) new Wilk(x,y,sila,swiat);
                    else if(s.equals("Zolw")) new Zolw(x,y,sila,swiat);
                    else if(s.equals("Czlowiek")) new Czlowiek(x,y,sila,Integer.parseInt(br.readLine()),swiat);
                }
            }
            catch (Exception e)
            {
                System.err.println(e.getMessage());
            }
            finally {
                if (br != null) { try { br.close(); } catch(Throwable t) { /* ensure close happens */ } }
                if (r != null) { try { r.close(); } catch(Throwable t) { /* ensure close happens */ } }
                if (ins != null) { try { ins.close(); } catch(Throwable t) { /* ensure close happens */ } }
            }
        }
    }
}
