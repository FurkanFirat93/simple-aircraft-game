
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import javax.swing.JFrame;


public class OyunDeneme extends JFrame{

    public OyunDeneme(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
    OyunDeneme ekran = new OyunDeneme("Bayraktar TB-2");
    ekran.setResizable(false);
    ekran.setFocusable(false);
    ekran.setSize(800, 600);
    ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Oyun oyun = new Oyun();
    oyun.requestFocus();
    oyun.addKeyListener(oyun);
    oyun.setFocusable(true);
    oyun.setFocusTraversalKeysEnabled(false);
    
    ekran.add(oyun);
    ekran.setVisible(true);
    
    }
}
