import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    public boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    public GamePanel(int width, int height){
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }

    public void init(){
        running = true;

        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D)img.getGraphics();
    }

    @Override
    public void run() {
        init();
    }
}
