
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class ates{
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
public class Oyun extends JPanel implements KeyListener,ActionListener{
    
    
    Timer timer = new Timer(1,this);
    
    
    private int gecen_sure = 0;
    private int atis_sayisi=0;
    private BufferedImage image;
    private ArrayList<ates> atesler = new ArrayList<ates>();
    
    private int atesdirY = 5;
    private int topX = 0;
    private int topdirX = 2;
    private int uzayGemisiX = 0;
    private int uzaydirX = 20;

    
    public boolean kontrolEt(){
        for (ates Ates : atesler){
            if(new Rectangle(Ates.getX(),Ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){
                return true;
            }
               
               
            
            }
        return false;
    }
    
    
    
    public Oyun() throws FileNotFoundException {
        
        try {
            image = ImageIO.read(new FileInputStream(new File("bayraktar.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.WHITE);
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure+=1;
        
        g.setColor(Color.black);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image, uzayGemisiX, 418, image.getHeight()/1, image.getWidth()/1,this);
        timer.start();
        for (ates Ates : atesler){
            if(Ates.getY()<0){
                atesler.remove(Ates);
                
            }
        }
        g.setColor(Color.RED);
        for (ates Ates : atesler){
            g.fillRect(Ates.getX(), Ates.getY(), 10, 5);
        }
        if(kontrolEt()){
            timer.stop();
            String message = "KAZANDINIZ \n" +
                             "Harcanan Ateş:" + atis_sayisi +
                             "Geçen Süre : " + gecen_sure/100;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT){
            if(uzayGemisiX<=0 ){
                uzayGemisiX=0;
                
            }
            else{
                uzayGemisiX -= uzaydirX;
            }
            
        }
        else if (c == KeyEvent.VK_RIGHT){
            if(uzayGemisiX>=590){
                uzayGemisiX=590;
                
            }
            else{
                uzayGemisiX += uzaydirX;
                
            }
        }
        else if(c == KeyEvent.VK_CONTROL){
            atesler.add(new ates(uzayGemisiX+95, 450));
            atis_sayisi++;
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (ates Ates : atesler){
            
            Ates.setY(Ates.getY() - atesdirY);
        }
        
        topX += topdirX;
        if (topX >= 775){
            topdirX = -topdirX;
            
        }
        if (topX <= 0){
            topdirX = -topdirX;
            
            
        }
        repaint();
        
    }
    
}
