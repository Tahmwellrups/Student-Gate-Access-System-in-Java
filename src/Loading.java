import javax.swing.*;
import java.awt.*;
import java.util.Objects;


public class Loading extends JFrame{

    public Loading()
    {
        JPanel ldPanel = new JPanel();
        //Image ogBG = new
        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("ACSYS4.png")));
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("logo.png")));
        JLabel ldScr = new JLabel(background);
        ldPanel.add(ldScr);

        setSize(854, 480);
        setIconImage(logo.getImage());
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        add(ldPanel, BorderLayout.CENTER);
    }

    public void showLDScreen()
    {
        setVisible(true);
    }
    public void hideLDScreen()
    {
        setVisible(false);
    }

}
