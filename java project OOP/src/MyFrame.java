import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener
{
    private Swiat swiat;
    MyFrame(int width, int height,Swiat swiat)
    {
        this.setTitle("Wirtualny Swiat Adam Niesiobedzki 188641");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(1100,1000);
        this.addKeyListener(this);
        this.setVisible(true);
        this.swiat = swiat;
        this.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case 37: this.swiat.setNextMove('a'); break;
            case 38: this.swiat.setNextMove('w'); break;
            case 39: this.swiat.setNextMove('d'); break;
            case 40: this.swiat.setNextMove('s'); break;
            case 81: this.swiat.setNextMove('q'); break;
            case 69: this.swiat.setNextMove('e'); break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
