import java.awt.*;
import javax.swing.*;

public class Loading extends JFrame{
    JProgressBar bar = new JProgressBar();
    private final Image bgImage = new ImageIcon("FBG.png").getImage();
    private final Image logo = new ImageIcon("FLOGO.png").getImage();
    Loading(){
        Color color  = new Color(0xc3203c);
        bar.setValue(0);
        bar.setBounds(0,470,854,10);
        bar.setForeground(color);
        bar.setBorderPainted(false);

        add(bar);
        setIconImage(logo);

        setSize(854,480);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(null);  // the container will have no layout manager
        setVisible(true);

        fill();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        // Draw the background image
        g.drawImage(bgImage, 0, 0, null);
    }
    public void fill(){
        int counter = 0;

        while(counter <= 100)
        {
            bar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            counter++;
        }
        dispose();
    }

}
